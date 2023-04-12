package com.ninni.uber.level.gen;

import com.mojang.serialization.Codec;
import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SoilPatchFeature extends Feature<NoneFeatureConfiguration> {

    public SoilPatchFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource randomSource = featurePlaceContext.random();
        if (!world.getBlockState(blockPos).is(UberTags.BASE_BLOCKS_GRYMMOTH)) {
            return false;
        } else {
            int radius = Mth.nextInt(randomSource, 4, 8);
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    for (int y = -radius; y <= radius; y++) {
                        BlockPos pos = blockPos.offset(x, y, z);
                        int size = Mth.nextInt(randomSource, radius / 2, radius);
                        if (x * x + y * y + z * z <= size * size && world.getBlockState(pos).is(UberTags.BASE_BLOCKS_GRYMMOTH)) {
                            world.setBlock(pos, UberBlocks.MEDULESOIL.defaultBlockState(), 2);
                        }
                    }
                }
            }
            return true;
        }
    }
}
