package com.ninni.uber.block;

import com.ninni.uber.UberTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UberTallVegetationBlock extends DoublePlantBlock {
    public UberTallVegetationBlock(Properties properties) {
        super(properties);
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(UberTags.MEDULESOIL);
    }
}

