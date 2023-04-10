package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class UberBiomes {

    public static final ResourceKey<Biome> LUMINOUS_WASTELAND = register("luminous_wasteland");

    public static Biome luminousWastelands(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        MobSpawnSettings mobSpawnSettings = (new MobSpawnSettings.Builder()).build();
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2));
        BiomeDefaultFeatures.addDefaultMushrooms(builder);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(
                        (new BiomeSpecialEffects.Builder())
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(0)
                                .skyColor(0)
                                .ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .mobSpawnSettings(mobSpawnSettings)
                .generationSettings(builder.build())
                .build();
    }

    public static void bootstrap(BootstapContext<Biome> biomeRegisterable) {
        biomeRegisterable.register(LUMINOUS_WASTELAND, UberBiomes.luminousWastelands(biomeRegisterable));
    }

    private static ResourceKey<Biome> register(String string) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Uber.MOD_ID, string));
    }

}
