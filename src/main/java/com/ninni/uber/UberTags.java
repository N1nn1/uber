package com.ninni.uber;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import static com.ninni.uber.Uber.MOD_ID;

public interface UberTags {
    //fluid tags
    TagKey<Fluid> MANA = TagKey.create(Registries.FLUID, new ResourceLocation(MOD_ID, "mana"));
}
