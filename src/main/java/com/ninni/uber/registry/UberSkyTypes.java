package com.ninni.uber.registry;

import net.minecraft.client.renderer.DimensionSpecialEffects;

public enum UberSkyTypes {
    UBER;

    public DimensionSpecialEffects.SkyType get() {
        return DimensionSpecialEffects.SkyType.valueOf(this.name());
    }

}
