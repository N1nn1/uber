package com.ninni.uber.level.gen;

import com.mojang.serialization.Codec;
import com.ninni.uber.UberTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ElysiumOasisFeature extends Feature<NoneFeatureConfiguration> {

    public ElysiumOasisFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();
        int radius = 8;
        boolean flag = world.getBlockState(blockPos.below()).is(UberTags.BASE_BLOCKS_ECSTACE) && world.getBlockState(blockPos).isAir();
        NormalNoise noise = NormalNoise.create(random, -3, 1.0);
        if (flag) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() - 1, blockPos.getZ() + z);
                    if (world.getBlockState(pos).is(UberTags.BASE_BLOCKS_ECSTACE)) {
                        boolean shouldPlace = true;
                        for (Direction direction : Direction.Plane.HORIZONTAL) {
                            BlockState relativeState = world.getBlockState(pos.relative(direction));
                            if (relativeState.getMaterial().isReplaceable() && !relativeState.getMaterial().isLiquid()) {
                                shouldPlace = false;
                            }
                        }
                        double value = 2 * (noise.getValue(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z) + 1);
                        if (x * x + z * z <= (radius * radius) / value && shouldPlace && world.getBlockState(pos.above()).isAir() && world.getBlockState(pos.below()).is(UberTags.BASE_BLOCKS_ECSTACE)) {
                            world.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
