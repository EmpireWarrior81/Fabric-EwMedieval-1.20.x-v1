package net.empire.ewmedieval.item;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.item.utils.armor.ModArmorMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


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

    public static final Item RAW_HORSE = registerItem("raw_horse", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_HORSE)));
    public static final Item RAW_SWAN = registerItem("raw_swan", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_SWAN)));
    public static final Item RAW_GOAT = registerItem("raw_goat", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_GOAT)));
    public static final Item COOKED_HORSE = registerItem("cooked_horse", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_HORSE)));
    public static final Item COOKED_SWAN = registerItem("cooked_swan", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_SWAN)));
    public static final Item COOKED_GOAT = registerItem("cooked_goat", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_GOAT)));
    public static final Item BAT_WING = registerItem("bat_wing", new Item(new FabricItemSettings().food(ModFoodComponents.BAT_WING)));
    public static final Item SMOKED_BAT_WING = registerItem("smoked_bat_wing", new Item(new FabricItemSettings().food(ModFoodComponents.SMOKED_BAT_WING)));

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


    public static final Item GONDORIAN_FOUNTAIN_GUARD_HELMET = registerItem("gondorian_fountain_guard_helmet",
            new ArmorItem(ModArmorMaterials.GONDORIAN_FOUNTAIN_GUARD, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE = registerItem("gondorian_fountain_guard_chestplate",
            new ArmorItem(ModArmorMaterials.GONDORIAN_FOUNTAIN_GUARD, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item GONDORIAN_FOUNTAIN_GUARD_LEGGINGS = registerItem("gondorian_fountain_guard_leggings",
            new ArmorItem(ModArmorMaterials.GONDORIAN_FOUNTAIN_GUARD, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item GONDORIAN_FOUNTAIN_GUARD_BOOTS = registerItem("gondorian_fountain_guard_boots",
            new ArmorItem(ModArmorMaterials.GONDORIAN_FOUNTAIN_GUARD, ArmorItem.Type.BOOTS,
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
        return Registry.register(Registries.ITEM, new Identifier(EwMedieval.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EwMedieval.LOGGER.info("Registering ModItems for EwMedieval " + EwMedieval.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
