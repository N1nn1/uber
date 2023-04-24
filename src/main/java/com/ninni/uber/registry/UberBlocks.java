package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.block.*;
import com.ninni.uber.registry.secondary.UberFluids;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

@SuppressWarnings("unused")
public class UberBlocks {

    //GRYMMOTH
        //dreadstone
    public static final Block DREADSTONE = register("dreadstone", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
    public static final Block DREADSTONE_STAIRS = register("dreadstone_stairs", new StairBlock(DREADSTONE.defaultBlockState(), BlockBehaviour.Properties.copy(DREADSTONE)));
    public static final Block DREADSTONE_SLAB = register("dreadstone_slab", new SlabBlock(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block DREADSTONE_WALL = register("dreadstone_wall", new WallBlock(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block CHISELED_DREADSTONE = register("chiseled_dreadstone", new Block(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block DREADSTONE_PILLAR = register("dreadstone_pillar", new RotatedPillarBlock(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block SMOOTH_DREADSTONE = register("smooth_dreadstone", new Block(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block SMOOTH_DREADSTONE_SLAB = register("smooth_dreadstone_slab", new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_DREADSTONE)));
    public static final Block DREADSTONE_TILES = register("dreadstone_tiles", new Block(FabricBlockSettings.copyOf(DREADSTONE)));
    public static final Block CRACKED_DREADSTONE_TILES = register("cracked_dreadstone_tiles", new Block(FabricBlockSettings.copyOf(DREADSTONE_TILES)));
    public static final Block DREADSTONE_TILE_STAIRS = register("dreadstone_tile_stairs", new StairBlock(DREADSTONE_TILES.defaultBlockState(), BlockBehaviour.Properties.copy(DREADSTONE_TILES)));
    public static final Block DREADSTONE_TILE_SLAB = register("dreadstone_tile_slab", new SlabBlock(FabricBlockSettings.copyOf(DREADSTONE_TILES)));
    public static final Block DREADSTONE_TILE_WALL = register("dreadstone_tile_wall", new WallBlock(FabricBlockSettings.copyOf(DREADSTONE_TILES)));

    //ECSTACE
        //crownstone
    public static final Block CROWNSTONE = register("crownstone", new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)));
    public static final Block CROWNSTONE_STAIRS = register("crownstone_stairs", new StairBlock(CROWNSTONE.defaultBlockState(), BlockBehaviour.Properties.copy(CROWNSTONE)));
    public static final Block CROWNSTONE_SLAB = register("crownstone_slab", new SlabBlock(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block CROWNSTONE_WALL = register("crownstone_wall", new WallBlock(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block CROWNSTONE_PILLAR = register("crownstone_pillar", new RotatedPillarBlock(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block POLISHED_CROWNSTONE = register("polished_crownstone", new Block(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block POLISHED_CROWNSTONE_STAIRS = register("polished_crownstone_stairs", new StairBlock(POLISHED_CROWNSTONE.defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_CROWNSTONE)));
    public static final Block POLISHED_CROWNSTONE_SLAB = register("polished_crownstone_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CROWNSTONE)));
    public static final Block CHISELED_POLISHED_CROWNSTONE = register("chiseled_polished_crownstone", new Block(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block POLISHED_CROWNSTONE_TILES = register("polished_crownstone_tiles", new Block(FabricBlockSettings.copyOf(CROWNSTONE)));
    public static final Block CRACKED_POLISHED_CROWNSTONE_TILES = register("cracked_polished_crownstone_tiles", new Block(FabricBlockSettings.copyOf(POLISHED_CROWNSTONE_TILES)));
    public static final Block POLISHED_CROWNSTONE_TILE_STAIRS = register("polished_crownstone_tile_stairs", new StairBlock(POLISHED_CROWNSTONE_TILES.defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_CROWNSTONE_TILES)));
    public static final Block POLISHED_CROWNSTONE_TILE_SLAB = register("polished_crownstone_tile_slab", new SlabBlock(FabricBlockSettings.copyOf(POLISHED_CROWNSTONE_TILES)));
    public static final Block POLISHED_CROWNSTONE_TILE_WALL = register("polished_crownstone_tile_wall", new WallBlock(FabricBlockSettings.copyOf(POLISHED_CROWNSTONE_TILES)));

    public static final Block MEDULESOIL = register("medulesoil", new Block(FabricBlockSettings.copyOf(Blocks.DIRT)));
    public static final Block PHASMOFUZZ = register("phasmofuzz", new FuzzBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK), UberConfiguredFeatures.PHASMOFUZZ_BONEMEAL));
    public static final Block PHASMOFOLLICLE = register("phasmofollicle", new UberVegetationBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS), false));
    public static final Block ISTALKS = register("istalks", new UberVegetalDecoBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS).luminance(blockState -> 3), false));
    public static final Block POTTED_ISTALKS = register("potted_istalks", new FlowerPotBlock(ISTALKS, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final Block TALL_ISTALKS = register("tall_istalks", new UberTallVegetalDecoBlock(FabricBlockSettings.copyOf(Blocks.PEONY).luminance(blockState -> 5), false));
    public static final Block MARROW = register("marrow", new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MARROW_PLANKS = register("marrow_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block MANA = register("mana", new LiquidBlock(UberFluids.MANA, FabricBlockSettings.of(Material.WATER).color(MaterialColor.COLOR_LIGHT_GREEN).noCollission().strength(100.0F).lightLevel(blockState -> 15).noLootTable()));
    public static final Block MANA_GEL = register("mana_gel", new ManaGelBlock(FabricBlockSettings.of(Material.CLAY, MaterialColor.GRASS).sounds(SoundType.HONEY_BLOCK).color(MaterialColor.COLOR_LIGHT_GREEN).noOcclusion().lightLevel(blockState -> 10)));
    public static final Block MANA_CAULDRON = register("mana_cauldron", new ManaCauldronBlock(FabricBlockSettings.copy(Blocks.CAULDRON).lightLevel(ManaCauldronBlock::getLightLevel)));

    //ECSTACE
    public static final Block MELLOROCK = register("mellorock", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block ELYSIAFUZZ = register("elysiafuzz", new FuzzBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK), UberConfiguredFeatures.ELYSIAFUZZ_BONEMEAL));
    public static final Block MELLOW_ELYSIAFUZZ = register("mellow_elysiafuzz", new FuzzBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK), UberConfiguredFeatures.MELLOW_ELYSIAFUZZ_BONEMEAL));
    public static final Block ELYSIAVELD = register("elysiaveld", new UberVegetalDecoBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS), true));
    public static final Block CLOTTONBALL = register("clottonball", new ClottonballBlock(FabricBlockSettings.copyOf(Blocks.NETHER_SPROUTS), true));
    public static final Block CLOTTON_BLOCK = register("clotton_block", new Block(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL)));


    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Uber.MOD_ID, id), block);
    }
}
