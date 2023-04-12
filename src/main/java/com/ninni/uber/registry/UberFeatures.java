package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.level.gen.GrymmothSurfaceFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

public class UberFeatures {

    public static final Feature<VegetationPatchConfiguration> GRYMMOTH_SURFACE = register("grymmoth_surface", new GrymmothSurfaceFeature(VegetationPatchConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String string, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Uber.MOD_ID, string), feature);
    }

}
