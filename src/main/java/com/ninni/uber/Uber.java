package com.ninni.uber;

import com.google.common.reflect.Reflection;
import com.ninni.uber.registry.*;
import com.ninni.uber.registry.secondary.UberFluids;
import com.ninni.uber.registry.secondary.UberGameRules;
import net.fabricmc.api.ModInitializer;

public class Uber implements ModInitializer {
	public static final String MOD_ID = "uber";

	@Override
	public void onInitialize() {
		Reflection.initialize(
				UberFeatures.class,
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
