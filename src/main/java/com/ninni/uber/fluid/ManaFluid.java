package com.ninni.uber.fluid;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import com.ninni.uber.registry.secondary.UberFluids;
import com.ninni.uber.registry.secondary.UberGameRules;
import com.ninni.uber.registry.UberItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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

@SuppressWarnings("NullableProblems")
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
        return ParticleTypes.DRIPPING_WATER;
    }

    public int getSlopeFindDistance(LevelReader levelReader) {
        return 3;
    }

    public BlockState createLegacyBlock(FluidState fluidState) {
        return UberBlocks.MANA.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
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

    @SuppressWarnings("deprecation")
    public boolean canBeReplacedWith(FluidState fluidState, BlockGetter blockGetter, BlockPos blockPos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(UberTags.MANA);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
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
