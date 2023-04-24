package com.ninni.uber.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import static com.ninni.uber.Uber.MOD_ID;
import static com.ninni.uber.registry.UberItems.*;

public class UberCreativeModeTab {

    static {
        FabricItemGroup.builder(new ResourceLocation(MOD_ID,"item_group")).icon(PHASMOFUZZ::getDefaultInstance).displayItems((featureFlagSet, output) -> {
        //GRYMMOTH
            //dreadstone
            output.accept(DREADSTONE);
            output.accept(DREADSTONE_STAIRS);
            output.accept(DREADSTONE_SLAB);
            output.accept(DREADSTONE_WALL);
            output.accept(CHISELED_DREADSTONE);
            output.accept(DREADSTONE_PILLAR);
            output.accept(SMOOTH_DREADSTONE);
            output.accept(SMOOTH_DREADSTONE_SLAB);
            output.accept(DREADSTONE_TILES);
            output.accept(CRACKED_DREADSTONE_TILES);
            output.accept(DREADSTONE_TILE_STAIRS);
            output.accept(DREADSTONE_TILE_SLAB);
            output.accept(DREADSTONE_TILE_WALL);

            //GRYMMOTH
                //dreadstone
            output.accept(CROWNSTONE);
            output.accept(CROWNSTONE_STAIRS);
            output.accept(CROWNSTONE_SLAB);
            output.accept(CROWNSTONE_WALL);
            output.accept(CROWNSTONE_PILLAR);
            output.accept(POLISHED_CROWNSTONE);
            output.accept(POLISHED_CROWNSTONE_STAIRS);
            output.accept(POLISHED_CROWNSTONE_SLAB);
            output.accept(CHISELED_POLISHED_CROWNSTONE);
            output.accept(POLISHED_CROWNSTONE_TILES);
            output.accept(CRACKED_POLISHED_CROWNSTONE_TILES);
            output.accept(POLISHED_CROWNSTONE_TILE_STAIRS);
            output.accept(POLISHED_CROWNSTONE_TILE_SLAB);
            output.accept(POLISHED_CROWNSTONE_TILE_WALL);

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
            output.accept(ELYSIAFUZZ);
            output.accept(MELLOW_ELYSIAFUZZ);
            output.accept(ELYSIAVELD);
            output.accept(CLOTTONBALL);
            output.accept(CLOTTON_BLOCK);
        }).build();


        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {

            entries.addAfter(Items.PURPUR_SLAB,
                    DREADSTONE,
                    DREADSTONE_STAIRS,
                    DREADSTONE_SLAB,
                    DREADSTONE_WALL,
                    CHISELED_DREADSTONE,
                    DREADSTONE_PILLAR,
                    SMOOTH_DREADSTONE,
                    SMOOTH_DREADSTONE_SLAB,
                    DREADSTONE_TILES,
                    CRACKED_DREADSTONE_TILES,
                    DREADSTONE_TILE_STAIRS,
                    DREADSTONE_TILE_SLAB,
                    DREADSTONE_TILE_WALL,
                    CROWNSTONE,
                    CROWNSTONE_STAIRS,
                    CROWNSTONE_SLAB,
                    CROWNSTONE_WALL,
                    CROWNSTONE_PILLAR,
                    POLISHED_CROWNSTONE,
                    POLISHED_CROWNSTONE_SLAB,
                    POLISHED_CROWNSTONE_SLAB,
                    CHISELED_POLISHED_CROWNSTONE,
                    POLISHED_CROWNSTONE_TILES,
                    CRACKED_POLISHED_CROWNSTONE_TILES,
                    POLISHED_CROWNSTONE_TILE_STAIRS,
                    POLISHED_CROWNSTONE_TILE_SLAB,
                    POLISHED_CROWNSTONE_TILE_WALL
            );
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {

            entries.addAfter(Items.END_STONE,
                    DREADSTONE,
                    CROWNSTONE
            );
        });
    }


}
