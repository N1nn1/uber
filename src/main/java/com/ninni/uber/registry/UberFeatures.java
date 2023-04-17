package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.level.gen.MarrowCageFeature;
import com.ninni.uber.level.gen.MarrowRibCircleFeature;
import com.ninni.uber.level.gen.MarrowRibFeature;
import com.ninni.uber.level.gen.MarrowTreeFeature;
import com.ninni.uber.level.gen.UberSurfaceFeature;
import com.ninni.uber.level.gen.SoilPatchFeature;
import com.ninni.uber.level.gen.config.UberSurfaceConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class UberFeatures {

    public static final Feature<UberSurfaceConfig> GRYMMOTH_SURFACE = register("grymmoth_surface", new UberSurfaceFeature(UberSurfaceConfig.CODEC));
    public static final Feature<NoneFeatureConfiguration> SOIL_PATCH = register("soil_patch", new SoilPatchFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MARROW_TREE = register("marrow_tree", new MarrowTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MARROW_CAGE = register("marrow_cage", new MarrowCageFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MARROW_RIB = register("marrow_rib", new MarrowRibFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MARROW_RIB_CIRCLE = register("marrow_rib_circle", new MarrowRibCircleFeature(NoneFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String string, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Uber.MOD_ID, string), feature);
    }

}
