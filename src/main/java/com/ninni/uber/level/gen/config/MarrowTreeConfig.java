package com.ninni.uber.level.gen.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record MarrowTreeConfig(float funnyChance) implements FeatureConfiguration {
    public static final Codec<MarrowTreeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.FLOAT.fieldOf("funny_chance").forGetter(config -> config.funnyChance)).apply(instance, MarrowTreeConfig::new));
}
