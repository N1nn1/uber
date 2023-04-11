package com.ninni.uber.registry.secondary;

import net.minecraft.client.renderer.DimensionSpecialEffects;

public enum UberSkyTypes {
    UBER;

    public DimensionSpecialEffects.SkyType get() {
        return DimensionSpecialEffects.SkyType.valueOf(this.name());
    }

}
