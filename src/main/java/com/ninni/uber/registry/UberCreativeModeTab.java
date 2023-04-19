package com.ninni.uber.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.uber.Uber.MOD_ID;
import static com.ninni.uber.registry.UberItems.*;

public class UberCreativeModeTab {

    static {
        FabricItemGroup.builder(new ResourceLocation(MOD_ID,"item_group")).icon(PHASMOFUZZ::getDefaultInstance).displayItems((featureFlagSet, output) -> {
            output.accept(DREADSTONE);
            output.accept(MEDULESOIL);
            output.accept(PHASMOFUZZ);
            output.accept(PHASMOFOLLICLE);
            output.accept(ISTALKS);
            output.accept(TALL_ISTALKS);
            output.accept(MARROW);
            output.accept(MARROW_PLANKS);
            output.accept(MANA_BUCKET);
            output.accept(MANA_GEL);
            output.accept(HOUND_SPAWN_EGG);

            output.accept(MELLOROCK);
            output.accept(CROWNSTONE);
            output.accept(ELYSIAFUZZ);
            output.accept(MELLOW_ELYSIAFUZZ);
            output.accept(ELYSIAVELD);
            output.accept(CLOTTONBALL);
            output.accept(CLOTTON_BLOCK);
        }).build();

    }
}
