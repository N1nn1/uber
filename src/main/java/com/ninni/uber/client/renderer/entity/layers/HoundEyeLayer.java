package com.ninni.uber.client.renderer.entity.layers;

import com.ninni.uber.Uber;
import com.ninni.uber.client.model.entity.HoundModel;
import com.ninni.uber.entity.Hound;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class HoundEyeLayer<T extends Hound, M extends HoundModel<T>> extends EyesLayer<T, M> {
    private static final RenderType HOUND_EYE = RenderType.eyes(new ResourceLocation(Uber.MOD_ID, "textures/entity/hound/hound_eye.png"));

    public HoundEyeLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public RenderType renderType() {
        return HOUND_EYE;
    }
}

