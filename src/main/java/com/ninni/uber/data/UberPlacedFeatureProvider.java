package com.ninni.uber.data;

import com.ninni.uber.registry.UberPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class UberPlacedFeatureProvider extends FabricDynamicRegistryProvider {

    public UberPlacedFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, UberPlacedFeatures.PHASMOFUZZ_SURFACE);
        add(registries, entries, UberPlacedFeatures.PHASMOFUZZ_BONEMEAL);
        add(registries, entries, UberPlacedFeatures.ORE_MEDULESOIL);
        add(registries, entries, UberPlacedFeatures.LAKE_MANA);
        add(registries, entries, UberPlacedFeatures.MARROW_TREE);
        add(registries, entries, UberPlacedFeatures.MARROW_CAGE);
        add(registries, entries, UberPlacedFeatures.MARROW_RIB);
        add(registries, entries, UberPlacedFeatures.MARROW_RIB_CIRCLE);
        add(registries, entries, UberPlacedFeatures.MARROW_DECORATIONS);
        add(registries, entries, UberPlacedFeatures.CLOTTON_SENTINEL);
        add(registries, entries, UberPlacedFeatures.ELYSIUM_OASIS);
        add(registries, entries, UberPlacedFeatures.ELYSIAFUZZ_SURFACE);
        add(registries, entries, UberPlacedFeatures.ELYSIAFUZZ_BONEMEAL);
        add(registries, entries, UberPlacedFeatures.CROWNSTONE_VEGETATION);
        add(registries, entries, UberPlacedFeatures.MELLOW_ELYSIAFUZZ_SURFACE);
        add(registries, entries, UberPlacedFeatures.MELLOW_ELYSIAFUZZ_BONEMEAL);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<PlacedFeature> configuredFeature) {
        final HolderLookup.RegistryLookup<PlacedFeature> configuredFeatureRegistry = registries.lookupOrThrow(Registries.PLACED_FEATURE);

        entries.add(configuredFeature, configuredFeatureRegistry.getOrThrow(configuredFeature).value());
    }

    @Override
    public String getName() {
        return "worldgen/placed_feature";
    }
}
