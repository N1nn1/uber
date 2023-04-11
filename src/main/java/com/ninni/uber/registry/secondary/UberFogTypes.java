package com.ninni.uber.registry.secondary;

import net.minecraft.world.level.material.FogType;

public enum UberFogTypes {
    MANA;

    public FogType get() {
        return FogType.valueOf(this.name());
    }
}
