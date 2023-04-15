package com.ninni.uber;

import com.google.common.reflect.Reflection;
import com.ninni.uber.registry.*;
import com.ninni.uber.registry.secondary.UberFluids;
import com.ninni.uber.registry.secondary.UberFogTypes;
import com.ninni.uber.registry.secondary.UberGameRules;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class Uber implements ModInitializer {
	public static final String MOD_ID = "uber";
	public static final ResourceKey<Level> UBER = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(Uber.MOD_ID, "the_uber"));

	@Override
	public void onInitialize() {
		Reflection.initialize(
				UberFeatures.class,
				UberFogTypes.class,
				UberItems.class,
				UberBlocks.class,
				UberFluids.class,
				UberGameRules.class,
				UberSoundEvents.class,
				UberParticleTypes.class,
				UberCreativeModeTab.class
		);
	}
}
