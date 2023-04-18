package com.ninni.uber.block;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class UberVegetationBlock extends TallGrassBlock {
    public boolean isEcstace;

    public UberVegetationBlock(BlockBehaviour.Properties properties, boolean isEcstace) {
        super(properties);
        this.isEcstace = isEcstace;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return this.isEcstace ? blockState.is(UberTags.BASE_BLOCKS_ECSTACE) : blockState.is(UberTags.MEDULESOIL);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return false;
    }
}

