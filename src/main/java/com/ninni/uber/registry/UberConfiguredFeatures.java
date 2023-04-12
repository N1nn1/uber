package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.UberTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class UberConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> PHASMOFUZZ_SURFACE = createKey("phasmofuzz_surface");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PHASMOFUZZ_VEGETATION = createKey("phasmofuzz_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MEDULESOIL = createKey("medulesoil_patch");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
        FeatureUtils.register(bootstapContext, PHASMOFUZZ_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(UberBlocks.PHASMOFOLLICLE.defaultBlockState(), 4).add(UberBlocks.ISTALKS.defaultBlockState(), 2).add(UberBlocks.TALL_ISTALKS.defaultBlockState(), 1))));
        FeatureUtils.register(bootstapContext, PHASMOFUZZ_SURFACE, UberFeatures.GRYMMOTH_SURFACE, new VegetationPatchConfiguration(UberTags.PHASMOFUZZ_REPLACES, BlockStateProvider.simple(UberBlocks.PHASMOFUZZ), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(PHASMOFUZZ_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0f, 5, 0.05F, UniformInt.of(4, 7), 0.3f));
        FeatureUtils.register(bootstapContext, ORE_MEDULESOIL, UberFeatures.SOIL_PATCH, NoneFeatureConfiguration.INSTANCE);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Uber.MOD_ID, string));
    }

}
