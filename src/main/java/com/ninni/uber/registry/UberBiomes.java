package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class UberBiomes {

    public static final ResourceKey<Biome> GRYMMOTH_WASTES = register("grymmoth_wastes");
    public static final ResourceKey<Biome> ESTACE_WASTES = register("estace_wastes");
    public static final ResourceKey<Biome> PHASMO_FOREST = register("phasmo_forest");
    public static final ResourceKey<Biome> ELYSIUM_FIELDS = register("elysium_fields");

    public static Biome estaceWastes(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        MobSpawnSettings mobSpawnSettings = (new MobSpawnSettings.Builder()).build();
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2));

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
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .mobSpawnSettings(mobSpawnSettings)
                .generationSettings(builder.build())
                .build();
    }

    public static Biome elysiumFields(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        MobSpawnSettings mobSpawnSettings = (new MobSpawnSettings.Builder()).build();
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2));

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
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.CRIMSON_SPORE, 0.01428f))
                                .ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .mobSpawnSettings(mobSpawnSettings)
                .generationSettings(builder.build())
                .build();
    }

    public static Biome grymmothWastes(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        MobSpawnSettings mobSpawnSettings = (new MobSpawnSettings.Builder()).build();
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2));

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
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .mobSpawnSettings(mobSpawnSettings)
                .generationSettings(builder.build())
                .build();
    }

    public static Biome phasmoForest(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2));

        MobSpawnSettings mobSpawnSettings =
                (new MobSpawnSettings.Builder())
                        //.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 1, 1, 1))
                        .build();
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
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.WARPED_SPORE, 0.01428f))
                                .ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .generationSettings(builder
                        .build())

                .mobSpawnSettings(mobSpawnSettings)
                .build();
    }

    public static void bootstrap(BootstapContext<Biome> biomeRegisterable) {
        biomeRegisterable.register(ESTACE_WASTES, UberBiomes.estaceWastes(biomeRegisterable));
        biomeRegisterable.register(GRYMMOTH_WASTES, UberBiomes.grymmothWastes(biomeRegisterable));
        biomeRegisterable.register(PHASMO_FOREST, UberBiomes.phasmoForest(biomeRegisterable));
        biomeRegisterable.register(ELYSIUM_FIELDS, UberBiomes.elysiumFields(biomeRegisterable));
    }

    private static ResourceKey<Biome> register(String string) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Uber.MOD_ID, string));
    }

}
