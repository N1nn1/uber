package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;

public class UberItems {

    public static final Item DREADSTONE = register("dreadstone", new BlockItem(UberBlocks.DREADSTONE, new FabricItemSettings()));
    public static final Item CROWNSTONE = register("crownstone", new BlockItem(UberBlocks.CROWNSTONE, new FabricItemSettings()));
    public static final Item MANA_BUCKET = register("mana_bucket", new BucketItem(UberFluids.MANA, new FabricItemSettings().maxCount(1)));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Uber.MOD_ID, id), item);
    }
}
