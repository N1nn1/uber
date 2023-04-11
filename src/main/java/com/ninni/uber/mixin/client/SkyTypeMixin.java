package com.ninni.uber.mixin.client;

import com.ninni.uber.registry.secondary.UberSkyTypes;
import net.minecraft.client.renderer.DimensionSpecialEffects;
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

@Mixin(DimensionSpecialEffects.SkyType.class)
public class SkyTypeMixin {
    @Shadow
    @Mutable
    @Final
    private static DimensionSpecialEffects.SkyType[] $VALUES;

    @Invoker("<init>")
    public static DimensionSpecialEffects.SkyType newSkyType(String name, int id) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/DimensionSpecialEffects$SkyType;$VALUES:[Lnet/minecraft/client/renderer/DimensionSpecialEffects$SkyType;", shift = At.Shift.AFTER))
    private static void Uber$addCustomDimensionSpecialEffects(CallbackInfo ci) {
        List<DimensionSpecialEffects.SkyType> poses = new ArrayList<>(Arrays.asList($VALUES));
        DimensionSpecialEffects.SkyType last = poses.get(poses.size() - 1);
        int i = 1;
        for (UberSkyTypes skyType : UberSkyTypes.values()) {
            poses.add(newSkyType(skyType.name(), last.ordinal() + i));
            i++;
        }
        $VALUES = poses.toArray(new DimensionSpecialEffects.SkyType[0]);
    }
}
