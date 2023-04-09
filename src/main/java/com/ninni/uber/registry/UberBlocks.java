package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;

public class UberBlocks {

    public static final Block DREADSTONE = register("dreadstone", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block CROWNSTONE = register("crownstone", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block MANA = register("mana", new LiquidBlock(UberFluids.MANA, FabricBlockSettings.of(Material.WATER).noCollission().strength(100.0F).lightLevel(blockState -> 15).noLootTable()));

    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Uber.MOD_ID, id), block);
    }
}
