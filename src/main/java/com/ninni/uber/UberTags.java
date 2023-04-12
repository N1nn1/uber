package com.ninni.uber;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import static com.ninni.uber.Uber.MOD_ID;

public interface UberTags {
    //block tags
    TagKey<Block> MEDULESOIL = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "medulesoil"));
    TagKey<Block> FUZZ = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "fuzz"));
    TagKey<Block> BASE_STONE_GRYMMOTH = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "base_stone_grymmoth")) ;

    //fluid tags
    TagKey<Fluid> MANA = TagKey.create(Registries.FLUID, new ResourceLocation(MOD_ID, "mana"));
}
