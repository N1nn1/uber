package com.ninni.uber.level.gen;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.HashSet;

public class ClottonSentinelFeature extends Feature<NoneFeatureConfiguration> {

    public ClottonSentinelFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();
        HashSet<BlockPos> clottonPoses = Sets.newHashSet();
        int radius = Mth.nextInt(random, 4, 8);
        int height = Mth.nextInt(random, 12, 24);
        if (this.canGenerate(world, blockPos)) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    for (int y = 0; y <= height; y++) {
                        BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                        if (world.getBlockState(pos).isAir()) {
                            if (x * x + Mth.sin(y) * (radius / (Mth.nextDouble(random, 1, 2))) + z * z < radius) {
                                world.setBlock(pos, UberBlocks.CLOTTON_BLOCK.defaultBlockState(), 2);
                                clottonPoses.add(pos.above());
                            }
                        }
                    }
                }
            }
            for (BlockPos pos : clottonPoses) {
                if (world.getBlockState(pos).isAir() && random.nextInt(5) == 0) {
                    world.setBlock(pos, UberBlocks.CLOTTONBALL.defaultBlockState(), 2);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean canGenerate(WorldGenLevel world, BlockPos blockPos) {
        boolean flag = true;
        for (Direction direction : Direction.values()) {
            if (!world.getBlockState(blockPos.relative(direction)).isAir() && direction != Direction.DOWN) {
                flag = false;
            }
        }
        return flag && world.getBlockState(blockPos.below()).is(UberTags.BASE_BLOCKS_ECSTACE);
    }
}
