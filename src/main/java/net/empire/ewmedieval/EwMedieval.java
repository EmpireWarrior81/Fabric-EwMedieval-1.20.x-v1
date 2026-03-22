package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.block.custom.CuttingBoardBlock;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.enchantment.ModEnchantments;
import net.empire.ewmedieval.entity.ModEntityTypes;
import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.item.*;
import net.empire.ewmedieval.item.fooditems.DogFoodItem;
import net.empire.ewmedieval.item.fooditems.HorseFeedItem;
import net.empire.ewmedieval.item.toolitems.KnifeItem;
import net.empire.ewmedieval.recipe.ModRecipes;
import net.empire.ewmedieval.sound.ModSounds;
import net.empire.ewmedieval.util.ModLootTableModifiers;
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
        LOGGER.info("ewmedieval mod initialized successfully.");


        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModLootTableModifiers.modifyLootTables();
        ModLootTableModifiers.replaceLootTables();
        KnifeItem.init();
        ModEnchantments.registerEnchantments();

        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();

        ModRecipes.registerRecipes();

        ModSounds.registerModSounds();

        ModEntityTypes.registerEntityTypes();

        HorseFeedItem.init();
        CuttingBoardBlock.init();
        DogFoodItem.init();
        RopeItem.init();

    //    ModBannerPatterns.register();



        FuelRegistry.INSTANCE.add(ModItems.ROPE, 200);
        FuelRegistry.INSTANCE.add(ModItems.STRAW, 200);
        FuelRegistry.INSTANCE.add(ModBlocks.STRAW_BALE, 1800);

        FuelRegistry.INSTANCE.add(ModItems.OAK_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.SPRUCE_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.BIRCH_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.JUNGLE_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.ACACIA_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.DARK_OAK_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.MANGROVE_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.CHERRY_BARK, 200);
        FuelRegistry.INSTANCE.add(ModItems.BAMBOO_BARK, 200);

    }
}
