package com.ninni.uber.entity.pose;

import net.minecraft.world.entity.Pose;

public enum UberPose {
    LIMP;

    public Pose get() {
        return Pose.valueOf(this.name());
    }
}
