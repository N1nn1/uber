package com.ninni.uber.level.gen;

import com.mojang.serialization.Codec;
import com.ninni.uber.level.gen.config.MarrowTreeConfig;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class MarrowTreeFeature extends Feature<MarrowTreeConfig> {

    public MarrowTreeFeature(Codec<MarrowTreeConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MarrowTreeConfig> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        RandomSource random = featurePlaceContext.random();
        int height = Mth.nextInt(random, 6, 10);
        float chance = featurePlaceContext.config().funnyChance();
        BlockPos blockPos = featurePlaceContext.origin().above(height / 2);
        BlockPos basePos = featurePlaceContext.origin().below();
        BlockState belowState = world.getBlockState(basePos);
        boolean flag = belowState.is(UberBlocks.PHASMOFUZZ) || belowState.is(UberBlocks.MEDULESOIL);
        boolean initialFlag = flag && this.canGenerate(world, basePos) && world.getBlockState(blockPos.above(height)).isAir();
        if (!initialFlag) {
            return false;
        } else {
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            Direction randomDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            for (int y = 0; y < height; y++) {
                if (y == height / 2) {
                    world.setBlock(mutableBlockPos, UberBlocks.MARROW.defaultBlockState(), 2);
                    mutableBlockPos.move(randomDirection);
                }
                if (!world.getBlockState(mutableBlockPos).getMaterial().isReplaceable()) continue;
                world.setBlock(mutableBlockPos, UberBlocks.MARROW.defaultBlockState(), 2);
                mutableBlockPos.move(Direction.UP);
                if (y == height - 1) {
                    this.generateHand(world, mutableBlockPos, randomDirection, random, chance);
                }
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                this.placeRoot(world, blockPos.relative(direction), random, direction, 0);
            }
            return true;
        }
    }

    private boolean canGenerate(WorldGenLevel world, BlockPos blockPos) {
        int range = 4;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (world.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWaterOrLava)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean placeRoot(WorldGenLevel world, BlockPos blockPos, RandomSource random, Direction direction, int tries) {
        Block block = UberBlocks.MARROW;
        int maxTries = 5;
        if (tries == maxTries) {
            return this.repeatPlace(world, blockPos, block);
        }
        BlockPos finalPos;
        Direction.Axis axis = direction.getAxis();
        if (random.nextFloat() < 0.4F) {
            finalPos = blockPos.below();
            axis = Direction.Axis.Y;
        } else {
            finalPos = tries == 0 ? blockPos : blockPos.relative(direction);
        }
        if (tries < maxTries && world.getBlockState(finalPos).getMaterial().isReplaceable()) {
            world.setBlock(finalPos, block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis), 2);
            tries++;
            return placeRoot(world, finalPos, random, direction, tries);
        } else {
            return true;
        }
    }

    public boolean repeatPlace(WorldGenLevel world, BlockPos blockPos, Block block) {
        BlockPos belowPos = blockPos.below();
        if (world.getBlockState(belowPos).getMaterial().isReplaceable()) {
            world.setBlock(belowPos, block.defaultBlockState(), 2);
            return this.repeatPlace(world, belowPos, block);
        } else {
            return true;
        }
    }

    public void generateHand(WorldGenLevel world, BlockPos blockPos, Direction offsetDirection, RandomSource random, float chance) {
        int range = 1;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                BlockPos baseHand = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                BlockState bone = UberBlocks.MARROW.defaultBlockState().setValue(RotatedPillarBlock.AXIS, offsetDirection.getAxis());
                world.setBlock(baseHand, bone, 2);
                if (x == 0 || z == 0 && world.getBlockState(baseHand.relative(offsetDirection.getOpposite())).isAir()) {
                    world.setBlock(blockPos.relative(offsetDirection.getOpposite(), 2), bone, 2);
                    if (chance > 0.0F && random.nextFloat() < chance) {
                        this.generateFunnyHand(world, blockPos, offsetDirection, bone);
                    } else {
                        this.generateMiddleFinger(world, blockPos, offsetDirection, bone, false);
                        this.generateThumb(world, blockPos, offsetDirection, false);
                        this.generateRemainings(world, blockPos, offsetDirection, bone);
                    }
                }
            }
        }
    }

    private void generateFunnyHand(WorldGenLevel world, BlockPos blockPos, Direction offsetDirection, BlockState bone) {
        this.generateMiddleFinger(world, blockPos, offsetDirection, bone, true);
        for (int i = 1; i <= 4; i++) {
            Direction[] directions = new Direction[]{offsetDirection.getClockWise(), offsetDirection.getCounterClockWise()};
            for (Direction direction : directions) {
                BlockPos fingerPos = blockPos.relative(offsetDirection.getOpposite(), 3).relative(direction, 2).above(i);
                int threshold = 1;
                if (i > threshold) {
                    world.setBlock(blockPos.relative(offsetDirection.getOpposite(), 3).relative(direction, 2).above(threshold + 1).relative(offsetDirection, i - threshold - 1), bone.setValue(RotatedPillarBlock.AXIS, offsetDirection.getAxis()), 2);
                    if (i == 4) {
                        continue;
                    }
                    world.setBlock(blockPos.relative(offsetDirection.getOpposite(), i - 1).relative(direction, 2), bone.setValue(RotatedPillarBlock.AXIS, offsetDirection.getAxis()), 2);
                } else {
                    world.setBlock(fingerPos, UberBlocks.MARROW.defaultBlockState(), 2);
                }
            }
        }
        this.generateThumb(world, blockPos, offsetDirection, true);
    }

    private void generateRemainings(WorldGenLevel world, BlockPos blockPos, Direction offsetDirection, BlockState bone) {
        for (int i = 1; i <= 3; i++) {
            BlockPos fingerPos = blockPos.relative(offsetDirection.getOpposite(), 3).relative(offsetDirection.getCounterClockWise(), 2).above(i);
            world.setBlock(fingerPos, UberBlocks.MARROW.defaultBlockState(), 2);
            if (i <= 2) {
                world.setBlock(blockPos.relative(offsetDirection.getOpposite(), i).relative(offsetDirection.getCounterClockWise(), 2), bone, 2);
            }
            if (i == 3) {
                world.setBlock(blockPos.relative(offsetDirection.getOpposite(), 2).relative(offsetDirection.getCounterClockWise(), 2).above(3), bone, 2);
            }
            world.setBlock(blockPos.relative(offsetDirection, i - 3).relative(offsetDirection.getClockWise(), 2), bone, 2);
            BlockPos secondaryFingerPos = blockPos.relative(offsetDirection.getOpposite(), 3).relative(offsetDirection.getClockWise(), 2).above(i);
            world.setBlock(secondaryFingerPos, UberBlocks.MARROW.defaultBlockState(), 2);
            if (i == 3) {
                world.setBlock(blockPos.relative(offsetDirection.getOpposite(), 2).relative(offsetDirection.getClockWise(), 2).above(3), bone, 2);
            }
        }
    }

    private void generateMiddleFinger(WorldGenLevel world, BlockPos blockPos, Direction offsetDirection, BlockState bone, boolean funnyFinger) {
        int length = 4;
        if (funnyFinger) {
            length += 1;
        }
        for (int i = 1; i <= length; i++) {
            BlockPos fingerPos = blockPos.relative(offsetDirection.getOpposite(), 3).above(i);
            world.setBlock(fingerPos, UberBlocks.MARROW.defaultBlockState(), 2);
            if (i == 4 && !funnyFinger) {
                world.setBlock(blockPos.relative(offsetDirection.getOpposite(), 2).above(4), bone, 2);
            }
        }
    }

    private void generateThumb(WorldGenLevel world, BlockPos blockPos, Direction offsetDirection, boolean funnyFinger) {
        for (int index = 0; index < 3; index++) {
            world.setBlock(blockPos.relative(offsetDirection).relative(offsetDirection.getCounterClockWise(), 2).above(index), UberBlocks.MARROW.defaultBlockState(), 2);
            world.setBlock(blockPos.relative(offsetDirection).relative(offsetDirection.getCounterClockWise(), 2).above(index), UberBlocks.MARROW.defaultBlockState(), 2);
        }
    }

}
