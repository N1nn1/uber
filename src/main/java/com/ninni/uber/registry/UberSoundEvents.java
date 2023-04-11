package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface UberSoundEvents {

    Holder.Reference<SoundEvent> AMBIENT_UBER_GRYMMOTH = registerForHolder("ambient.uber.grymmoth");


    private static Holder.Reference<SoundEvent> registerForHolder(String string) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(string), SoundEvent.createVariableRangeEvent(new ResourceLocation(string)));
    }

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(Uber.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
