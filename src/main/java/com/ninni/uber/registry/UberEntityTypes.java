package com.ninni.uber.registry;

import com.ninni.uber.Uber;
import com.ninni.uber.entity.Hound;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;

public class UberEntityTypes {

    public static final EntityType<Hound> HOUND = register(
            "hound",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(Hound::new)
                    .defaultAttributes(Hound::createHoundAttributes)
                    .spawnGroup(MobCategory.MONSTER)
                    .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, Hound::canSpawn)
                    .dimensions(EntityDimensions.scalable(1.2F, 1.2F))
                    .trackRangeChunks(10)
    );

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(Uber.MOD_ID, id), entityType.build());
    }
}
