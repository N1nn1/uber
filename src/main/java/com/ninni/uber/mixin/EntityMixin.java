package com.ninni.uber.mixin;

import com.ninni.uber.UberTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public Level level;
    @Shadow public abstract void setDeltaMovement(Vec3 vec3);
    @Shadow public abstract Vec3 getDeltaMovement();
    @Shadow public abstract BlockState getFeetBlockState();
    @Shadow public abstract void resetFallDistance();

    @Shadow public abstract boolean isSpectator();

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void addManaEffects(CallbackInfo ci) {
        if (this.getFeetBlockState().getFluidState().is(UberTags.MANA) && !this.isSpectator()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0f, 0.2f, 0.0f));
            this.resetFallDistance();
        }
    }
}
