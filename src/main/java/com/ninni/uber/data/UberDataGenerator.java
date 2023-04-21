package com.ninni.uber.data;

import com.ninni.uber.registry.UberBiomes;
import com.ninni.uber.registry.UberConfiguredFeatures;
import com.ninni.uber.registry.UberPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class UberDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(UberModelProvider::new);
        pack.addProvider(UberRecipeProvider::new);
        pack.addProvider(UberBlockTagProvider::new);
        pack.addProvider(UberBiomeProvider::new);
        pack.addProvider(UberConfiguredFeatureProvider::new);
        pack.addProvider(UberPlacedFeatureProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, UberBiomes::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, UberConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, UberPlacedFeatures::bootstrap);
    }
}
