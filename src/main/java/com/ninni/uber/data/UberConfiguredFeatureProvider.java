package com.ninni.uber.data;

import com.ninni.uber.registry.UberConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class UberConfiguredFeatureProvider extends FabricDynamicRegistryProvider {

    public UberConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, UberConfiguredFeatures.CROWNSTONE_VEGETATION);
        add(registries, entries, UberConfiguredFeatures.PHASMOFUZZ_VEGETATION);
        add(registries, entries, UberConfiguredFeatures.PHASMOFUZZ_SURFACE);
        add(registries, entries, UberConfiguredFeatures.ORE_MEDULESOIL);
        add(registries, entries, UberConfiguredFeatures.LAKE_MANA);
        add(registries, entries, UberConfiguredFeatures.MARROW_TREE);
        add(registries, entries, UberConfiguredFeatures.MARROW_CAGE);
        add(registries, entries, UberConfiguredFeatures.MARROW_RIB);
        add(registries, entries, UberConfiguredFeatures.MARROW_RIB_CIRCLE);
        add(registries, entries, UberConfiguredFeatures.MARROW_DECORATIONS);
        add(registries, entries, UberConfiguredFeatures.CLOTTON_SENTINEL);
        add(registries, entries, UberConfiguredFeatures.ELYSIUM_OASIS);
        add(registries, entries, UberConfiguredFeatures.ELYSIAFUZZ_SURFACE);
        add(registries, entries, UberConfiguredFeatures.MELLOW_ELYSIAFUZZ_SURFACE);
        add(registries, entries, UberConfiguredFeatures.ELYSIAFUZZ_VEGETATION);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature) {
        final HolderLookup.RegistryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistry = registries.lookupOrThrow(Registries.CONFIGURED_FEATURE);

        entries.add(configuredFeature, configuredFeatureRegistry.getOrThrow(configuredFeature).value());
    }

    @Override
    public String getName() {
        return "worldgen/configured_feature";
    }
}
