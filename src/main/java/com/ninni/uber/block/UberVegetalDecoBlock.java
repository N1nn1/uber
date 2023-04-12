package com.ninni.uber.block;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UberVegetalDecoBlock extends TallGrassBlock {

    public UberVegetalDecoBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(UberTags.FUZZ);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Block doublePlantBlock = UberBlocks.TALL_ISTALKS;
        if (blockState.is(UberBlocks.ISTALKS) && doublePlantBlock.defaultBlockState().canSurvive(serverLevel, blockPos) && serverLevel.isEmptyBlock(blockPos.above())) {
            UberTallVegetationBlock.placeAt(serverLevel, doublePlantBlock.defaultBlockState(), blockPos, 2);
        }
    }
}
