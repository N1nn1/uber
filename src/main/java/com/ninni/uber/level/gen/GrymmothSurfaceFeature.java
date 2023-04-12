package com.ninni.uber.level.gen;

import com.mojang.serialization.Codec;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class GrymmothSurfaceFeature extends Feature<VegetationPatchConfiguration> {

    public GrymmothSurfaceFeature(Codec<VegetationPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<VegetationPatchConfiguration> featurePlaceContext) {
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        VegetationPatchConfiguration vegetationPatchConfiguration = featurePlaceContext.config();
        RandomSource randomSource = featurePlaceContext.random();
        BlockPos blockPos = featurePlaceContext.origin();
        Predicate<BlockState> predicate = blockState -> blockState.is(vegetationPatchConfiguration.replaceable);
        int i = vegetationPatchConfiguration.xzRadius.sample(randomSource) + 1;
        int j = vegetationPatchConfiguration.xzRadius.sample(randomSource) + 1;
        Set<BlockPos> set = this.placeGroundPatch(worldGenLevel, vegetationPatchConfiguration, randomSource, blockPos, predicate, i, j);
        this.distributeVegetation(featurePlaceContext, worldGenLevel, vegetationPatchConfiguration, randomSource, set, i, j);
        return !set.isEmpty();
    }

    protected Set<BlockPos> placeGroundPatch(WorldGenLevel worldGenLevel, VegetationPatchConfiguration vegetationPatchConfiguration, RandomSource randomSource, BlockPos blockPos, Predicate<BlockState> predicate, int i, int j) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        BlockPos.MutableBlockPos mutableBlockPos2 = mutableBlockPos.mutable();
        Direction direction = vegetationPatchConfiguration.surface.getDirection();
        Direction direction2 = direction.getOpposite();
        HashSet<BlockPos> set = new HashSet<BlockPos>();
        for (int k = -i; k <= i; ++k) {
            boolean bl = k == -i || k == i;
            for (int l = -j; l <= j; ++l) {
                int m;
                boolean bl5;
                boolean bl2 = l == -j || l == j;
                boolean bl3 = bl || bl2;
                boolean bl4 = bl && bl2;
                boolean bl6 = bl5 = bl3 && !bl4;
                if (bl4 || bl5 && (vegetationPatchConfiguration.extraEdgeColumnChance == 0.0f || randomSource.nextFloat() > vegetationPatchConfiguration.extraEdgeColumnChance)) continue;
                mutableBlockPos.setWithOffset(blockPos, k, 0, l);
                for (m = 0; worldGenLevel.isStateAtPosition(mutableBlockPos, BlockBehaviour.BlockStateBase::isAir) && m < vegetationPatchConfiguration.verticalRange; ++m) {
                    mutableBlockPos.move(direction);
                }
                for (m = 0; worldGenLevel.isStateAtPosition(mutableBlockPos, blockState -> !blockState.isAir()) && m < vegetationPatchConfiguration.verticalRange; ++m) {
                    mutableBlockPos.move(direction2);
                }
                mutableBlockPos2.setWithOffset(mutableBlockPos, vegetationPatchConfiguration.surface.getDirection());
                BlockState blockState2 = worldGenLevel.getBlockState(mutableBlockPos2);
                if (!worldGenLevel.isEmptyBlock(mutableBlockPos) || !blockState2.isFaceSturdy(worldGenLevel, mutableBlockPos2, vegetationPatchConfiguration.surface.getDirection().getOpposite())) continue;
                int n = vegetationPatchConfiguration.depth.sample(randomSource) + (vegetationPatchConfiguration.extraBottomBlockChance > 0.0f && randomSource.nextFloat() < vegetationPatchConfiguration.extraBottomBlockChance ? 1 : 0);
                BlockPos blockPos2 = mutableBlockPos2.immutable();
                boolean bl62 = this.placeGround(worldGenLevel, vegetationPatchConfiguration, predicate, randomSource, mutableBlockPos2, n);
                if (!bl62) continue;
                set.add(blockPos2);
            }
        }
        return set;
    }

    protected void distributeVegetation(FeaturePlaceContext<VegetationPatchConfiguration> featurePlaceContext, WorldGenLevel worldGenLevel, VegetationPatchConfiguration vegetationPatchConfiguration, RandomSource randomSource, Set<BlockPos> set, int i, int j) {
        for (BlockPos blockPos : set) {
            if (randomSource.nextFloat() > vegetationPatchConfiguration.vegetationChance) continue;
            this.placeVegetation(worldGenLevel, vegetationPatchConfiguration, featurePlaceContext.chunkGenerator(), randomSource, blockPos);
        }
    }

    protected boolean placeVegetation(WorldGenLevel worldGenLevel, VegetationPatchConfiguration vegetationPatchConfiguration, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos) {
        return vegetationPatchConfiguration.vegetationFeature.value().place(worldGenLevel, chunkGenerator, randomSource, blockPos.relative(vegetationPatchConfiguration.surface.getDirection().getOpposite()));
    }

    protected boolean placeGround(WorldGenLevel worldGenLevel, VegetationPatchConfiguration vegetationPatchConfiguration, Predicate<BlockState> predicate, RandomSource randomSource, BlockPos.MutableBlockPos mutableBlockPos, int i) {
        for (int j = 0; j < i; ++j) {
            BlockState blockState = vegetationPatchConfiguration.groundState.getState(randomSource, mutableBlockPos);
            BlockState blockState2;
            if (blockState.is((blockState2 = worldGenLevel.getBlockState(mutableBlockPos)).getBlock())) continue;
            if (!predicate.test(blockState2)) {
                return j != 0;
            }
            worldGenLevel.setBlock(mutableBlockPos, blockState, 2);
            for (int k = 1; k < 3; k++) {
                if (!predicate.test(worldGenLevel.getBlockState(mutableBlockPos.below(k)))) continue;
                worldGenLevel.setBlock(mutableBlockPos.below(k), UberBlocks.MEDULESOIL.defaultBlockState(), 2);
            }
            mutableBlockPos.move(vegetationPatchConfiguration.surface.getDirection());
        }
        return true;
    }
}
