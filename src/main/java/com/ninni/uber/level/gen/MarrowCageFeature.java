package com.ninni.uber.level.gen;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.HashSet;

public class MarrowCageFeature extends Feature<NoneFeatureConfiguration> {

    public MarrowCageFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();
        HashSet<BlockPos> layerPoses = Sets.newHashSet();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int height = UniformInt.of(2, 4).sample(random);
        if (random.nextInt(15) == 0) {
            height /= 2;
        }
        boolean flag = world.getBlockState(blockPos.below()).is(UberBlocks.PHASMOFUZZ) && world.getBlockState(blockPos).getMaterial().isReplaceable();
        if (!flag) {
            return false;
        } else {
            for (int i = -1; i <= height; i++) {
                BlockPos columnPos = blockPos.above(i);
                if (world.getBlockState(columnPos).getMaterial().isReplaceable() || world.getBlockState(columnPos).is(UberBlocks.PHASMOFUZZ)) {
                    world.setBlock(columnPos, UberBlocks.MARROW.defaultBlockState(), 2);
                    if (i % 2 == 0) {
                        layerPoses.add(columnPos);
                    }
                }
            }
            for (BlockPos pos : layerPoses) {
                this.generateMarrowCrown(world, pos, random, direction);
            }
            return true;
        }
    }

    public void generateMarrowCrown(WorldGenLevel world, BlockPos blockPos, RandomSource random, Direction direction) {
        world.setBlock(blockPos.relative(direction), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
        for (int i = 0; i <= 3; i++) {
            if (i == 3) {
                world.setBlock(blockPos.relative(direction.getClockWise().getClockWise(), 3).relative(direction.getClockWise()), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getClockWise().getAxis()), 2);
                world.setBlock(blockPos.relative(direction.getCounterClockWise().getCounterClockWise(), 3).relative(direction.getCounterClockWise()), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getCounterClockWise().getAxis()), 2);
            } else {
                if (world.getBlockState(blockPos.relative(direction.getClockWise(), i)).getMaterial().isReplaceable()) {
                    world.setBlock(blockPos.relative(direction.getClockWise(), i), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getClockWise().getAxis()), 2);
                    world.setBlock(blockPos.relative(direction.getClockWise(), 2).relative(direction.getClockWise().getClockWise(), i), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getClockWise().getClockWise().getAxis()), 2);
                }
                if (world.getBlockState(blockPos.relative(direction.getCounterClockWise(), i)).getMaterial().isReplaceable()) {
                    world.setBlock(blockPos.relative(direction.getCounterClockWise(), i), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getClockWise().getAxis()), 2);
                    world.setBlock(blockPos.relative(direction.getCounterClockWise(), 2).relative(direction.getCounterClockWise().getCounterClockWise(), i), UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getClockWise().getClockWise().getAxis()), 2);
                }
            }
        }
    }

}
