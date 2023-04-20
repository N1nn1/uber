package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class UberParticleTypes {

    public static final SimpleParticleType BLOWING_WIND = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "blowing_wind"), FabricParticleTypes.simple());
    public static final SimpleParticleType WIND = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "wind"), FabricParticleTypes.simple());
    public static final SimpleParticleType CALM_WIND = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "calm_wind"), FabricParticleTypes.simple());
    public static final SimpleParticleType MANA = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "mana"), FabricParticleTypes.simple());
    public static final SimpleParticleType MANA_CAULDRON = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "mana_cauldron"), FabricParticleTypes.simple());
    public static final SimpleParticleType CLOTTON = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Uber.MOD_ID, "clotton"), FabricParticleTypes.simple());
}
