package com.ninni.uber.mixin.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import com.ninni.uber.Uber;
import com.ninni.uber.registry.UberSkyTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private static ResourceLocation END_SKY_LOCATION;
    @Shadow private @Nullable ClientLevel level;
    private static final ResourceLocation MINDS_EYE = new ResourceLocation(Uber.MOD_ID, "textures/environment/minds_eye.png");

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;", shift = At.Shift.AFTER), method = "renderSky")
    private void renderSky(PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {
        ClientLevel level = this.minecraft.level;
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        float l;
        float j;
        float r;
        float q;
        float p;
        if (level != null && level.effects().skyType() == UberSkyTypes.UBER.get()) {
            this.renderMindsEye(poseStack, bufferBuilder);
        }
    }

    private void renderMindsEye(PoseStack poseStack, BufferBuilder bufferBuilder) {
        Matrix4f matrix4f3 = poseStack.last().pose();
        float l = 30.0f;
        poseStack.pushPose();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, MINDS_EYE);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f3, -l, 100.0f, -l).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, l, 100.0f, -l).uv(1.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, l, 100.0f, l).uv(1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, -l, 100.0f, l).uv(0.0f, 1.0f).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        poseStack.popPose();
    }

}
