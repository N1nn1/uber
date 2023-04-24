package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.item.*;
import com.ninni.uber.registry.secondary.UberFluids;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class UberItems {
    //GRYMMOTH
        //dreadstone
    public static final Item DREADSTONE = register("dreadstone", new BlockItem(UberBlocks.DREADSTONE, new FabricItemSettings()));
    public static final Item DREADSTONE_STAIRS = register("dreadstone_stairs", new BlockItem(UberBlocks.DREADSTONE_STAIRS, new FabricItemSettings()));
    public static final Item DREADSTONE_SLAB = register("dreadstone_slab", new BlockItem(UberBlocks.DREADSTONE_SLAB, new FabricItemSettings()));
    public static final Item DREADSTONE_WALL = register("dreadstone_wall", new BlockItem(UberBlocks.DREADSTONE_WALL, new FabricItemSettings()));
    public static final Item CHISELED_DREADSTONE = register("chiseled_dreadstone", new BlockItem(UberBlocks.CHISELED_DREADSTONE, new FabricItemSettings()));
    public static final Item DREADSTONE_PILLAR = register("dreadstone_pillar", new BlockItem(UberBlocks.DREADSTONE_PILLAR, new FabricItemSettings()));
    public static final Item SMOOTH_DREADSTONE = register("smooth_dreadstone", new BlockItem(UberBlocks.SMOOTH_DREADSTONE, new FabricItemSettings()));
    public static final Item SMOOTH_DREADSTONE_SLAB = register("smooth_dreadstone_slab", new BlockItem(UberBlocks.SMOOTH_DREADSTONE_SLAB, new FabricItemSettings()));
    public static final Item DREADSTONE_TILES = register("dreadstone_tiles", new BlockItem(UberBlocks.DREADSTONE_TILES, new FabricItemSettings()));
    public static final Item CRACKED_DREADSTONE_TILES = register("cracked_dreadstone_tiles", new BlockItem(UberBlocks.CRACKED_DREADSTONE_TILES, new FabricItemSettings()));
    public static final Item DREADSTONE_TILE_STAIRS = register("dreadstone_tile_stairs", new BlockItem(UberBlocks.DREADSTONE_TILE_STAIRS, new FabricItemSettings()));
    public static final Item DREADSTONE_TILE_SLAB = register("dreadstone_tile_slab", new BlockItem(UberBlocks.DREADSTONE_TILE_SLAB, new FabricItemSettings()));
    public static final Item DREADSTONE_TILE_WALL = register("dreadstone_tile_wall", new BlockItem(UberBlocks.DREADSTONE_TILE_WALL, new FabricItemSettings()));

    //ECSTACE
        //crownstone
    public static final Item CROWNSTONE = register("crownstone", new BlockItem(UberBlocks.CROWNSTONE, new FabricItemSettings()));
    public static final Item CROWNSTONE_STAIRS = register("crownstone_stairs", new BlockItem(UberBlocks.CROWNSTONE_STAIRS, new FabricItemSettings()));
    public static final Item CROWNSTONE_SLAB = register("crownstone_slab", new BlockItem(UberBlocks.CROWNSTONE_SLAB, new FabricItemSettings()));
    public static final Item CROWNSTONE_WALL = register("crownstone_wall", new BlockItem(UberBlocks.CROWNSTONE_WALL, new FabricItemSettings()));
    public static final Item CROWNSTONE_PILLAR = register("crownstone_pillar", new BlockItem(UberBlocks.CROWNSTONE_PILLAR, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE = register("polished_crownstone", new BlockItem(UberBlocks.POLISHED_CROWNSTONE, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_STAIRS = register("polished_crownstone_stairs", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_STAIRS, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_SLAB = register("polished_crownstone_slab", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_SLAB, new FabricItemSettings()));
    public static final Item CHISELED_POLISHED_CROWNSTONE = register("chiseled_polished_crownstone", new BlockItem(UberBlocks.CHISELED_POLISHED_CROWNSTONE, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_TILES = register("polished_crownstone_tiles", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_TILES, new FabricItemSettings()));
    public static final Item CRACKED_POLISHED_CROWNSTONE_TILES = register("cracked_polished_crownstone_tiles", new BlockItem(UberBlocks.CRACKED_POLISHED_CROWNSTONE_TILES, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_TILE_STAIRS = register("polished_crownstone_tile_stairs", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_TILE_STAIRS, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_TILE_SLAB = register("polished_crownstone_tile_slab", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_TILE_SLAB, new FabricItemSettings()));
    public static final Item POLISHED_CROWNSTONE_TILE_WALL = register("polished_crownstone_tile_wall", new BlockItem(UberBlocks.POLISHED_CROWNSTONE_TILE_WALL, new FabricItemSettings()));

    public static final Item MEDULESOIL = register("medulesoil", new BlockItem(UberBlocks.MEDULESOIL, new FabricItemSettings()));
    public static final Item PHASMOFUZZ = register("phasmofuzz", new BlockItem(UberBlocks.PHASMOFUZZ, new FabricItemSettings()));
    public static final Item PHASMOFOLLICLE = register("phasmofollicle", new BlockItem(UberBlocks.PHASMOFOLLICLE, new FabricItemSettings()));
    public static final Item ISTALKS = register("istalks", new BlockItem(UberBlocks.ISTALKS, new FabricItemSettings()));
    public static final Item TALL_ISTALKS = register("tall_istalks", new DoubleHighBlockItem(UberBlocks.TALL_ISTALKS, new FabricItemSettings()));
    public static final Item MARROW = register("marrow", new BlockItem(UberBlocks.MARROW, new FabricItemSettings()));
    public static final Item MARROW_PLANKS = register("marrow_planks", new BlockItem(UberBlocks.MARROW_PLANKS, new FabricItemSettings()));
    public static final Item MANA_BUCKET = register("mana_bucket", new ManaBucketItem(UberFluids.MANA, new FabricItemSettings().maxCount(1)));
    public static final Item MANA_GEL = register("mana_gel", new BlockItem(UberBlocks.MANA_GEL, new FabricItemSettings()));
    public static final Item HOUND_SPAWN_EGG = register("hound_spawn_egg", new SpawnEggItem(UberEntityTypes.HOUND, 0x43518A, 0x0AB405, new Item.Properties().stacksTo(64)));

    //ECSTACE
    public static final Item MELLOROCK = register("mellorock", new BlockItem(UberBlocks.MELLOROCK, new FabricItemSettings()));
    public static final Item ELYSIAFUZZ = register("elysiafuzz", new BlockItem(UberBlocks.ELYSIAFUZZ, new FabricItemSettings()));
    public static final Item MELLOW_ELYSIAFUZZ = register("mellow_elysiafuzz", new BlockItem(UberBlocks.MELLOW_ELYSIAFUZZ, new FabricItemSettings()));
    public static final Item ELYSIAVELD = register("elysiaveld", new BlockItem(UberBlocks.ELYSIAVELD, new FabricItemSettings()));
    public static final Item CLOTTONBALL = register("clottonball", new BlockItem(UberBlocks.CLOTTONBALL, new FabricItemSettings()));
    public static final Item CLOTTON_BLOCK = register("clotton_block", new BlockItem(UberBlocks.CLOTTON_BLOCK, new FabricItemSettings()));


    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Uber.MOD_ID, id), item);
    }
}
