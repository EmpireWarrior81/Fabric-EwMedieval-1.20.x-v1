package net.empire.ewmedieval.item;

import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.empire.ewmedieval.EwMedieval.MOD_ID;


public class ModItems {


    public static final Item ARKENSTONE = registerItem("arkenstone", new Item(new FabricItemSettings()));
    public static final Item RAW_TIN = registerItem("raw_tin", new Item(new FabricItemSettings()));
    public static final Item TIN_INGOT = registerItem("tin_ingot", new Item(new FabricItemSettings()));
    public static final Item TIN_NUGGET = registerItem("tin_nugget", new Item(new FabricItemSettings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new FabricItemSettings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new FabricItemSettings()));
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new FabricItemSettings()));
    public static final Item KHAZAD_STEEL_INGOT = registerItem("khazad_steel_ingot", new Item(new FabricItemSettings()));
    public static final Item KHAZAD_STEEL_NUGGET = registerItem("khazad_steel_nugget", new Item(new FabricItemSettings()));
    public static final Item BURZUM_STEEL_INGOT = registerItem("burzum_steel_ingot", new Item(new FabricItemSettings()));
    public static final Item BURZUM_STEEL_NUGGET = registerItem("burzum_steel_nugget", new Item(new FabricItemSettings()));
    public static final Item CRUDE_INGOT = registerItem("crude_ingot", new Item(new FabricItemSettings()));
    public static final Item CRUDE_NUGGET = registerItem("crude_nugget", new Item(new FabricItemSettings()));
    public static final Item EDHEL_STEEL_INGOT = registerItem("edhel_steel_ingot", new Item(new FabricItemSettings()));
    public static final Item EDHEL_STEEL_NUGGET = registerItem("edhel_steel_nugget", new Item(new FabricItemSettings()));
    public static final Item LEAD_NUGGET = registerItem("lead_nugget", new Item(new FabricItemSettings()));
    public static final Item RAW_LEAD = registerItem("raw_lead", new Item(new FabricItemSettings()));
    public static final Item LEAD_INGOT = registerItem("lead_ingot", new Item(new FabricItemSettings()));
    public static final Item MITHRIL_INGOT = registerItem("mithril_ingot", new Item(new FabricItemSettings()));
    public static final Item MITHRIL_NUGGET = registerItem("mithril_nugget", new Item(new FabricItemSettings()));
    public static final Item RAW_MITHRIL = registerItem("raw_mithril", new Item(new FabricItemSettings()));
    public static final Item BRONZE_MIXTURE = registerItem("bronze_mixture", new Item(new FabricItemSettings()));
    public static final Item BRONZE_INGOT = registerItem("bronze_ingot", new Item(new FabricItemSettings()));
    public static final Item BRONZE_NUGGET = registerItem("bronze_nugget", new Item(new FabricItemSettings()));
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item STEEL_NUGGET = registerItem("steel_nugget", new Item(new FabricItemSettings()));

    public static final Item BRONZE_ASH_MIXTURE = registerItem("bronze_ash_mixture", new Item(new FabricItemSettings()));

    public static final Item RAW_IRON_NUGGET = registerItem("raw_iron_nugget", new Item(new FabricItemSettings()));

    public static final Item ASH = registerItem("ash", new Item(new FabricItemSettings()));
    public static final Item ASH_PIECE = registerItem("ash_piece", new Item(new FabricItemSettings()));
    public static final Item FUR = registerItem("fur", new Item(new FabricItemSettings()));

    public static final Item RAW_HORSE = registerItem("raw_horse", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_HORSE)));
    public static final Item RAW_SWAN = registerItem("raw_swan", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_SWAN)));
    public static final Item RAW_GOAT = registerItem("raw_goat", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_GOAT)));
    public static final Item COOKED_HORSE = registerItem("cooked_horse", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_HORSE)));
    public static final Item COOKED_SWAN = registerItem("cooked_swan", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_SWAN)));
    public static final Item COOKED_GOAT = registerItem("cooked_goat", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_GOAT)));
    public static final Item BAT_WING = registerItem("bat_wing", new Item(new FabricItemSettings().food(ModFoodComponents.BAT_WING)));
    public static final Item SMOKED_BAT_WING = registerItem("smoked_bat_wing", new Item(new FabricItemSettings().food(ModFoodComponents.SMOKED_BAT_WING)));
    public static final Item RAW_WOLF = registerItem("raw_wolf", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_WOLF)));
    public static final Item COOKED_WOLF = registerItem("cooked_wolf", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_WOLF)));

    public static final Item LEMBAS = registerItem("lembas", new Item(new FabricItemSettings().food(ModFoodComponents.LEMBAS)));

    public static final Item CACTUS_FLESH = registerItem("cactus_flesh", new Item(new FabricItemSettings().food(ModFoodComponents.CACTUS_FLESH)));
    public static final Item CACTUS_STEAK = registerItem("cactus_steak", new Item(new FabricItemSettings().food(ModFoodComponents.CACTUS_STEAK)));
    public static final Item CRANBERRY = registerItem("cranberry", new Item(new FabricItemSettings().food(ModFoodComponents.CRANBERRY)));
    public static final Item FIG = registerItem("fig", new Item(new FabricItemSettings().food(ModFoodComponents.FIG)));
    public static final Item KIWI = registerItem("kiwi", new Item(new FabricItemSettings().food(ModFoodComponents.KIWI)));
    public static final Item LEMON = registerItem("lemon", new Item(new FabricItemSettings().food(ModFoodComponents.LEMON)));
    public static final Item MANGO = registerItem("mango", new Item(new FabricItemSettings().food(ModFoodComponents.MANGO)));
    public static final Item ORANGE = registerItem("orange", new Item(new FabricItemSettings().food(ModFoodComponents.ORANGE)));
    public static final Item PEACH = registerItem("peach", new Item(new FabricItemSettings().food(ModFoodComponents.PEACH)));
    public static final Item PEAR = registerItem("pear", new Item(new FabricItemSettings().food(ModFoodComponents.PEAR)));
    public static final Item BAKED_PEAR = registerItem("baked_pear", new Item(new FabricItemSettings().food(ModFoodComponents.BAKED_PEAR)));

    public static final Item CAKE_SLICE = registerItem("cake_slice", new Item(new FabricItemSettings().food(ModFoodComponents.CAKE_SLICE)));
    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", new Item(new FabricItemSettings().food(ModFoodComponents.PUMPKIN_SLICE)));

    public static final Item STONE_PEBBLE = registerItem("stone_pebble", new Item(new FabricItemSettings()));


    public static final Item CRUDE_PICKAXE = registerItem("crude_pickaxe",
            new PickaxeItem(ModToolMaterial.CRUDE, 1, -2.8f, new FabricItemSettings()));
      public static final Item CRUDE_AXE = registerItem("crude_axe",
            new AxeItem(ModToolMaterial.CRUDE, 6, -3.0f, new FabricItemSettings()));
      public static final Item CRUDE_SHOVEL = registerItem("crude_shovel",
            new ShovelItem(ModToolMaterial.CRUDE, 1, -3.0f, new FabricItemSettings()));
      public static final Item CRUDE_HOE = registerItem("crude_hoe",
            new HoeItem(ModToolMaterial.CRUDE, -1, -1.0f, new FabricItemSettings()));

    public static final Item BRONZE_SHEARS = registerItem("bronze_shears",
            new BronzeShears(new Item.Settings().maxCount(1)));

    public static final Item BRONZE_PICKAXE = registerItem("bronze_pickaxe",
            new PickaxeItem(ModToolMaterial.BRONZE, 1, -2.8f, new FabricItemSettings()));

    public static final Item BRONZE_AXE = registerItem("bronze_axe",
            new AxeItem(ModToolMaterial.BRONZE, 5.5f, -3.0f, new FabricItemSettings()));

    public static final Item BRONZE_SHOVEL = registerItem("bronze_shovel",
            new ShovelItem(ModToolMaterial.BRONZE, 1, -3.0f, new FabricItemSettings()));

    public static final Item BRONZE_HOE = registerItem("bronze_hoe",
            new HoeItem(ModToolMaterial.BRONZE, -1, -1.0f, new FabricItemSettings()));

    public static final Item BRONZE_KNIFE = registerItem("bronze_knife",
            new KnifeItem(ModToolMaterial.BRONZE, 0.5f, -2.0f,
                    new FabricItemSettings()));

    public static final Item IRON_KNIFE = registerItem("iron_knife",
            new KnifeItem(ToolMaterials.IRON, 0.5f, -2.0f,
                    new FabricItemSettings()));

    public static final Item GOLDEN_KNIFE = registerItem("golden_knife",
            new KnifeItem(ToolMaterials.GOLD, 0.5f, -2.0f,
                    new FabricItemSettings()));
    public static final Item DIAMOND_KNIFE = registerItem("diamond_knife",
            new KnifeItem(ToolMaterials.DIAMOND, 0.5f, -2.0f,
                    new FabricItemSettings()));
    public static final Item NETHERITE_KNIFE = registerItem("netherite_knife",
            new KnifeItem(ToolMaterials.NETHERITE, 0.5f, -2.0f,
                    new FabricItemSettings()));
    public static final Item FLINT_KNIFE = registerItem("flint_knife",
            new KnifeItem(ModToolMaterial.FLINT, 0.5f, -2.0f,
                    new FabricItemSettings()));
 /*   public static final Item WOODEN_KNIFE = registerItem("wooden_knife",
            new KnifeItem(ToolMaterials.WOOD, 0.5f, -2.0f,
                    new FabricItemSettings())); */
    public static final Item STONE_KNIFE = registerItem("stone_knife",
            new KnifeItem(ToolMaterials.STONE, 0.5f, -2.0f,
                    new FabricItemSettings()));





    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(RAW_TIN);
        entries.add(TIN_INGOT);
        entries.add(SILVER_INGOT);
        entries.add(RAW_SILVER);
        entries.add(KHAZAD_STEEL_INGOT);
        entries.add(KHAZAD_STEEL_NUGGET);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void registerModItems() {
        EwMedieval.LOGGER.info("Registering ModItems for EwMedieval " + MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
