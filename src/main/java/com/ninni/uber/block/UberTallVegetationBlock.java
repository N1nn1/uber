package com.ninni.uber.block;

import com.ninni.uber.UberTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UberTallVegetationBlock extends DoublePlantBlock {
    public boolean isEcstace;
    public UberTallVegetationBlock(Properties properties, boolean isEcstace) {
        super(properties);
        this.isEcstace = isEcstace;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return this.isEcstace ? blockState.is(UberTags.BASE_BLOCKS_ECSTACE) : blockState.is(UberTags.MEDULESOIL);
    }
}

