package com.ninni.uber.mixin.client;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {

    @Shadow public abstract void addParticle(ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i);

    @Inject(at = @At("HEAD"), method = "doAnimateTick")
    private void addWindEffects(int i, int j, int k, int l, RandomSource randomSource, Block block, BlockPos.MutableBlockPos mutableBlockPos, CallbackInfo ci) {
        ClientLevel $this = (ClientLevel) (Object) this;
        int m = i + $this.random.nextInt(l) - $this.random.nextInt(l);
        int n = j + $this.random.nextInt(l) - $this.random.nextInt(l);
        int o = k + $this.random.nextInt(l) - $this.random.nextInt(l);
        mutableBlockPos.set(m, n, o);
        BlockState blockState = $this.getBlockState(mutableBlockPos);

        if (!blockState.isCollisionShapeFullBlock($this, mutableBlockPos) && $this.getBiome(mutableBlockPos).is(UberTags.ECSTACE)) {
            if (randomSource.nextFloat() <= 0.00006f) this.addParticle(UberParticleTypes.CALM_WIND, (double)mutableBlockPos.getX() + $this.random.nextDouble(), (double)mutableBlockPos.getY() + $this.random.nextDouble(), (double)mutableBlockPos.getZ() + $this.random.nextDouble(), 0, 0, 0);
            if (randomSource.nextFloat() <= 0.00005f) this.addParticle(UberParticleTypes.WIND, (double)mutableBlockPos.getX() + $this.random.nextDouble(), (double)mutableBlockPos.getY() + $this.random.nextDouble(), (double)mutableBlockPos.getZ() + $this.random.nextDouble(), 0, 0, 0);
            if (randomSource.nextFloat() <= 0.00004f) this.addParticle(UberParticleTypes.BLOWING_WIND, (double)mutableBlockPos.getX() + $this.random.nextDouble(), (double)mutableBlockPos.getY() + $this.random.nextDouble(), (double)mutableBlockPos.getZ() + $this.random.nextDouble(), 0, 0, 0);
        }
    }
}
