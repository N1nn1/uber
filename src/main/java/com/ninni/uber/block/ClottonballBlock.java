package com.ninni.uber.block;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ClottonballBlock extends UberVegetationBlock{
    public ClottonballBlock(Properties properties, boolean isEcstace) {
        super(properties, isEcstace);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if (randomSource.nextInt(20) == 0) {
            double d = (double)blockPos.getX() + randomSource.nextDouble();
            double e = (double)blockPos.getY() + 0.8;
            double f = (double)blockPos.getZ() + randomSource.nextDouble();
            level.addParticle(ParticleTypes.CLOUD, d, e, f, randomSource.nextFloat() * 0.15, 0.05, randomSource.nextFloat() * 0.15);
            //TODO custom particle
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(UberBlocks.CLOTTON_BLOCK) || blockState.is(UberTags.BASE_BLOCKS_ECSTACE);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        serverLevel.setBlock(blockPos, UberBlocks.CLOTTON_BLOCK.defaultBlockState(), 2);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return randomSource.nextInt(5) == 0;
    }
}
