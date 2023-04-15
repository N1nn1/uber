package com.ninni.uber.mixin.client;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.secondary.UberFogTypes;
import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow private Vec3 position;
    @Shadow public abstract Camera.NearPlane getNearPlane();
    @Shadow private BlockGetter level;

    @Inject(at = @At("RETURN"), method = "getFluidInCamera", cancellable = true)
    private void manaFogColor(CallbackInfoReturnable<FogType> cir) {
        if (this.level != null) {
            Camera.NearPlane nearPlane = this.getNearPlane();
            List<Vec3> list = Arrays.asList(nearPlane.getTopLeft().subtract(nearPlane.getBottomRight()), nearPlane.getTopLeft(), nearPlane.getTopRight(), nearPlane.getBottomLeft(), nearPlane.getBottomRight());
            for (Vec3 vec3 : list) {
                Vec3 vec32 = this.position.add(vec3);
                BlockPos blockPos = BlockPos.containing(vec32);
                FluidState fluidState2 = this.level.getFluidState(blockPos);
                if (fluidState2.is(UberTags.MANA)) {
                    if (!(vec32.y <= (double) (fluidState2.getHeight(this.level, blockPos) + (float) blockPos.getY()))) continue;
                    cir.setReturnValue(UberFogTypes.MANA.get());
                }
            }
        }
    }
}
