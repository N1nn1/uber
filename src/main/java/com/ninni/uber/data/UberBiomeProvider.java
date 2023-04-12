package com.ninni.uber.data;

import com.ninni.uber.registry.UberBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class UberBiomeProvider extends FabricDynamicRegistryProvider {

    public UberBiomeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, UberBiomes.GRYMMOTH_WASTES);
        add(registries, entries, UberBiomes.ESTACE_WASTES);
        add(registries, entries, UberBiomes.PHASMO_FOREST);
        add(registries, entries, UberBiomes.ELYSIUM_FIELDS);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<Biome> biome) {
        final HolderLookup.RegistryLookup<Biome> biomeRegistry = registries.lookupOrThrow(Registries.BIOME);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/biome";
    }
}
