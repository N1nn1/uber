package com.ninni.uber.client.renderer.entity;

import com.ninni.uber.client.model.entity.HoundModel;
import com.ninni.uber.client.model.entity.UberEntityModelLayers;
import com.ninni.uber.client.renderer.entity.layers.HoundEyeLayer;
import com.ninni.uber.entity.Hound;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.uber.Uber.MOD_ID;

@Environment(value= EnvType.CLIENT)
public class HoundRenderer extends MobRenderer<Hound, HoundModel<Hound>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/hound/hound.png");

    public HoundRenderer(EntityRendererProvider.Context context) {
        super(context, new HoundModel<>(context.bakeLayer(UberEntityModelLayers.HOUND)), 0.3f);
        this.addLayer(new HoundEyeLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Hound fish) {
        return TEXTURE;
    }
}
