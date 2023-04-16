package com.ninni.uber.item;

import com.ninni.uber.Uber;
import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberParticleTypes;
import com.ninni.uber.registry.UberSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ManaBucketItem extends BucketItem {
    private final Fluid content;

    public ManaBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
        this.content = fluid;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos blockPos, @Nullable BlockHitResult blockHitResult) {
        if (this.content.is(UberTags.MANA) && level.dimension() != Uber.UBER) {
            //TODO custom sounds
            level.playSound(player, blockPos, UberSoundEvents.MANA_DISAPPEAR, SoundSource.BLOCKS, 0.5f, 2.6f + (level.random.nextFloat() - level.random.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                if (!level.getBlockState(blockPos.below()).isAir()) level.addParticle(UberParticleTypes.MANA, (double)blockPos.getX() + Math.random(), (double)blockPos.getY() + 0.3f, (double)blockPos.getZ() + Math.random(), 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.CLOUD, (double)blockPos.getX() + Math.random(), (double)blockPos.getY() + Math.random(), (double)blockPos.getZ() + Math.random(), 0.0, 0.0, 0.0);
            }
            return true;
        }
        return super.emptyContents(player, level, blockPos, blockHitResult);
    }

    @Override
    protected void playEmptySound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos) {
        levelAccessor.playSound(player, blockPos, UberSoundEvents.BUCKET_EMPTY_MANA, SoundSource.BLOCKS, 1.0f, 1.0f);
        levelAccessor.gameEvent(player, GameEvent.FLUID_PLACE, blockPos);
    }
}
