package com.ninni.uber.mixin.client;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.ninni.uber.Uber;
import com.ninni.uber.UberTags;
import com.ninni.uber.registry.secondary.UberFogTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Shadow private static float fogRed;
    @Shadow private static float fogGreen;
    @Shadow private static float fogBlue;
    @Shadow private static long biomeChangedTime;

    @Shadow @Final private static List<FogRenderer.MobEffectFogFunction> MOB_EFFECT_FOG;

    @Inject(at = @At("TAIL"), method = "setupColor")
    private static void manaFogColor(Camera camera, float f, ClientLevel clientLevel, int i, float g, CallbackInfo ci) {
        FogType fogType = camera.getFluidInCamera();
        Entity entity = camera.getEntity();

        if (fogType == UberFogTypes.MANA.get()) {
            fogRed = 0.2f;
            fogGreen = 0.8f;
            fogBlue = 0.3f;
            biomeChangedTime = -1L;
        }

        float u = ((float)camera.getPosition().y - (float)clientLevel.getMinBuildHeight()) * clientLevel.getLevelData().getClearColorScale();
        FogRenderer.MobEffectFogFunction mobEffectFogFunction = getPriorityFogFunction(entity, f);
        if (mobEffectFogFunction != null) {
            LivingEntity livingEntity = (LivingEntity)entity;
            u = mobEffectFogFunction.getModifiedVoidDarkness(livingEntity, livingEntity.getEffect(mobEffectFogFunction.getMobEffect()), u, f);
        }
        if (u < 1.0f && fogType != UberFogTypes.MANA.get()) {
            if (u < 0.0f) u = 0.0f;
            u *= u;
            fogRed *= u;
            fogGreen *= u;
            fogBlue *= u;
        }
    }


    @Inject(at = @At("HEAD"), method = "setupFog", cancellable = true)
    private static void setupManaFog(Camera camera, FogRenderer.FogMode fogMode, float f, boolean bl, float g, CallbackInfo ci) {
        ci.cancel();
        FogType fogType = camera.getFluidInCamera();
        Entity entity = camera.getEntity();
        FogRenderer.FogData fogData = new FogRenderer.FogData(fogMode);
        FogRenderer.MobEffectFogFunction mobEffectFogFunction = getPriorityFogFunction(entity, g);
        if (fogType == FogType.LAVA) {
            if (entity.isSpectator()) {
                fogData.start = -8.0f;
                fogData.end = f * 0.5f;
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasEffect(MobEffects.FIRE_RESISTANCE)) {
                fogData.start = 0.0f;
                fogData.end = 3.0f;
            } else {
                fogData.start = 0.25f;
                fogData.end = 1.0f;
            }
        } else if (fogType == FogType.POWDER_SNOW) {
            if (entity.isSpectator()) {
                fogData.start = -8.0f;
                fogData.end = f * 0.5f;
            } else {
                fogData.start = 0.0f;
                fogData.end = 2.0f;
            }
        } else if (fogType == UberFogTypes.MANA.get()) {
            if (entity.isSpectator()) {
                fogData.start = -10.0f;
                fogData.end = f * 0.75f;
            } else {
                fogData.start = -6.0f;
                fogData.end = f * 0.25f;
            }
        } else if (entity.getLevel().dimension() == Uber.UBER) {
            fogData.start = f * 0.05f;
            fogData.end = f * ((float)entity.getY()* 0.01f);
            fogData.shape = FogShape.CYLINDER;
        } else if (mobEffectFogFunction != null) {
            LivingEntity livingEntity = (LivingEntity)entity;
            MobEffectInstance mobEffectInstance = livingEntity.getEffect(mobEffectFogFunction.getMobEffect());
            if (mobEffectInstance != null) {
                mobEffectFogFunction.setupFog(fogData, livingEntity, mobEffectInstance, f, g);
            }
        } else if (fogType == FogType.WATER) {
            fogData.start = -8.0f;
            fogData.end = 96.0f;
            if (entity instanceof LocalPlayer localPlayer) {
                fogData.end *= Math.max(0.25f, localPlayer.getWaterVision());
                Holder<Biome> holder = localPlayer.level.getBiome(localPlayer.blockPosition());
                if (holder.is(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                    fogData.end *= 0.85f;
                }
            }
            if (fogData.end > f) {
                fogData.end = f;
                fogData.shape = FogShape.CYLINDER;
            }
        } else if (bl) {
            fogData.start = f * 0.05f;
            fogData.end = Math.min(f, 192.0f) * 0.5f;
        } else if (fogMode == FogRenderer.FogMode.FOG_SKY) {
            fogData.start = 0.0f;
            fogData.end = f;
            fogData.shape = FogShape.CYLINDER;
        } else {
            float h = Mth.clamp(f / 10.0f, 4.0f, 64.0f);
            fogData.start = f - h;
            fogData.end = f;
            fogData.shape = FogShape.CYLINDER;
        }
        RenderSystem.setShaderFogStart(fogData.start);
        RenderSystem.setShaderFogEnd(fogData.end);
        RenderSystem.setShaderFogShape(fogData.shape);
    }

    @Nullable
    private static FogRenderer.MobEffectFogFunction getPriorityFogFunction(Entity entity, float f) {
        if (entity instanceof LivingEntity livingEntity) return MOB_EFFECT_FOG.stream().filter(mobEffectFogFunction -> mobEffectFogFunction.isEnabled(livingEntity, f)).findFirst().orElse(null);
        return null;
    }
}
