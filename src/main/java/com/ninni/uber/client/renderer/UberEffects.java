package com.ninni.uber.client.renderer;

import com.ninni.uber.registry.UberSkyTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class UberEffects extends DimensionSpecialEffects {

    public UberEffects() {
        super(85, true, UberSkyTypes.UBER.get(), false, true);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vec3, float f) {
        return vec3;
    }

    @Override
    public boolean isFoggyAt(int i, int j) {
        return false;
    }
}
