package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.UberTags;
import com.ninni.uber.level.gen.config.UberSurfaceConfig;
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
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class UberConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> PHASMOFUZZ_SURFACE = createKey("phasmofuzz_surface");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PHASMOFUZZ_VEGETATION = createKey("phasmofuzz_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELYSIAFUZZ_SURFACE = createKey("elysiafuzz_surface");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MELLOW_ELYSIAFUZZ_SURFACE = createKey("mellow_elysiafuzz_surface");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELYSIAFUZZ_VEGETATION = createKey("elysiafuzz_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CROWNSTONE_VEGETATION = createKey("crownstone_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MEDULESOIL = createKey("medulesoil_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_MANA = createKey("lake_mana");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARROW_TREE = createKey("marrow_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARROW_CAGE = createKey("marrow_cage");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARROW_RIB = createKey("marrow_rib");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARROW_RIB_CIRCLE = createKey("marrow_rib_circle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARROW_DECORATIONS = createKey("marrow_decorations");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLOTTON_SENTINEL = createKey("clotton_sentinel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELYSIUM_OASIS = createKey("elysium_oasis");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> holderGetter2 = bootstapContext.lookup(Registries.PLACED_FEATURE);
        FeatureUtils.register(bootstapContext, CROWNSTONE_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(UberBlocks.CLOTTONBALL)));
        FeatureUtils.register(bootstapContext, PHASMOFUZZ_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(UberBlocks.PHASMOFOLLICLE.defaultBlockState(), 4).add(UberBlocks.ISTALKS.defaultBlockState(), 2).add(UberBlocks.TALL_ISTALKS.defaultBlockState(), 1))));
        FeatureUtils.register(bootstapContext, ELYSIAFUZZ_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(UberBlocks.ELYSIAVELD.defaultBlockState(), 6).add(UberBlocks.CLOTTONBALL.defaultBlockState(), 1))));
        FeatureUtils.register(bootstapContext, PHASMOFUZZ_SURFACE, UberFeatures.UBER_SURFACE, new UberSurfaceConfig(UberTags.PHASMOFUZZ_REPLACES, BlockStateProvider.simple(UberBlocks.PHASMOFUZZ), BlockStateProvider.simple(UberBlocks.MEDULESOIL), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(PHASMOFUZZ_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0f, 5, 0.05F, UniformInt.of(4, 7), 0.3f));
        FeatureUtils.register(bootstapContext, ELYSIAFUZZ_SURFACE, UberFeatures.UBER_SURFACE, new UberSurfaceConfig(UberTags.ELYSIAFUZZ_REPLACES, BlockStateProvider.simple(UberBlocks.ELYSIAFUZZ), BlockStateProvider.simple(UberBlocks.CROWNSTONE), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(ELYSIAFUZZ_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0f, 5, 0.05F, UniformInt.of(4, 7), 0.3f));
        FeatureUtils.register(bootstapContext, MELLOW_ELYSIAFUZZ_SURFACE, UberFeatures.UBER_SURFACE, new UberSurfaceConfig(UberTags.MELLOW_ELYSIAFUZZ_REPLACES, BlockStateProvider.simple(UberBlocks.MELLOW_ELYSIAFUZZ), BlockStateProvider.simple(UberBlocks.MELLOROCK), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(ELYSIAFUZZ_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0f, 5, 0.05F, UniformInt.of(4, 7), 0.3f));
        FeatureUtils.register(bootstapContext, ORE_MEDULESOIL, UberFeatures.SOIL_PATCH, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, LAKE_MANA, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(UberBlocks.MANA.defaultBlockState()), BlockStateProvider.simple(UberBlocks.DREADSTONE.defaultBlockState())));
        FeatureUtils.register(bootstapContext, MARROW_TREE, UberFeatures.MARROW_TREE, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, MARROW_CAGE, UberFeatures.MARROW_CAGE, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, MARROW_RIB, UberFeatures.MARROW_RIB, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, MARROW_RIB_CIRCLE, UberFeatures.MARROW_RIB_CIRCLE, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, MARROW_DECORATIONS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(holderGetter2.getOrThrow(UberPlacedFeatures.MARROW_CAGE), 0.5F), new WeightedPlacedFeature(holderGetter2.getOrThrow(UberPlacedFeatures.MARROW_RIB), 0.5F), new WeightedPlacedFeature(holderGetter2.getOrThrow(UberPlacedFeatures.MARROW_RIB_CIRCLE), 0.5F)), holderGetter2.getOrThrow(UberPlacedFeatures.MARROW_TREE)));
        FeatureUtils.register(bootstapContext, CLOTTON_SENTINEL, UberFeatures.CLOTTON_SENTINEL, NoneFeatureConfiguration.INSTANCE);
        FeatureUtils.register(bootstapContext, ELYSIUM_OASIS, UberFeatures.ELYSIUM_OASIS, NoneFeatureConfiguration.INSTANCE);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Uber.MOD_ID, string));
    }

}
