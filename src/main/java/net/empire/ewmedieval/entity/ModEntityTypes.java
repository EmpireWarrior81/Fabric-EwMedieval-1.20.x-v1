package net.empire.ewmedieval.entity;

import net.empire.ewmedieval.EwMedieval;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {

    public static final EntityType<RottenTomatoEntity> ROTTEN_TOMATO = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(EwMedieval.MOD_ID, "rotten_tomato"),
            EntityType.Builder.<RottenTomatoEntity>create(RottenTomatoEntity::new, SpawnGroup.MISC)
                    .trackingTickInterval(10)
                    .maxTrackingRange(4)
                    .build("rotten_tomato"));

    public static void registerEntityTypes() {
        EwMedieval.LOGGER.info("Registering entity types for " + EwMedieval.MOD_ID);
    }
}