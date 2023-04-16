package com.ninni.uber.fluid;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import com.ninni.uber.registry.UberParticleTypes;
import com.ninni.uber.registry.UberSoundEvents;
import com.ninni.uber.registry.secondary.UberFluids;
import com.ninni.uber.registry.secondary.UberGameRules;
import com.ninni.uber.registry.UberItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("NullableProblems, deprecation")
public class ManaFluid extends FlowingFluid {
    public ManaFluid() {
    }

    public Fluid getFlowing() {
        return UberFluids.FLOWING_MANA;
    }

    public Fluid getSource() {
        return UberFluids.MANA;
    }

    public Item getBucket() {
        return UberItems.MANA_BUCKET;
    }

    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource randomSource) {
        BlockPos blockPos2 = blockPos.above();
        if (level.getBlockState(blockPos2).isAir() && this.isSource(fluidState) && !level.getBlockState(blockPos2).isSolidRender(level, blockPos2)) {
            if (randomSource.nextInt(20) == 0) {
                double d = (double)blockPos.getX() + randomSource.nextDouble();
                double e = (double)blockPos.getY() + 1.15;
                double f = (double)blockPos.getZ() + randomSource.nextDouble();
                level.addParticle(UberParticleTypes.MANA, d, e, f, 0.0, 0.0, 0.0);
                if (randomSource.nextInt(30) == 0) level.playLocalSound(d, e, f, UberSoundEvents.MANA_POP, SoundSource.BLOCKS, 0.2f + randomSource.nextFloat() * 0.2f, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
            if (randomSource.nextInt(300) == 0) {
                level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomSource.nextInt(300) == 0 ? UberSoundEvents.MANA_GURGLE : UberSoundEvents.MANA_GURGLE_ADDITION, SoundSource.BLOCKS, 0.1f + randomSource.nextFloat() * 0.2f, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
        }
    }


    @Override
    protected boolean canConvertToSource(Level level) {
        return level.getGameRules().getBoolean(UberGameRules.RULE_MANA_SOURCE_CONVERSION);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {

    }
    @Nullable
    public ParticleOptions getDripParticle() {
        return null;
    }

    public int getSlopeFindDistance(LevelReader levelReader) {
        return 3;
    }

    public BlockState createLegacyBlock(FluidState fluidState) {
        return UberBlocks.MANA.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    @Override
    protected void spreadTo(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidState2 = levelAccessor.getFluidState(blockPos);
            if (this.is(UberTags.MANA) && (fluidState2.is(FluidTags.WATER) || fluidState2.is(FluidTags.LAVA))) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    levelAccessor.setBlock(blockPos, UberBlocks.MELLOROCK.defaultBlockState(), 3);
                }
                return;
            }
        }
        super.spreadTo(levelAccessor, blockPos, blockState, direction, fluidState);
    }

    @Override
    public boolean isSource(FluidState fluidState) {
        return false;
    }

    public boolean isSame(Fluid fluid) {
        return fluid == UberFluids.MANA || fluid == UberFluids.FLOWING_MANA;
    }

    public int getDropOff(LevelReader levelReader) {
        return 1;
    }

    @Override
    public int getAmount(FluidState fluidState) {
        return 0;
    }

    public int getTickDelay(LevelReader levelReader) {
        return 5;
    }

    public boolean canBeReplacedWith(FluidState fluidState, BlockGetter blockGetter, BlockPos blockPos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(UberTags.MANA);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(UberSoundEvents.BUCKET_FILL_MANA);
    }

    public static class Flowing extends ManaFluid {
        public Flowing() {
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        public boolean isSource(FluidState fluidState) {
            return false;
        }
    }

    public static class Source extends ManaFluid {
        public Source() {
        }

        public int getAmount(FluidState fluidState) {
            return 8;
        }

        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }
}
