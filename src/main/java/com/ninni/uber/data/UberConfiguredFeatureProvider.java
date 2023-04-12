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
        add(registries, entries, UberConfiguredFeatures.PHASMOFUZZ_VEGETATION);
        add(registries, entries, UberConfiguredFeatures.PHASMOFUZZ_SURFACE);
        add(registries, entries, UberConfiguredFeatures.ORE_MEDULESOIL);
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