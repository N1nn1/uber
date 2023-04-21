package com.ninni.uber.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class UberRecipeProvider extends FabricRecipeProvider {

    protected UberRecipeProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
    }

}
