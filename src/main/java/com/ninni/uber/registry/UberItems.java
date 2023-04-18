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

public class UberItems {

    public static final Item DREADSTONE = register("dreadstone", new BlockItem(UberBlocks.DREADSTONE, new FabricItemSettings()));
    public static final Item MEDULESOIL = register("medulesoil", new BlockItem(UberBlocks.MEDULESOIL, new FabricItemSettings()));
    public static final Item PHASMOFUZZ = register("phasmofuzz", new BlockItem(UberBlocks.PHASMOFUZZ, new FabricItemSettings()));
    public static final Item PHASMOFOLLICLE = register("phasmofollicle", new BlockItem(UberBlocks.PHASMOFOLLICLE, new FabricItemSettings()));
    public static final Item ISTALKS = register("istalks", new BlockItem(UberBlocks.ISTALKS, new FabricItemSettings()));
    public static final Item TALL_ISTALKS = register("tall_istalks", new DoubleHighBlockItem(UberBlocks.TALL_ISTALKS, new FabricItemSettings()));
    public static final Item MARROW = register("marrow", new BlockItem(UberBlocks.MARROW, new FabricItemSettings()));
    public static final Item MARROW_PLANKS = register("marrow_planks", new BlockItem(UberBlocks.MARROW_PLANKS, new FabricItemSettings()));
    public static final Item MANA_BUCKET = register("mana_bucket", new ManaBucketItem(UberFluids.MANA, new FabricItemSettings().maxCount(1)));
    public static final Item MANA_GEL = register("mana_gel", new BlockItem(UberBlocks.MANA_GEL, new FabricItemSettings()));

    public static final Item MELLOROCK = register("mellorock", new BlockItem(UberBlocks.MELLOROCK, new FabricItemSettings()));
    public static final Item CROWNSTONE = register("crownstone", new BlockItem(UberBlocks.CROWNSTONE, new FabricItemSettings()));
    public static final Item ELYSIAFUZZ = register("elysiafuzz", new BlockItem(UberBlocks.ELYSIAFUZZ, new FabricItemSettings()));
    public static final Item MELLOW_ELYSIAFUZZ = register("mellow_elysiafuzz", new BlockItem(UberBlocks.MELLOW_ELYSIAFUZZ, new FabricItemSettings()));
    public static final Item ELYSIAVELD = register("elysiaveld", new BlockItem(UberBlocks.ELYSIAVELD, new FabricItemSettings()));
    public static final Item CLOTTONBALL = register("clottonball", new BlockItem(UberBlocks.CLOTTONBALL, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Uber.MOD_ID, id), item);
    }
}
