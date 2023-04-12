package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface UberSoundEvents {

    Holder.Reference<SoundEvent> AMBIENT_UBER_GRYMMOTH = registerForHolder("ambient.uber.grymmoth");
    Holder.Reference<SoundEvent> AMBIENT_UBER_GRYMMOTH_LOOP = registerForHolder("ambient.uber.grymmoth.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_GRYMMOTH_ADDITIONS = registerForHolder("ambient.uber.grymmoth.additions");
    Holder.Reference<SoundEvent> AMBIENT_UBER_PHASMO_FOREST_LOOP = registerForHolder("ambient.uber.phasmo_forest.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_PHASMO_FOREST_ADDITIONS = registerForHolder("ambient.uber.phasmo_forest.additions");

    Holder.Reference<SoundEvent> AMBIENT_UBER_ESTACE = registerForHolder("ambient.uber.estace");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ESTACE_LOOP = registerForHolder("ambient.uber.estace.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ESTACE_ADDITIONS = registerForHolder("ambient.uber.estace.additions");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ELYSIUM_FIELDS_LOOP = registerForHolder("ambient.uber.elysium_fields.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ELYSIUM_FIELDS_ADDITIONS = registerForHolder("ambient.uber.elysium_fields.additions");


    //TODO addition sounds

    private static Holder.Reference<SoundEvent> registerForHolder(String name) {
        ResourceLocation id = new ResourceLocation(Uber.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(Uber.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
