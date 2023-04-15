package com.ninni.uber.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Shadow private static float fogRed;
    @Shadow private static float fogGreen;
    @Shadow private static float fogBlue;
    @Shadow private static long biomeChangedTime;

    @Inject(at = @At("HEAD"), method = "setupColor")
    private static void manaFogColor(Camera camera, float f, ClientLevel clientLevel, int i, float g, CallbackInfo ci) {
        //TODO fix
        //FogType fogType = camera.getFluidInCamera();
        //if (fogType == UberFogTypes.MANA.get()) {
        //    fogRed = 0.0f;
        //    fogGreen = 0.6f;
        //    fogBlue = 0.1f;
        //    biomeChangedTime = -1L;
        //}
    }

    @Inject(at = @At("HEAD"), method = "setupFog")
    private static void setupManaFog(Camera camera, FogRenderer.FogMode fogMode, float f, boolean bl, float g, CallbackInfo ci) {
        //FogType fogType = camera.getFluidInCamera();
        //Entity entity = camera.getEntity();
        //FogRenderer.FogData fogData = new FogRenderer.FogData(fogMode);
        //if (fogType == UberFogTypes.MANA.get()) {
        //    if (entity.isSpectator()) {
        //        fogData.start = -8.0f;
        //        fogData.end = f * 0.5f;
        //    } else {
        //        fogData.start = 0.25f;
        //        fogData.end = 1.0f;
        //    }
        //}
    }
}
