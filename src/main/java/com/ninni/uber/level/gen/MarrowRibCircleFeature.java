package com.ninni.uber.level.gen;

import com.mojang.serialization.Codec;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class MarrowRibCircleFeature extends Feature<NoneFeatureConfiguration> {

    public MarrowRibCircleFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();
        BlockState belowState = world.getBlockState(blockPos.below());
        boolean flag = (belowState.is(UberBlocks.PHASMOFUZZ) || belowState.is(UberBlocks.MEDULESOIL)) && world.getBlockState(blockPos).getMaterial().isReplaceable();
        if (!flag) {
            return false;
        } else {
            this.generateRibCircle(world, blockPos, random);
            return true;
        }
    }

    private void generateRibCircle(WorldGenLevel world, BlockPos blockPos, RandomSource randomSource) {
        Block marrow = UberBlocks.MARROW;
        int distance = UniformInt.of(3, 8).sample(randomSource);
        boolean flag = true;
        for (int x = -distance; x <= distance; x++) {
            for (int z = -distance; z <= distance; z++) {
                BlockPos checkPos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (x * x + z * z <= distance * distance) {
                    if (world.getBlockState(checkPos.below()).getMaterial().isReplaceable()) {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return;
        }
        for (float i = 0; i < Mth.PI * 2; i+=Math.PI / 4) {
            BlockPos circlePos = blockPos.offset((int) (Mth.sin(i) * distance), 0, (int) (Mth.cos(i) * distance));
            int height = UniformInt.of(2, 5).sample(randomSource);
            for (int t = 0; t <= height; t++) {
                BlockPos placePos = circlePos.above(t);
                if (t == height) {
                    BlockPos offsetPos = placePos.offset((int) -Mth.sin(i), 0, (int) -Mth.cos(i));
                    if (world.getBlockState(offsetPos).getMaterial().isReplaceable()) {
                        world.setBlock(offsetPos, marrow.defaultBlockState(), 2);
                    }
                } else {
                    if (world.getBlockState(placePos).getMaterial().isReplaceable()) {
                        world.setBlock(placePos, marrow.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private void generateMarrowColumn(WorldGenLevel world, BlockPos blockPos, int height, Direction direction) {
        if (world.getBlockState(blockPos.below()).getMaterial().isReplaceable()) {
            this.generateMarrowColumn(world, blockPos.below(), height, direction);
        } else {
            for (int i = 0; i <= height; i++) {
                BlockPos placePos = blockPos.above(i);
                if (i == height) {
                    BlockPos offsetPos = placePos.relative(direction);
                    if (world.getBlockState(offsetPos).getMaterial().isReplaceable()) {
                        world.setBlock(offsetPos, UberBlocks.MARROW.defaultBlockState(), 2);
                    }
                } else {
                    if (world.getBlockState(placePos).getMaterial().isReplaceable()) {
                        world.setBlock(placePos, UberBlocks.MARROW.defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}
