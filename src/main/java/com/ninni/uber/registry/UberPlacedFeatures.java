package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class UberPlacedFeatures {

    public static final ResourceKey<PlacedFeature> PHASMOFUZZ_SURFACE = createKey("phasmofuzz_surface");
    public static final ResourceKey<PlacedFeature> ELYSIAFUZZ_SURFACE = createKey("elysiafuzz_surface");
    public static final ResourceKey<PlacedFeature> MELLOW_ELYSIAFUZZ_SURFACE = createKey("mellow_elysiafuzz_surface");
    public static final ResourceKey<PlacedFeature> ORE_MEDULESOIL = createKey("ore_medulesoil");
    public static final ResourceKey<PlacedFeature> LAKE_MANA = createKey("lake_mana");

    public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
        Holder.Reference<ConfiguredFeature<?, ?>> holder = holderGetter.getOrThrow(UberConfiguredFeatures.PHASMOFUZZ_SURFACE);
        Holder.Reference<ConfiguredFeature<?, ?>> holder1 = holderGetter.getOrThrow(UberConfiguredFeatures.ORE_MEDULESOIL);
        Holder.Reference<ConfiguredFeature<?, ?>> holder2 = holderGetter.getOrThrow(UberConfiguredFeatures.LAKE_MANA);
        Holder.Reference<ConfiguredFeature<?, ?>> holder3 = holderGetter.getOrThrow(UberConfiguredFeatures.ELYSIAFUZZ_SURFACE);
        Holder.Reference<ConfiguredFeature<?, ?>> holder4 = holderGetter.getOrThrow(UberConfiguredFeatures.MELLOW_ELYSIAFUZZ_SURFACE);
        PlacementUtils.register(bootstapContext, PHASMOFUZZ_SURFACE, holder, CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        PlacementUtils.register(bootstapContext, ELYSIAFUZZ_SURFACE, holder3, CountPlacement.of(UniformInt.of(30, 50)), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        PlacementUtils.register(bootstapContext, MELLOW_ELYSIAFUZZ_SURFACE, holder4, CountPlacement.of(UniformInt.of(30, 50)), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        PlacementUtils.register(bootstapContext, ORE_MEDULESOIL, holder1, CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
        PlacementUtils.register(bootstapContext, LAKE_MANA, holder2, CountPlacement.of(3), RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier2) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> createKey(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Uber.MOD_ID, string));
    }

}
