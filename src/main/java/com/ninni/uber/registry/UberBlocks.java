package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.block.*;
import com.ninni.uber.registry.secondary.UberFluids;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;

public class UberBlocks {

    public static final Block DREADSTONE = register("dreadstone", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MEDULESOIL = register("medulesoil", new Block(FabricBlockSettings.copyOf(Blocks.DIRT)));
    public static final Block PHASMOFUZZ = register("phasmofuzz", new Block(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    public static final Block PHASMOFOLLICLE = register("phasmofollicle", new UberVegetationBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS)));
    public static final Block ISTALKS = register("istalks", new UberVegetalDecoBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS).luminance(blockState -> 3)));
    public static final Block TALL_ISTALKS = register("tall_istalks", new UberTallVegetalDecoBlock(FabricBlockSettings.copyOf(Blocks.PEONY).luminance(blockState -> 5)));
    public static final Block MANA = register("mana", new LiquidBlock(UberFluids.MANA, FabricBlockSettings.of(Material.WATER).noCollission().strength(100.0F).lightLevel(blockState -> 15).noLootTable()));

    public static final Block MELLOROCK = register("mellorock", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block CROWNSTONE = register("crownstone", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block ELYSIAFUZZ = register("elysiafuzz", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MELLOW_ELYSIAFUZZ = register("mellow_elysiafuzz", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));


    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Uber.MOD_ID, id), block);
    }
}
