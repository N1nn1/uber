package com.ninni.uber.data;

import com.ninni.uber.registry.UberBlocks;
import com.ninni.uber.registry.UberItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.List;

public class UberModelProvider extends FabricModelProvider {

    public UberModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(UberBlocks.CROWNSTONE);
        createFuzzBlock(blockStateModelGenerator, UberBlocks.ELYSIAFUZZ, UberBlocks.CROWNSTONE);
        createFuzzBlock(blockStateModelGenerator, UberBlocks.MELLOW_ELYSIAFUZZ, UberBlocks.ELYSIAFUZZ, UberBlocks.MELLOROCK);
        createFuzzBlock(blockStateModelGenerator, UberBlocks.PHASMOFUZZ, UberBlocks.MEDULESOIL, "_top");
    }

    public final void createFuzzBlock(BlockModelGenerators generators, Block block, Block baseBlock) {
        createFuzzBlock(generators, block, baseBlock, block, "");
    }

    public final void createFuzzBlock(BlockModelGenerators generators, Block block, Block baseBlock, String bottomName) {
        createFuzzBlock(generators, block, baseBlock, block, bottomName);
    }

    public final void createFuzzBlock(BlockModelGenerators generators, Block block, Block topBlock, Block baseBlock) {
        createFuzzBlock(generators, block, baseBlock, topBlock, "");
    }

    public final void createFuzzBlock(BlockModelGenerators generators, Block block, Block baseBlock, Block topBlock, String bottomName) {
        createFuzzBlock(generators, block, baseBlock, bottomName, topBlock, "_top", block, "");
    }

    private void createFuzzBlock(BlockModelGenerators generators, Block block, Block bottomBlock, String bottomName, Block topBlock, String topName, Block sideBlock, String sideName) {
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(bottomBlock, bottomName)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(topBlock, topName)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sideBlock, sideName));
        MultiVariantGenerator simpleBlock = BlockModelGenerators.createSimpleBlock(block, ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, generators.modelOutput));
        generators.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(simpleBlock.getBlock(), ModelLocationUtils.getModelLocation(simpleBlock.getBlock())));
    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(UberItems.MANA_BUCKET, ModelTemplates.FLAT_ITEM);
    }

}
