package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.registry.secondary.UberFluids;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;

public class UberItems {

    public static final Item DREADSTONE = register("dreadstone", new BlockItem(UberBlocks.DREADSTONE, new FabricItemSettings()));
    public static final Item CROWNSTONE = register("crownstone", new BlockItem(UberBlocks.CROWNSTONE, new FabricItemSettings()));
    public static final Item MEDULESOIL = register("medulesoil", new BlockItem(UberBlocks.MEDULESOIL, new FabricItemSettings()));
    public static final Item PHASMOFUZZ = register("phasmofuzz", new BlockItem(UberBlocks.PHASMOFUZZ, new FabricItemSettings()));
    public static final Item PHASMOFOLLICLE = register("phasmofollicle", new BlockItem(UberBlocks.PHASMOFOLLICLE, new FabricItemSettings()));
    public static final Item ISTALKS = register("istalks", new BlockItem(UberBlocks.ISTALKS, new FabricItemSettings()));
    public static final Item TALL_ISTALKS = register("tall_istalks", new DoubleHighBlockItem(UberBlocks.TALL_ISTALKS, new FabricItemSettings()));
    public static final Item MANA_BUCKET = register("mana_bucket", new BucketItem(UberFluids.MANA, new FabricItemSettings().maxCount(1)));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Uber.MOD_ID, id), item);
    }
}
