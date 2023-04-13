package com.ninni.uber.level.gen.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class UberSurfaceConfig implements FeatureConfiguration {
    public static final Codec<UberSurfaceConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group((TagKey.hashedCodec(Registries.BLOCK).fieldOf("replaceable")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.replaceable), (BlockStateProvider.CODEC.fieldOf("ground_state")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.groundState), (BlockStateProvider.CODEC.fieldOf("soil_state")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.soilState), (PlacedFeature.CODEC.fieldOf("vegetation_feature")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.vegetationFeature), (CaveSurface.CODEC.fieldOf("surface")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.surface), (IntProvider.codec(1, 128).fieldOf("depth")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.depth), (Codec.floatRange(0.0f, 1.0f).fieldOf("extra_bottom_block_chance")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.extraBottomBlockChance), (Codec.intRange(1, 256).fieldOf("vertical_range")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.verticalRange), (Codec.floatRange(0.0f, 1.0f).fieldOf("vegetation_chance")).forGetter(vegetationPatchConfiguration -> Float.valueOf(vegetationPatchConfiguration.vegetationChance)), (IntProvider.CODEC.fieldOf("xz_radius")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.xzRadius), (Codec.floatRange(0.0f, 1.0f).fieldOf("extra_edge_column_chance")).forGetter(vegetationPatchConfiguration -> vegetationPatchConfiguration.extraEdgeColumnChance), Codec.BOOL.fieldOf("place_soil").forGetter(config -> config.placeSoil)).apply(instance, UberSurfaceConfig::new));
    public final TagKey<Block> replaceable;
    public final BlockStateProvider groundState;
    public final BlockStateProvider soilState;
    public final Holder<PlacedFeature> vegetationFeature;
    public final CaveSurface surface;
    public final IntProvider depth;
    public final float extraBottomBlockChance;
    public final int verticalRange;
    public final float vegetationChance;
    public final IntProvider xzRadius;
    public final float extraEdgeColumnChance;
    public final boolean placeSoil;

    public UberSurfaceConfig(TagKey<Block> tagKey, BlockStateProvider blockStateProvider, BlockStateProvider soilState, Holder<PlacedFeature> holder, CaveSurface caveSurface, IntProvider intProvider, float f, int i, float g, IntProvider intProvider2, float h) {
        this(tagKey, blockStateProvider, soilState, holder, caveSurface, intProvider, f, i, g, intProvider2, h, true);
    }

    public UberSurfaceConfig(TagKey<Block> tagKey, BlockStateProvider blockStateProvider, BlockStateProvider soilState, Holder<PlacedFeature> holder, CaveSurface caveSurface, IntProvider intProvider, float f, int i, float g, IntProvider intProvider2, float h, boolean placeSoil) {
        this.replaceable = tagKey;
        this.groundState = blockStateProvider;
        this.soilState = soilState;
        this.vegetationFeature = holder;
        this.surface = caveSurface;
        this.depth = intProvider;
        this.extraBottomBlockChance = f;
        this.verticalRange = i;
        this.vegetationChance = g;
        this.xzRadius = intProvider2;
        this.extraEdgeColumnChance = h;
        this.placeSoil = placeSoil;
    }
}
