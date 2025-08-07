package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItemGroups;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EwMedieval implements ModInitializer {
    public static final String MOD_ID = "ewmedieval";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final boolean IS_DEBUG = true;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing ewmedieval mod...");

        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        LOGGER.info("ewmedieval mod initialized successfully.");
    }
}
