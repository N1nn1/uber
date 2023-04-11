package com.ninni.uber.mixin;

import com.ninni.uber.registry.secondary.UberFogTypes;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(FogType.class)
public class FogTypeMixin {
    @Shadow
    @Mutable
    @Final
    private static FogType[] $VALUES;

    @Invoker("<init>")
    public static FogType newFogType(String name, int id) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/material/FogType;$VALUES:[Lnet/minecraft/world/level/material/FogType;", shift = At.Shift.AFTER))
    private static void Uber$addCustomFogTypes(CallbackInfo ci) {
        List<FogType> poses = new ArrayList<>(Arrays.asList($VALUES));
        FogType last = poses.get(poses.size() - 1);
        int i = 1;
        for (UberFogTypes fogTypes : UberFogTypes.values()) {
            poses.add(newFogType(fogTypes.name(), last.ordinal() + i));
            i++;
        }
        $VALUES = poses.toArray(new FogType[0]);
    }
}
