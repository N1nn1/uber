package com.ninni.uber;

import com.google.common.reflect.Reflection;
import com.ninni.uber.registry.*;
import net.fabricmc.api.ModInitializer;

public class Uber implements ModInitializer {
	public static final String MOD_ID = "uber";

	@Override
	public void onInitialize() {
		Reflection.initialize(
				UberItems.class,
				UberBlocks.class,
				UberFluids.class,
				UberCreativeModeTab.class
		);
	}
}
