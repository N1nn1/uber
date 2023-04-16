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

    Holder.Reference<SoundEvent> AMBIENT_UBER_ECSTACE = registerForHolder("ambient.uber.ecstace");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ECSTACE_LOOP = registerForHolder("ambient.uber.ecstace.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ECSTACE_ADDITIONS = registerForHolder("ambient.uber.ecstace.additions");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ELYSIUM_FIELDS_LOOP = registerForHolder("ambient.uber.elysium_fields.loop");
    Holder.Reference<SoundEvent> AMBIENT_UBER_ELYSIUM_FIELDS_ADDITIONS = registerForHolder("ambient.uber.elysium_fields.additions");

    SoundEvent BUCKET_EMPTY_MANA = register("item.bucket.empty_mana");
    SoundEvent BUCKET_FILL_MANA = register("item.bucket.fill_mana");
    SoundEvent MANA_DISAPPEAR = register("liquid.mana.disappear");
    SoundEvent MANA_ENTER = register("liquid.mana.enter");
    SoundEvent MANA_EXIT = register("liquid.mana.exit");
    SoundEvent MANA_SPLASH = register("liquid.mana.splash");
    SoundEvent MANA_SPLASH_HEAVY = register("liquid.mana.splash_heavy");
    SoundEvent MANA_GURGLE = register("liquid.mana.gurgle");
    SoundEvent MANA_GURGLE_ADDITION = register("liquid.mana.gurgle_addition");
    SoundEvent MANA_POP = register("liquid.mana.pop");

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
