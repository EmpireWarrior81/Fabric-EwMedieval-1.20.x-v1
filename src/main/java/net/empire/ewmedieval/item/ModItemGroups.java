package net.empire.ewmedieval.item;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ARTIFACTS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "artifacts"),
            FabricItemGroup.builder().displayName(Text.translatable("Ew Medieval Artifacts"))
                    .icon(() -> new ItemStack(ModItems.ARKENSTONE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ARKENSTONE);
                    }).build());

    public static final ItemGroup METALS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "metals"),
            FabricItemGroup.builder().displayName(Text.translatable("Ew Medieval - Metals"))
                    .icon(() -> new ItemStack(ModItems.TIN_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_TIN);
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModItems.TIN_NUGGET);
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                        entries.add(ModItems.KHAZAD_STEEL_INGOT);
                        entries.add(ModItems.KHAZAD_STEEL_NUGGET);
                        entries.add(ModItems.BURZUM_STEEL_INGOT);
                        entries.add(ModItems.BURZUM_STEEL_NUGGET);
                        entries.add(ModItems.EDHEL_STEEL_INGOT);
                        entries.add(ModItems.EDHEL_STEEL_NUGGET);
                        entries.add(ModItems.CRUDE_INGOT);
                        entries.add(ModItems.CRUDE_NUGGET);
                        entries.add(ModItems.RAW_LEAD);
                        entries.add(ModItems.LEAD_INGOT);
                        entries.add(ModItems.LEAD_NUGGET);
                        entries.add(ModItems.RAW_MITHRIL);
                        entries.add(ModItems.MITHRIL_INGOT);
                        entries.add(ModItems.MITHRIL_NUGGET);
                    }).build());

    public static final ItemGroup FOOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "food"),
            FabricItemGroup.builder().displayName(Text.translatable("Ew Medieval - Food"))
                    .icon(() -> new ItemStack(ModItems.LEMBAS)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_HORSE);
                        entries.add(ModItems.COOKED_HORSE);
                        entries.add(ModItems.RAW_SWAN);
                        entries.add(ModItems.COOKED_SWAN);
                        entries.add(ModItems.RAW_GOAT);
                        entries.add(ModItems.COOKED_GOAT);
                        entries.add(ModItems.BAT_WING);
                        entries.add(ModItems.SMOKED_BAT_WING);
                        entries.add(ModItems.LEMBAS);
                        entries.add(ModItems.CACTUS_FLESH);
                        entries.add(ModItems.CACTUS_STEAK);
                        entries.add(ModItems.CRANBERRY);
                        entries.add(ModItems.FIG);
                        entries.add(ModItems.KIWI);
                        entries.add(ModItems.LEMON);
                        entries.add(ModItems.MANGO);
                        entries.add(ModItems.ORANGE);
                        entries.add(ModItems.PEACH);
                        entries.add(ModItems.PEAR);
                        entries.add(ModItems.BAKED_PEAR);
                    }).build());

    public static final ItemGroup BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("Ew Medieval - Blocks"))
                    .icon(() -> new ItemStack(ModBlocks.TIN_BLOCK)).entries((displayContext, entries) -> {
                        // metalen en grondstoffen
                        entries.add(ModBlocks.TIN_BLOCK);
                        entries.add(ModBlocks.RAW_TIN_BLOCK);
                        entries.add(ModBlocks.TIN_ORE);
                        entries.add(ModBlocks.DEEPSLATE_TIN_ORE);
                        entries.add(ModBlocks.SLATE_TIN_ORE);
                        entries.add(ModBlocks.RAW_SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);
                        entries.add(ModBlocks.RAW_LEAD_BLOCK);
                        entries.add(ModBlocks.LEAD_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_LEAD_ORE);
                        entries.add(ModBlocks.RAW_MITHRIL_BLOCK);
                        entries.add(ModBlocks.MITHRIL_BLOCK);
                        entries.add(ModBlocks.BURZUM_STEEL_BLOCK);
                        entries.add(ModBlocks.EDHEL_STEEL_BLOCK);
                        entries.add(ModBlocks.CRUDE_BLOCK);

                        // slate varianten
                        entries.add(ModBlocks.SLATE);
                        entries.add(ModBlocks.SLATE_STAIRS);
                        entries.add(ModBlocks.SLATE_SLAB);
                        entries.add(ModBlocks.SLATE_BUTTON);
                        entries.add(ModBlocks.SLATE_WALL);
                        entries.add(ModBlocks.SLATE_TRAPDOOR);
                        entries.add(ModBlocks.SLATE_PRESSURE_PLATE);

                        entries.add(ModBlocks.POLISHED_SLATE);
                        entries.add(ModBlocks.POLISHED_SLATE_STAIRS);
                        entries.add(ModBlocks.POLISHED_SLATE_SLAB);
                        entries.add(ModBlocks.POLISHED_SLATE_WALL);

                        entries.add(ModBlocks.SLATE_BRICKS);
                        entries.add(ModBlocks.SLATE_BRICKS_STAIRS);
                        entries.add(ModBlocks.SLATE_BRICKS_SLAB);
                        entries.add(ModBlocks.SLATE_BRICKS_WALL);

                        entries.add(ModBlocks.COBBLED_SLATE);
                        entries.add(ModBlocks.COBBLED_SLATE_STAIRS);
                        entries.add(ModBlocks.COBBLED_SLATE_SLAB);
                        entries.add(ModBlocks.COBBLED_SLATE_WALL);

                        // extra ores
                        entries.add(ModBlocks.SLATE_COPPER_ORE);
                        entries.add(ModBlocks.SLATE_COAL_ORE);

                        // deepslate varianten
                        entries.add(ModBlocks.OLD_DEEPSLATE);
                        entries.add(ModBlocks.OLD_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.OLD_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.OLD_DEEPSLATE_WALL);

                        entries.add(ModBlocks.SMOOTH_DEEPSLATE);
                        entries.add(ModBlocks.SMOOTH_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.SMOOTH_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.SMOOTH_DEEPSLATE_WALL);

                        entries.add(ModBlocks.MOSSY_SMOOTH_DEEPSLATE);
                        entries.add(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.MOSSY_SMOOTH_DEEPSLATE_WALL);

                        entries.add(ModBlocks.CRACKED_SMOOTH_DEEPSLATE);
                        entries.add(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.CRACKED_SMOOTH_DEEPSLATE_WALL);

                        entries.add(ModBlocks.MOSSY_COBBLED_DEEPSLATE);
                        entries.add(ModBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.MOSSY_COBBLED_DEEPSLATE_WALL);

                        entries.add(ModBlocks.DEEPSLATE_PILLAR);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_PILLAR);
                        entries.add(ModBlocks.CRACKED_DEEPSLATE_PILLAR);

                        entries.add(ModBlocks.MOSSY_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_BRICKS_SLAB);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_BRICKS_STAIRS);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_BRICKS_WALL);

                        entries.add(ModBlocks.MOSSY_DEEPSLATE_TILES);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_TILES_SLAB);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_TILES_STAIRS);
                        entries.add(ModBlocks.MOSSY_DEEPSLATE_TILES_WALL);

                        entries.add(ModBlocks.MOSSY_POLISHED_DEEPSLATE);
                        entries.add(ModBlocks.MOSSY_POLISHED_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.MOSSY_POLISHED_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.MOSSY_POLISHED_DEEPSLATE_WALL);

                        entries.add(ModBlocks.CRACKED_POLISHED_DEEPSLATE);
                        entries.add(ModBlocks.CRACKED_POLISHED_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.CRACKED_POLISHED_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.CRACKED_POLISHED_DEEPSLATE_WALL);

                        entries.add(ModBlocks.CHISELED_POLISHED_DEEPSLATE);
                        entries.add(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.CHISELED_DEEPSLATE_TILES);
                        entries.add(ModBlocks.CHISELED_SMOOTH_DEEPSLATE);

                        entries.add(ModBlocks.DEEPSLATE_BRICKWORK);
                        entries.add(ModBlocks.DEEPSLATE_BRICKWORK_SLAB);
                        entries.add(ModBlocks.DEEPSLATE_BRICKWORK_STAIRS);
                        entries.add(ModBlocks.DEEPSLATE_BRICKWORK_WALL);

                        entries.add(ModBlocks.DEEPSLATE_PRESSURE_PLATE);
                        entries.add(ModBlocks.DEEPSLATE_BUTTON);
                    }).build());

    public static void registerItemGroups() {
        EwMedieval.LOGGER.info("Registering Item Groups for " + EwMedieval.MOD_ID);
    }
}
