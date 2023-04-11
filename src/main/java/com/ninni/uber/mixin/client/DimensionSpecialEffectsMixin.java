package com.ninni.uber.mixin.client;

import com.ninni.uber.client.renderer.UberEffects;
import com.ninni.uber.registry.secondary.UberBuiltinDimensionTypes;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DimensionSpecialEffects.class)
public class DimensionSpecialEffectsMixin {

    @Shadow @Final private static Object2ObjectMap<ResourceLocation, DimensionSpecialEffects> EFFECTS;

    @Inject(at = @At("RETURN"), method = "<clinit>")
    private static void Uber$onConstruct(CallbackInfo ci) {
        if (EFFECTS != null) {
            EFFECTS.put(UberBuiltinDimensionTypes.UBER_EFFECTS, new UberEffects());
        }
    }

}
