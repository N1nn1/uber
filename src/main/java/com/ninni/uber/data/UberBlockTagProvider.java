package com.ninni.uber.data;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class UberBlockTagProvider extends FabricTagProvider<Block> {

    public UberBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(UberTags.MEDULESOIL).add(UberBlocks.PHASMOFUZZ).add(UberBlocks.MEDULESOIL);
        getOrCreateTagBuilder(UberTags.ECSTACE_FUZZ).add(UberBlocks.ELYSIAFUZZ).add(UberBlocks.MELLOW_ELYSIAFUZZ);
        getOrCreateTagBuilder(UberTags.BASE_BLOCKS_ECSTACE).addTag(UberTags.ECSTACE_FUZZ).add(UberBlocks.CROWNSTONE).add(UberBlocks.MELLOROCK).add(Blocks.CALCITE);
    }
}
