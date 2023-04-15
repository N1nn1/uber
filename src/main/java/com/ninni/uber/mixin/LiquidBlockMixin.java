package com.ninni.uber.mixin;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.LiquidBlock.POSSIBLE_FLOW_DIRECTIONS;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {
    @Shadow @Final protected FlowingFluid fluid;

    @Shadow protected abstract void fizz(LevelAccessor levelAccessor, BlockPos blockPos);

    @SuppressWarnings("deprecation")
    @Inject(at = @At("TAIL"), method = "shouldSpreadLiquid", cancellable = true)
    private void shouldSpreadToMana(Level level, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        if (this.fluid.is(FluidTags.LAVA)) {
            boolean bl = level.getBlockState(blockPos.below()).is(Blocks.SOUL_SOIL);
            for (Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos2 = blockPos.relative(direction.getOpposite());
                if (level.getFluidState(blockPos2).is(FluidTags.WATER)) {
                    Block block = level.getFluidState(blockPos).isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                    level.setBlockAndUpdate(blockPos, block.defaultBlockState());
                    this.fizz(level, blockPos);
                    cir.setReturnValue(false);
                }
                if (level.getFluidState(blockPos2).is(UberTags.MANA)) {
                    Block block = level.getFluidState(blockPos).isSource() ? UberBlocks.CROWNSTONE : UberBlocks.DREADSTONE;
                    level.setBlockAndUpdate(blockPos, block.defaultBlockState());
                    this.fizz(level, blockPos);
                    cir.setReturnValue(false);
                }
                if (!bl || !level.getBlockState(blockPos2).is(Blocks.BLUE_ICE)) continue;
                level.setBlockAndUpdate(blockPos, Blocks.BASALT.defaultBlockState());
                this.fizz(level, blockPos);
                cir.setReturnValue(false);
            }
        } else if (this.fluid.is(UberTags.MANA)) {
            for (Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos2 = blockPos.relative(direction.getOpposite());
                if (level.getFluidState(blockPos2).is(FluidTags.WATER)) {
                    Block block = level.getFluidState(blockPos).isSource() ? Blocks.OBSIDIAN : UberBlocks.CROWNSTONE;
                    level.setBlockAndUpdate(blockPos, block.defaultBlockState());
                    cir.setReturnValue(false);
                };
                cir.setReturnValue(false);
            }
        }
        cir.setReturnValue(true);
    }
}
