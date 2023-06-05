package com.ninni.uber.mixin.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.ninni.uber.Uber;
import com.ninni.uber.registry.secondary.UberSkyTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow private @Nullable ClientLevel level;

    private static final ResourceLocation MINDS_EYE_LOCATION = new ResourceLocation(Uber.MOD_ID, "textures/environment/minds_eye.png");
    private static final ResourceLocation SHOOTING_STAR_LOCATION1 = new ResourceLocation(Uber.MOD_ID, "textures/environment/shooting_star1.png");
    private static final ResourceLocation SHOOTING_STAR_LOCATION2 = new ResourceLocation(Uber.MOD_ID, "textures/environment/shooting_star2.png");
    private static final ResourceLocation UBER_SKY_LOCATION = new ResourceLocation(Uber.MOD_ID, "textures/environment/uber_sky.png");

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;", shift = At.Shift.AFTER), method = "renderSky")
    private void renderSky(PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {
        ClientLevel level = this.minecraft.level;
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        if (level != null && level.effects().skyType() == UberSkyTypes.UBER.get()) {
            this.renderUberSky(poseStack, camera);
            //this.renderMindsEye(poseStack, bufferBuilder);
            //this.renderShootingStars(poseStack, bufferBuilder, 69, 1f, 3);
            //this.renderShootingStars(poseStack, bufferBuilder, 60, -0.7f, 4);
            //this.renderShootingStars(poseStack, bufferBuilder, 55, 1.2f, 3);
            //this.renderShootingStars(poseStack, bufferBuilder, 47, -0.35f, 6);
            //this.renderShootingStars(poseStack, bufferBuilder, 31, 0.5f, 3);
            //this.renderShootingStars(poseStack, bufferBuilder, 23, -0.5f, 5);
            //this.renderShootingStars(poseStack, bufferBuilder, 7, 0.7f, 4);
            //this.renderShootingStars(poseStack, bufferBuilder, -10, -1f, 4);
            //this.renderShootingStars(poseStack, bufferBuilder, -20, 1.2f, 3);
            //this.renderShootingStars(poseStack, bufferBuilder, -31, -0.4f, 3);
            //this.renderShootingStars(poseStack, bufferBuilder, -43, 0.6f, 4);
            //this.renderShootingStars(poseStack, bufferBuilder, -50, -0.4f, 4);
            //this.renderShootingStars(poseStack, bufferBuilder, -69, 0.4f, 5);
            //this.renderShootingStars(poseStack, bufferBuilder, -75, -0.3f, 5);
        }
    }

    private void renderShootingStars(PoseStack poseStack, BufferBuilder bufferBuilder, float z, float speed, int size) {
        assert this.level != null;
        poseStack.pushPose();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees((float)this.level.getGameTime() * speed));
        poseStack.mulPose(Axis.ZP.rotationDegrees(z));
        Matrix4f matrix4f3 = poseStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, (speed > 0 ? SHOOTING_STAR_LOCATION1 : SHOOTING_STAR_LOCATION2));
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f3, -size, 100.0f, -size).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, size, 100.0f, -size).uv(1.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, size, 100.0f, size).uv(1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, -size, 100.0f, size).uv(0.0f, 1.0f).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        poseStack.popPose();
    }

    private void renderMindsEye(PoseStack poseStack, BufferBuilder bufferBuilder) {
        float size = 30.0f;
        poseStack.pushPose();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0F);
        assert this.level != null;
        poseStack.mulPose(Axis.YP.rotationDegrees((float)this.level.getGameTime()/8));
        Matrix4f matrix4f3 = poseStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, MINDS_EYE_LOCATION);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f3, -size, 100.0f, -size).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, size, 100.0f, -size).uv(1.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, size, 100.0f, size).uv(1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, -size, 100.0f, size).uv(0.0f, 1.0f).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        poseStack.popPose();
    }

    private void renderUberSky(PoseStack poseStack, Camera camera) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, UBER_SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();


        for (int i = 0; i < 6; ++i) {
            poseStack.pushPose();

            if (i == 1) poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
            if (i == 2) poseStack.mulPose(Axis.XP.rotationDegrees(-90.0f));
            if (i == 3) poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
            if (i == 4) poseStack.mulPose(Axis.ZP.rotationDegrees(90.0f));
            if (i == 5) poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0f));

            Matrix4f matrix4f = poseStack.last().pose();
            int y = (int)camera.getEntity().getY();
            if (y >= 146) y = 146;
            if (y <= 52) y = 52;
            int d = (int) (-140 + y * 2.7);
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferBuilder.vertex(matrix4f, -100.0f, -100.0f, -100.0f).uv(0.0f, 0.0f).color(d, d, d, 255).endVertex();
            bufferBuilder.vertex(matrix4f, -100.0f, -100.0f, 100.0f).uv(0.0f, 16.0f).color(d, d, d, 255).endVertex();
            bufferBuilder.vertex(matrix4f, 100.0f, -100.0f, 100.0f).uv(16.0f, 16.0f).color(d, d, d, 255).endVertex();
            bufferBuilder.vertex(matrix4f, 100.0f, -100.0f, -100.0f).uv(16.0f, 0.0f).color(d, d, d, 255).endVertex();
            tesselator.end();
            poseStack.popPose();
        }
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }

}
