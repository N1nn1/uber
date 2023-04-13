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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class UberBiomes {

    public static final ResourceKey<Biome> GRYMMOTH_WASTES = register("grymmoth_wastes");
    public static final ResourceKey<Biome> ECSTACE_WASTES = register("ecstace_wastes");
    public static final ResourceKey<Biome> PHASMO_FOREST = register("phasmo_forest");
    public static final ResourceKey<Biome> ELYSIUM_FIELDS = register("elysium_fields");

    public static Biome ecstaceWastes(BootstapContext<Biome> bootstapContext) {
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
                                .fogColor(6249561)
                                .skyColor(6249561)
                                .ambientLoopSound(UberSoundEvents.AMBIENT_UBER_ECSTACE_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_ECSTACE, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(UberSoundEvents.AMBIENT_UBER_ECSTACE_ADDITIONS, 0.0111))
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
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UberPlacedFeatures.ELYSIAFUZZ_SURFACE).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UberPlacedFeatures.MELLOW_ELYSIAFUZZ_SURFACE));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(
                        (new BiomeSpecialEffects.Builder())
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(4662533)
                                .skyColor(4662533)
                                //.ambientParticle(new AmbientParticleSettings(ParticleTypes.CRIMSON_SPORE, 0.01428f))
                                .ambientLoopSound(UberSoundEvents.AMBIENT_UBER_ELYSIUM_FIELDS_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_ECSTACE, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(UberSoundEvents.AMBIENT_UBER_ELYSIUM_FIELDS_ADDITIONS, 0.0111))
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
                                .fogColor(2764353)
                                .skyColor(2764353)
                                .ambientLoopSound(UberSoundEvents.AMBIENT_UBER_GRYMMOTH_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH_ADDITIONS, 0.0111))
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
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder(holderGetter, holderGetter2).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UberPlacedFeatures.PHASMOFUZZ_SURFACE).addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, UberPlacedFeatures.ORE_MEDULESOIL).addFeature(GenerationStep.Decoration.LAKES, UberPlacedFeatures.LAKE_MANA).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UberPlacedFeatures.MARROW_TREE));

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
                                .fogColor(1846397)
                                .skyColor(1846397)
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.WARPED_SPORE, 0.01428f))
                                .ambientLoopSound(UberSoundEvents.AMBIENT_UBER_PHASMO_FOREST_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(UberSoundEvents.AMBIENT_UBER_GRYMMOTH, 6000, 8, 2.0))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(UberSoundEvents.AMBIENT_UBER_PHASMO_FOREST_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                                .build()
                )
                .generationSettings(builder
                        .build())

                .mobSpawnSettings(mobSpawnSettings)
                .build();
    }

    public static void bootstrap(BootstapContext<Biome> biomeRegisterable) {
        biomeRegisterable.register(ECSTACE_WASTES, UberBiomes.ecstaceWastes(biomeRegisterable));
        biomeRegisterable.register(GRYMMOTH_WASTES, UberBiomes.grymmothWastes(biomeRegisterable));
        biomeRegisterable.register(PHASMO_FOREST, UberBiomes.phasmoForest(biomeRegisterable));
        biomeRegisterable.register(ELYSIUM_FIELDS, UberBiomes.elysiumFields(biomeRegisterable));
    }

    private static ResourceKey<Biome> register(String string) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Uber.MOD_ID, string));
    }

}
