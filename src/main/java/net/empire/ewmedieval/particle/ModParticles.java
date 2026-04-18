package net.empire.ewmedieval.particle;

import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final DefaultParticleType STEAM = Registry.register(
            Registries.PARTICLE_TYPE,
            new Identifier(EwMedieval.MOD_ID, "steam"),
            FabricParticleTypes.simple(true));

    public static void registerParticles() {
        EwMedieval.LOGGER.info("Registering particles for " + EwMedieval.MOD_ID);
    }
}