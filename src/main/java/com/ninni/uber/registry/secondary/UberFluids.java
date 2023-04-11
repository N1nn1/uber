package com.ninni.uber.registry.secondary;

import com.ninni.uber.fluid.ManaFluid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import static com.ninni.uber.Uber.MOD_ID;

public class UberFluids {

    public static final FlowingFluid FLOWING_MANA = register("flowing_mana", new ManaFluid.Flowing());
    public static final FlowingFluid MANA = register("mana", new ManaFluid.Source());

    private static <T extends Fluid> T register(String string, T fluid) {
        return Registry.register(BuiltInRegistries.FLUID, new ResourceLocation(MOD_ID, string), fluid);
    }
}
