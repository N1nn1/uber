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
    TagKey<Block> PHASMOFUZZ_REPLACES = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "phasmofuzz_replaces")) ;
    TagKey<Block> ELYSIAFUZZ_REPLACES = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "elysiafuzz_replaces")) ;
    TagKey<Block> MELLOW_ELYSIAFUZZ_REPLACES = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "mellow_elysiafuzz_replaces")) ;
    TagKey<Block> BASE_BLOCKS_GRYMMOTH = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "base_blocks_grymmoth")) ;
    TagKey<Block> INFINIBURN_UBER = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "infiniburn_uber")) ;

    //fluid tags
    TagKey<Fluid> MANA = TagKey.create(Registries.FLUID, new ResourceLocation(MOD_ID, "mana"));
}
