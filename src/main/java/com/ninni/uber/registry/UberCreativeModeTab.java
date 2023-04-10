package com.ninni.uber.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.uber.Uber.MOD_ID;
import static com.ninni.uber.registry.UberItems.*;

public class UberCreativeModeTab {

    static {
        FabricItemGroup.builder(new ResourceLocation(MOD_ID,"item_group")).icon(DREADSTONE::getDefaultInstance).displayItems((featureFlagSet, output) -> {
                output.accept(DREADSTONE);
                output.accept(CROWNSTONE);
                output.accept(MEDULESOIL);
                output.accept(PHASMOFUZZ);
                output.accept(PHASMOFOLLICLE);
                output.accept(ISTALKS);
                output.accept(TALL_ISTALKS);
                output.accept(MANA_BUCKET);
        }).build();

    }
}
