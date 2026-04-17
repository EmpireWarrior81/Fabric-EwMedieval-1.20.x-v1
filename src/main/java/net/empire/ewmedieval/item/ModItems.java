package net.empire.ewmedieval.item;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.fooditems.*;
import net.empire.ewmedieval.item.toolitems.BronzeShears;
import net.empire.ewmedieval.item.toolitems.CustomSwordItem;
import net.empire.ewmedieval.item.toolitems.KnifeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

    public static final Item OAK_BARK = registerItem("oak_bark",
            new Item(new FabricItemSettings()));
    public static final Item SPRUCE_BARK = registerItem("spruce_bark",
            new Item(new FabricItemSettings()));
    public static final Item BIRCH_BARK = registerItem("birch_bark",
            new Item(new FabricItemSettings()));
    public static final Item JUNGLE_BARK = registerItem("jungle_bark",
            new Item(new FabricItemSettings()));
    public static final Item ACACIA_BARK = registerItem("acacia_bark",
            new Item(new FabricItemSettings()));
    public static final Item DARK_OAK_BARK = registerItem("dark_oak_bark",
            new Item(new FabricItemSettings()));
    public static final Item MANGROVE_BARK = registerItem("mangrove_bark",
            new Item(new FabricItemSettings()));
    public static final Item CHERRY_BARK = registerItem("cherry_bark",
            new Item(new FabricItemSettings()));
    public static final Item BAMBOO_BARK = registerItem("bamboo_bark",
            new Item(new FabricItemSettings()));

    public static final Item ASH = registerItem("ash", new Item(new FabricItemSettings()));
    public static final Item ASH_PIECE = registerItem("ash_piece", new Item(new FabricItemSettings()));
    public static final Item FUR = registerItem("fur", new Item(new FabricItemSettings()));

    public static final Item STONE_PEBBLE = registerItem("stone_pebble", new Item(new FabricItemSettings()));

    public static final Item ROPE = registerItem("rope", new RopeItem(ModBlocks.ROPE, new FabricItemSettings()));
    public static final Item GLASS_JUG = registerItem("glass_jug", new Item(new FabricItemSettings()));
    public static final Item GLASS_CHALICE = registerItem("glass_chalice", new Item(new FabricItemSettings()));

    public static final Item RAW_HORSE = registerItem("raw_horse", new Item(new FabricItemSettings().food(ModFoods.RAW_HORSE)));
    public static final Item RAW_SWAN = registerItem("raw_swan", new Item(new FabricItemSettings().food(ModFoods.RAW_SWAN)));
    public static final Item RAW_GOAT = registerItem("raw_goat", new Item(new FabricItemSettings().food(ModFoods.RAW_GOAT)));
    public static final Item COOKED_HORSE = registerItem("cooked_horse", new Item(new FabricItemSettings().food(ModFoods.COOKED_HORSE)));
    public static final Item COOKED_SWAN = registerItem("cooked_swan", new Item(new FabricItemSettings().food(ModFoods.COOKED_SWAN)));
    public static final Item COOKED_GOAT = registerItem("cooked_goat", new Item(new FabricItemSettings().food(ModFoods.COOKED_GOAT)));
    public static final Item BAT_WING = registerItem("bat_wing", new Item(new FabricItemSettings().food(ModFoods.BAT_WING)));
    public static final Item SMOKED_BAT_WING = registerItem("smoked_bat_wing", new Item(new FabricItemSettings().food(ModFoods.SMOKED_BAT_WING)));
    public static final Item RAW_WOLF = registerItem("raw_wolf", new Item(new FabricItemSettings().food(ModFoods.RAW_WOLF)));
    public static final Item COOKED_WOLF = registerItem("cooked_wolf", new Item(new FabricItemSettings().food(ModFoods.COOKED_WOLF)));

    public static final Item CABBAGE = registerItem("cabbage", new Item(new FabricItemSettings().food(ModFoods.CABBAGE)));
    public static final Item TOMATO = registerItem("tomato", new Item(new FabricItemSettings().food(ModFoods.TOMATO)));
    public static final Item ONION = registerItem("onion", new AliasedBlockItem(ModBlocks.ONION_CROP, new FabricItemSettings().food(ModFoods.ONION)));


    public static final Item CRANBERRY = registerItem("cranberry", new Item(new FabricItemSettings().food(ModFoods.CRANBERRY)));
    public static final Item FIG = registerItem("fig", new Item(new FabricItemSettings().food(ModFoods.FIG)));
    public static final Item KIWI = registerItem("kiwi", new Item(new FabricItemSettings().food(ModFoods.KIWI)));
    public static final Item LEMON = registerItem("lemon", new Item(new FabricItemSettings().food(ModFoods.LEMON)));
    public static final Item MANGO = registerItem("mango", new Item(new FabricItemSettings().food(ModFoods.MANGO)));
    public static final Item ORANGE = registerItem("orange", new Item(new FabricItemSettings().food(ModFoods.ORANGE)));
    public static final Item PEACH = registerItem("peach", new Item(new FabricItemSettings().food(ModFoods.PEACH)));
    public static final Item PEAR = registerItem("pear", new Item(new FabricItemSettings().food(ModFoods.PEAR)));
    public static final Item BAKED_PEAR = registerItem("baked_pear", new Item(new FabricItemSettings().food(ModFoods.BAKED_PEAR)));


    public static final Item FRIED_EGG = registerItem("fried_egg", new Item(new FabricItemSettings().food(ModFoods.FRIED_EGG)));
    public static final Item TOMATO_SAUCE = registerItem("tomato_sauce", new Item(new FabricItemSettings().food(ModFoods.TOMATO_SAUCE).recipeRemainder(Items.BOWL).maxCount(16)));
    public static final Item WHEAT_DOUGH = registerItem("wheat_dough", new Item(new FabricItemSettings().food(ModFoods.WHEAT_DOUGH)));
    public static final Item RAW_PASTA = registerItem("raw_pasta", new Item(new FabricItemSettings().food(ModFoods.RAW_PASTA)));
    public static final Item PIE_CRUST = registerItem("pie_crust", new Item(new FabricItemSettings().food(ModFoods.PIE_CRUST)));
    public static final Item CAKE_SLICE = registerItem("cake_slice", new Item(new FabricItemSettings().food(ModFoods.CAKE_SLICE)));
    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", new Item(new FabricItemSettings().food(ModFoods.PUMPKIN_SLICE)));
    public static final Item CABBAGE_LEAF = registerItem("cabbage_leaf", new Item(new FabricItemSettings().food(ModFoods.CABBAGE_LEAF)));
    public static final Item MINCED_BEEF = registerItem("minced_beef", new Item(new FabricItemSettings().food(ModFoods.MINCED_BEEF)));
    public static final Item BEEF_PATTY = registerItem("beef_patty", new Item(new FabricItemSettings().food(ModFoods.BEEF_PATTY)));
    public static final Item CHICKEN_CUTS = registerItem("chicken_cuts", new Item(new FabricItemSettings().food(ModFoods.CHICKEN_CUTS)));
    public static final Item COOKED_CHICKEN_CUTS = registerItem("cooked_chicken_cuts", new Item(new FabricItemSettings().food(ModFoods.COOKED_CHICKEN_CUTS)));
    public static final Item BACON = registerItem("bacon", new Item(new FabricItemSettings().food(ModFoods.BACON)));
    public static final Item COOKED_BACON = registerItem("cooked_bacon", new Item(new FabricItemSettings().food(ModFoods.COOKED_BACON)));
    public static final Item COD_SLICE = registerItem("cod_slice", new Item(new FabricItemSettings().food(ModFoods.COD_SLICE)));
    public static final Item COOKED_COD_SLICE = registerItem("cooked_cod_slice", new Item(new FabricItemSettings().food(ModFoods.COOKED_COD_SLICE)));
    public static final Item SALMON_SLICE = registerItem("salmon_slice", new Item(new FabricItemSettings().food(ModFoods.SALMON_SLICE)));
    public static final Item COOKED_SALMON_SLICE = registerItem("cooked_salmon_slice", new Item(new FabricItemSettings().food(ModFoods.COOKED_SALMON_SLICE)));
    public static final Item MUTTON_CHOPS = registerItem("mutton_chops", new Item(new FabricItemSettings().food(ModFoods.MUTTON_CHOPS)));
    public static final Item COOKED_MUTTON_CHOPS = registerItem("cooked_mutton_chops", new Item(new FabricItemSettings().food(ModFoods.COOKED_MUTTON_CHOPS)));
    public static final Item HAM = registerItem("ham", new Item(new FabricItemSettings().food(ModFoods.HAM)));
    public static final Item SMOKED_HAM = registerItem("smoked_ham", new Item(new FabricItemSettings().food(ModFoods.SMOKED_HAM)));

    public static final Item FRUIT_SALAD = registerItem("fruit_salad", new ConsumableItem(new FabricItemSettings().food(ModFoods.FRUIT_SALAD).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item GLOW_BERRY_CUSTARD = registerItem("glow_berry_custard", new ConsumableItem(new FabricItemSettings().food(ModFoods.GLOW_BERRY_CUSTARD)
            .recipeRemainder(ModItems.GLASS_CHALICE).maxCount(16)));


    public static final Item MIXED_SALAD = registerItem("mixed_salad", new ConsumableItem(new FabricItemSettings().food(ModFoods.MIXED_SALAD).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item NETHER_SALAD = registerItem("nether_salad", new ConsumableItem(new FabricItemSettings().food(ModFoods.NETHER_SALAD).recipeRemainder(Items.BOWL).maxCount(16)));
    public static final Item BARBECUE_STICK = registerItem("barbecue_stick", new Item(new FabricItemSettings().food(ModFoods.BARBECUE_STICK)));
    public static final Item EGG_SANDWICH = registerItem("egg_sandwich", new Item(new FabricItemSettings().food(ModFoods.EGG_SANDWICH)));
    public static final Item CHICKEN_SANDWICH = registerItem("chicken_sandwich", new Item(new FabricItemSettings().food(ModFoods.CHICKEN_SANDWICH)));
    public static final Item HAMBURGER = registerItem("hamburger", new Item(new FabricItemSettings().food(ModFoods.HAMBURGER)));
    public static final Item BACON_SANDWICH = registerItem("bacon_sandwich", new Item(new FabricItemSettings().food(ModFoods.BACON_SANDWICH)));
    public static final Item MUTTON_WRAP = registerItem("mutton_wrap", new Item(new FabricItemSettings().food(ModFoods.MUTTON_WRAP)));
    public static final Item DUMPLINGS = registerItem("dumplings", new Item(new FabricItemSettings().food(ModFoods.DUMPLINGS)));
    public static final Item STUFFED_POTATO = registerItem("stuffed_potato", new Item(new FabricItemSettings().food(ModFoods.STUFFED_POTATO)));
    public static final Item CABBAGE_ROLLS = registerItem("cabbage_rolls", new Item(new FabricItemSettings().food(ModFoods.CABBAGE_ROLLS)));
    public static final Item SALMON_ROLL = registerItem("salmon_roll", new Item(new FabricItemSettings().food(ModFoods.SALMON_ROLL)));
    public static final Item COD_ROLL = registerItem("cod_roll", new Item(new FabricItemSettings().food(ModFoods.COD_ROLL)));
    public static final Item KELP_ROLL = registerItem("kelp_roll", new Item(new FabricItemSettings().food(ModFoods.KELP_ROLL)));
    public static final Item KELP_ROLL_SLICE = registerItem("kelp_roll_slice", new Item(new FabricItemSettings().food(ModFoods.KELP_ROLL_SLICE)));


    public static final Item COOKED_RICE = registerItem("cooked_rice", new ConsumableItem(new FabricItemSettings().food(ModFoods.COOKED_RICE).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item BONE_BROTH = registerItem("bone_broth", new DrinkableItem(new FabricItemSettings().food(ModFoods.BONE_BROTH).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item BEEF_STEW = registerItem("beef_stew", new ConsumableItem(new FabricItemSettings().food(ModFoods.BEEF_STEW).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item VEGETABLE_SOUP = registerItem("vegetable_soup", new ConsumableItem(new FabricItemSettings().food(ModFoods.VEGETABLE_SOUP).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item FISH_STEW = registerItem("fish_stew", new ConsumableItem(new FabricItemSettings().food(ModFoods.FISH_STEW).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item CHICKEN_SOUP = registerItem("chicken_soup", new ConsumableItem(new FabricItemSettings().food(ModFoods.CHICKEN_SOUP).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item FRIED_RICE = registerItem("fried_rice", new ConsumableItem(new FabricItemSettings().food(ModFoods.FRIED_RICE).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item PUMPKIN_SOUP = registerItem("pumpkin_soup", new ConsumableItem(new FabricItemSettings().food(ModFoods.PUMPKIN_SOUP).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item BAKED_COD_STEW = registerItem("baked_cod_stew", new ConsumableItem(new FabricItemSettings().food(ModFoods.BAKED_COD_STEW).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item NOODLE_SOUP = registerItem("noodle_soup", new ConsumableItem(new FabricItemSettings().food(ModFoods.NOODLE_SOUP).recipeRemainder(Items.BOWL).maxCount(16), true));


    public static final Item BACON_AND_EGGS = registerItem("bacon_and_eggs", new ConsumableItem(new FabricItemSettings().food(ModFoods.BACON_AND_EGGS).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item RATATOUILLE = registerItem("ratatouille", new ConsumableItem(new FabricItemSettings().food(ModFoods.RATATOUILLE).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item STEAK_AND_POTATOES = registerItem("steak_and_potatoes", new ConsumableItem(new FabricItemSettings().food(ModFoods.STEAK_AND_POTATOES).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item PASTA_WITH_MEATBALLS = registerItem("pasta_with_meatballs", new ConsumableItem(new FabricItemSettings().food(ModFoods.PASTA_WITH_MEATBALLS).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item PASTA_WITH_MUTTON_CHOP = registerItem("pasta_with_mutton_chop", new ConsumableItem(new FabricItemSettings().food(ModFoods.PASTA_WITH_MUTTON_CHOP).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item MUSHROOM_RICE = registerItem("mushroom_rice", new ConsumableItem(new FabricItemSettings().food(ModFoods.MUSHROOM_RICE).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item ROASTED_MUTTON_CHOPS = registerItem("roasted_mutton_chops", new ConsumableItem(new FabricItemSettings().food(ModFoods.ROASTED_MUTTON_CHOPS).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item VEGETABLE_NOODLES = registerItem("vegetable_noodles", new ConsumableItem(new FabricItemSettings().food(ModFoods.VEGETABLE_NOODLES).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item SQUID_INK_PASTA = registerItem("squid_ink_pasta", new ConsumableItem(new FabricItemSettings().food(ModFoods.SQUID_INK_PASTA).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item GRILLED_SALMON = registerItem("grilled_salmon", new ConsumableItem(new FabricItemSettings().food(ModFoods.GRILLED_SALMON).recipeRemainder(Items.BOWL).maxCount(16), true));


    public static final Item STUFFED_PUMPKIN = registerItem("stuffed_pumpkin", new ConsumableItem(new FabricItemSettings().food(ModFoods.STUFFED_PUMPKIN).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item ROAST_CHICKEN = registerItem("roast_chicken", new ConsumableItem(new FabricItemSettings().food(ModFoods.ROAST_CHICKEN).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item HONEY_GLAZED_HAM = registerItem("honey_glazed_ham", new ConsumableItem(new FabricItemSettings().food(ModFoods.HONEY_GLAZED_HAM).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item SHEPHERDS_PIE = registerItem("shepherds_pie", new ConsumableItem(new FabricItemSettings().food(ModFoods.SHEPHERDS_PIE).recipeRemainder(Items.BOWL).maxCount(16), true));


    public static final Item APPLE_CIDER = registerItem("apple_cider",
            new DrinkableItem(new FabricItemSettings().food(ModFoods.APPLE_CIDER)
                    .recipeRemainder(ModItems.GLASS_JUG).maxCount(16)));
    public static final Item MELON_JUICE = registerItem("melon_juice",
            new MelonJuiceItem(new FabricItemSettings()
                    .food(ModFoods.MELON_JUICE).recipeRemainder(ModItems.GLASS_JUG).maxCount(16)));
    public static final Item HOT_COCOA = registerItem("hot_cocoa",
            new HotCocoaItem(new FabricItemSettings()
                    .food(ModFoods.HOT_COCOA).recipeRemainder(ModItems.GLASS_JUG).maxCount(16)));
    public static final Item MILK_BOTTLE = registerItem("milk_bottle",
            new MilkBottleItem(new FabricItemSettings()
                    .food(ModFoods.MILK_BOTTLE).recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));

    public static final Item LEMBAS = registerItem("lembas", new Item(new FabricItemSettings().food(ModFoods.LEMBAS)));
    public static final Item CACTUS_FLESH = registerItem("cactus_flesh", new Item(new FabricItemSettings().food(ModFoods.CACTUS_FLESH)));
    public static final Item CACTUS_STEAK = registerItem("cactus_steak", new Item(new FabricItemSettings().food(ModFoods.CACTUS_STEAK)));

    public static final Item CABBAGE_SEEDS = registerItem("cabbage_seeds",
            new AliasedBlockItem(ModBlocks.CABBAGE_CROP, new FabricItemSettings()));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new AliasedBlockItem(ModBlocks.BUDDING_TOMATO_CROP, new FabricItemSettings()));

    public static final Item CUCUMBER_SEEDS = registerItem("cucumber_seeds",
            new AliasedBlockItem(ModBlocks.CUCUMBER_CROP, new FabricItemSettings()));
    public static final Item CORN_KERNELS = registerItem("corn_kernels",
            new AliasedBlockItem(ModBlocks.CORN_CROP, new FabricItemSettings()));
    public static final Item EGGPLANT_SEEDS = registerItem("eggplant_seeds",
            new AliasedBlockItem(ModBlocks.EGGPLANT_CROP, new FabricItemSettings()));

    public static final Item BELL_PEPPER_SEEDS = registerItem("bell_pepper_seeds",
            new AliasedBlockItem(ModBlocks.BELL_PEPPER_CROP, new FabricItemSettings()));
    public static final Item COTTON_SEEDS = registerItem("cotton_seeds",
            new AliasedBlockItem(ModBlocks.COTTON_CROP, new FabricItemSettings()));
    public static final Item COFFEE_BEANS = registerItem("coffee_beans",
            new AliasedBlockItem(ModBlocks.COFFEE_CROP, new FabricItemSettings()));

    public static final Item SOYA_BEAN_POD = registerItem("soya_bean_pod",
            new Item(new FabricItemSettings()));
    public static final Item GARLIC = registerItem("garlic",
            new Item(new FabricItemSettings()));

    public static final Item SWEET_POTATO = registerItem("sweet_potato",
            new AliasedBlockItem(ModBlocks.SWEET_POTATO_CROP, new FabricItemSettings().food(ModFoods.SWEET_POTATO)));
    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new FabricItemSettings()));
    public static final Item BROCCOLI_SEEDS = registerItem("broccoli_seeds",
            new AliasedBlockItem(ModBlocks.BROCCOLI_CROP, new FabricItemSettings()));
    public static final Item ZUCCHINI_SEEDS = registerItem("zucchini_seeds",
            new AliasedBlockItem(ModBlocks.ZUCCHINI_CROP, new FabricItemSettings()));
    public static final Item TURNIP_SEEDS = registerItem("turnip_seeds",
            new AliasedBlockItem(ModBlocks.TURNIP_CROP, new FabricItemSettings()));
    public static final Item SOYA_BEANS = registerItem("soya_beans",
            new AliasedBlockItem(ModBlocks.SOYA_CROP, new FabricItemSettings().food(ModFoods.SOY_BEANS)));
    public static final Item YUCA = registerItem("yuca",
            new AliasedBlockItem(ModBlocks.YUCA_CROP, new FabricItemSettings().food(ModFoods.YUCA)));
    public static final Item AJI_AMARILLO_SEEDS = registerItem("aji_amarillo_seeds",
            new AliasedBlockItem(ModBlocks.AJI_AMARILLO_CROP, new FabricItemSettings()));
    public static final Item GARLIC_CLOVE = registerItem("garlic_clove",
            new AliasedBlockItem(ModBlocks.GARLIC_CROP, new FabricItemSettings().food(ModFoods.GARLIC_CLOVE)));
    public static final Item CHICKPEA = registerItem("chickpea",
            new AliasedBlockItem(ModBlocks.CHICKPEA_CROP, new FabricItemSettings().food(ModFoods.CHICKPEA)));
    public static final Item PARSLEY_SEEDS = registerItem("parsley_seeds",
            new AliasedBlockItem(ModBlocks.PARSLEY_CROP, new FabricItemSettings()));


    public static final Item AJI_AMARILLO = registerItem("aji_amarillo",
            new Item(new FabricItemSettings().food(ModFoods.AJI_AMARILLO)));
    public static final Item PARSLEY = registerItem("parsley",
            new Item(new FabricItemSettings().food(ModFoods.PARSLEY)));


    public static final Item BROCCOLI = registerItem("broccoli",
            new Item(new FabricItemSettings().food(ModFoods.BROCCOLI)));
    public static final Item CAULIFLOWER = registerItem("cauliflower",
            new Item(new FabricItemSettings().food(ModFoods.CAULIFLOWER)));
    public static final Item ZUCCHINI = registerItem("zucchini",
            new Item(new FabricItemSettings().food(ModFoods.ZUCCHINI)));
    public static final Item TURNIP = registerItem("turnip",
            new Item(new FabricItemSettings().food(ModFoods.TURNIP)));
    public static final Item DANDELION_LEAF = registerItem("dandelion_leaf",
            new Item(new FabricItemSettings().food(ModFoods.DANDELION_LEAF)));



    public static final Item RICE_PANICLE = registerItem("rice_panicle",
            new Item(new FabricItemSettings()));
    public static final Item RICE = registerItem("rice",
            new RiceItem(ModBlocks.RICE_CROP, new FabricItemSettings()));

    public static final Item STRAW = registerItem("straw",
            new Item(new FabricItemSettings()));

    public static final Item DOG_FOOD = registerItem("dog_food",
            new DogFoodItem(new FabricItemSettings()
                    .food(ModFoods.DOG_FOOD)
                    .recipeRemainder(Items.BOWL)
                    .maxCount(16)));

    public static final Item HORSE_FEED = registerItem("horse_feed",
            new HorseFeedItem(new FabricItemSettings().maxCount(16)));

    public static final Item MELON_POPSICLE = registerItem("melon_popsicle",
            new PopsicleItem(new FabricItemSettings()
                    .food(ModFoods.POPSICLE)
                    .maxCount(16)));


    public static final Item ROTTEN_TOMATO = registerItem("rotten_tomato",
            new RottenTomatoItem(new FabricItemSettings()));

    public static final Item HONEY_COOKIE = registerItem("honey_cookie",
            new Item(new FabricItemSettings().food(ModFoods.COOKIES)));
    public static final Item SWEET_BERRY_COOKIE = registerItem("sweet_berry_cookie",
            new Item(new FabricItemSettings().food(ModFoods.COOKIES)));

    public static final Item APPLE_PIE_SLICE = registerItem("apple_pie_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIE_SLICE)));
    public static final Item CHOCOLATE_PIE_SLICE = registerItem("chocolate_pie_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIE_SLICE)));
    public static final Item SWEET_BERRY_CHEESECAKE_SLICE = registerItem("sweet_berry_cheesecake_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIE_SLICE)));

    public static final Item AVOCADO = registerItem("avocado",
            new Item(new FabricItemSettings().food(ModFoods.AVOCADO)));
    public static final Item CUCUMBER = registerItem("cucumber",
            new Item(new FabricItemSettings().food(ModFoods.CUCUMBER)));
    public static final Item PICKLE = registerItem("pickle",
            new Item(new FabricItemSettings().food(ModFoods.PICKLE)));
    public static final Item EGGPLANT = registerItem("eggplant",
            new Item(new FabricItemSettings().food(ModFoods.EGGPLANT)));
    public static final Item SMOKED_TOMATO = registerItem("smoked_tomato",
            new Item(new FabricItemSettings().food(ModFoods.SMOKED_TOMATO)));
    public static final Item SMOKED_EGGPLANT = registerItem("smoked_eggplant",
            new Item(new FabricItemSettings().food(ModFoods.SMOKED_EGGPLANT)));
    public static final Item SMOKED_WHITE_EGGPLANT = registerItem("smoked_white_eggplant",
            new Item(new FabricItemSettings().food(ModFoods.SMOKED_WHITE_EGGPLANT)));
    public static final Item WHITE_EGGPLANT = registerItem("white_eggplant",
            new Item(new FabricItemSettings().food(ModFoods.WHITE_EGGPLANT)));
    public static final Item CORN_COB = registerItem("corn_cob",
            new Item(new FabricItemSettings().food(ModFoods.CORN_COB)));
    public static final Item GINGER = registerItem("ginger",
            new Item(new FabricItemSettings().food(ModFoods.GINGER)));
    public static final Item SQUID = registerItem("squid",
            new Item(new FabricItemSettings().food(ModFoods.SQUID)));
    public static final Item COOKED_SQUID = registerItem("cooked_squid",
            new Item(new FabricItemSettings().food(ModFoods.COOKED_SQUID)));
    public static final Item GLOW_SQUID = registerItem("glow_squid",
            new Item(new FabricItemSettings().food(ModFoods.GLOW_SQUID)));
    public static final Item RAW_CALAMARI = registerItem("raw_calamari",
            new Item(new FabricItemSettings().food(ModFoods.RAW_CALAMARI)));
    public static final Item COOKED_CALAMARI = registerItem("cooked_calamari",
            new Item(new FabricItemSettings().food(ModFoods.COOKED_CALAMARI)));
    public static final Item CUT_AVOCADO = registerItem("cut_avocado",
            new Item(new FabricItemSettings().food(ModFoods.CUT_AVOCADO)));
    public static final Item CUT_CUCUMBER = registerItem("cut_cucumber",
            new Item(new FabricItemSettings().food(ModFoods.CUT_CUCUMBER)));
    public static final Item CUT_PICKLE = registerItem("cut_pickle",
            new Item(new FabricItemSettings().food(ModFoods.CUT_PICKLE)));
    public static final Item CUT_EGGPLANT = registerItem("cut_eggplant",
            new Item(new FabricItemSettings().food(ModFoods.CUT_EGGPLANT)));
    public static final Item SMOKED_CUT_EGGPLANT = registerItem("smoked_cut_eggplant",
            new Item(new FabricItemSettings().food(ModFoods.SMOKED_CUT_EGGPLANT)));
    public static final Item MIDORI_ROLL = registerItem("midori_roll",
            new Item(new FabricItemSettings().food(ModFoods.MIDORI_ROLL)));
    public static final Item MIDORI_ROLL_SLICE = registerItem("midori_roll_slice",
            new Item(new FabricItemSettings().food(ModFoods.MIDORI_ROLL_SLICE)));
    public static final Item EGG_ROLL = registerItem("egg_roll",
            new Item(new FabricItemSettings().food(ModFoods.EGG_ROLL)));
    public static final Item CHICKEN_ROLL = registerItem("chicken_roll",
            new Item(new FabricItemSettings().food(ModFoods.CHICKEN_ROLL)));
    public static final Item CHICKEN_ROLL_SLICE = registerItem("chicken_roll_slice",
            new Item(new FabricItemSettings().food(ModFoods.CHICKEN_ROLL_SLICE)));
    public static final Item PUFFERFISH_ROLL = registerItem("pufferfish_roll",
            new Item(new FabricItemSettings().food(ModFoods.PUFFERFISH_ROLL)));
    public static final Item TROPICAL_ROLL = registerItem("tropical_roll",
            new Item(new FabricItemSettings().food(ModFoods.TROPICAL_ROLL)));
    public static final Item RICE_BALL = registerItem("rice_ball",
            new Item(new FabricItemSettings().food(ModFoods.RICE_BALL)));
    public static final Item CALAMARI_ROLL = registerItem("calamari_roll",
            new Item(new FabricItemSettings().food(ModFoods.CALAMARI_ROLL)));


    public static final Item CORN_DOUGH = registerItem("corn_dough",
            new Item(new FabricItemSettings().food(ModFoods.CORN_DOUGH)));
    public static final Item TORTILLA = registerItem("tortilla",
            new Item(new FabricItemSettings().food(ModFoods.TORTILLA)));
    public static final Item POPCORN = registerItem("popcorn",
            new Item(new FabricItemSettings().food(ModFoods.POPCORN)));
    public static final Item TORTILLA_CHIPS = registerItem("tortilla_chips",
            new Item(new FabricItemSettings().food(ModFoods.TORTILLA_CHIPS)));
    public static final Item ELOTE = registerItem("elote",
            new Item(new FabricItemSettings().food(ModFoods.ELOTE)));
    public static final Item EMPANADA = registerItem("empanada",
            new Item(new FabricItemSettings().food(ModFoods.EMPANADA)));
    public static final Item BEEF_BURRITO = registerItem("beef_burrito",
            new Item(new FabricItemSettings().food(ModFoods.BEEF_BURRITO)));
    public static final Item MUTTON_SANDWICH = registerItem("mutton_sandwich",
            new Item(new FabricItemSettings().food(ModFoods.MUTTON_SANDWICH)));
    public static final Item AVOCADO_TOAST = registerItem("avocado_toast",
            new Item(new FabricItemSettings().food(ModFoods.AVOCADO_TOAST)));

    public static final Item CREAMED_CORN = registerItem("creamed_corn",
            new ConsumableItem(new Item.Settings().maxCount(16).food(ModFoods.CREAMED_CORN)
                    .recipeRemainder(Items.BOWL).maxCount(16), false));
    public static final Item HEARTY_SALAD = registerItem("hearty_salad",
            new ConsumableItem(new Item.Settings().maxCount(16).food(ModFoods.HEARTY_SALAD)
                    .recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item SPICY_CURRY = registerItem("spicy_curry",
            new ConsumableItem(new Item.Settings().maxCount(16).food(ModFoods.SPICY_CURRY)
                    .recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item POACHED_EGGPLANTS = registerItem("poached_eggplants",
            new ConsumableItem(new Item.Settings().maxCount(16).food(ModFoods.POACHED_EGGPLANTS)
                    .recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item EGGPLANT_PARMESAN = registerItem("eggplant_parmesan",
            new ConsumableItem(new Item.Settings().maxCount(16).food(ModFoods.EGGPLANT_PARMESAN)
                    .recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item EGGPLANT_BURGER = registerItem("eggplant_burger",
            new Item(new Item.Settings().food(ModFoods.EGGPLANT_BURGER)));

    public static final Item CHICKEN_TACO = registerItem("chicken_taco",
            new Item(new FabricItemSettings().food(ModFoods.CHICKEN_TACO)));
    public static final Item PORK_WRAP = registerItem("pork_wrap",
            new Item(new FabricItemSettings().food(ModFoods.PORK_WRAP)));
    public static final Item FISH_TACO = registerItem("fish_taco",
            new Item(new FabricItemSettings().food(ModFoods.FISH_TACO)));

    public static final Item QUICHE_SLICE = registerItem("quiche_slice",
            new Item(new FabricItemSettings().food(ModFoods.QUICHE_SLICE)));

     public static final Item PIZZA_SLICE = registerItem("pizza_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIZZA_SLICE)));

    public static final Item FIERY_FONDUE = registerItem("fiery_fondue",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.FIERY_FONDUE).recipeRemainder(Items.BOWL), true));

    public static final Item VEGETABLE_OMELET = registerItem("vegetable_omelet",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.VEGETABLE_OMELET).recipeRemainder(Items.BOWL), true));
    public static final Item CREAMY_ONION_SOUP = registerItem("creamy_onion_soup",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.CREAMY_ONION_SOUP).recipeRemainder(Items.BOWL), true));
    public static final Item CHEESY_PASTA = registerItem("cheesy_pasta",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.CHEESY_PASTA).recipeRemainder(Items.BOWL), true));
    public static final Item HORROR_LASAGNA = registerItem("horror_lasagna",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.HORROR_LASAGNA).recipeRemainder(Items.BOWL), true));
    public static final Item SCARLET_PIEROGI = registerItem("scarlet_pierogi",
            new ConsumableItem(new FabricItemSettings()
                    .maxCount(16).food(ModFoods.SCARLET_PIEROGI).recipeRemainder(Items.BOWL), true));

    public static final Item FLAXEN_CHEESE_WEDGE = registerItem("flaxen_cheese_wedge",
            new Item(new FabricItemSettings().food(ModFoods.FLAXEN_CHEESE)));
    public static final Item SCARLET_CHEESE_WEDGE = registerItem("scarlet_cheese_wedge",
            new Item(new FabricItemSettings().food(ModFoods.SCARLET_CHEESE)));
    public static final Item DORBLU_CHEESE_WEDGE = registerItem("dorblu_cheese_wedge",
            new Item(new FabricItemSettings().food(ModFoods.DORBLU_CHEESE)));

    public static final Item HAM_AND_CHEESE_SANDWICH = registerItem("ham_and_cheese_sandwich",
            new Item(new FabricItemSettings().food(ModFoods.HAM_AND_CHEESE_SANDWICH)));

    public static final Item KIMCHI = registerItem("kimchi",
            new Item(new FabricItemSettings().food(ModFoods.KIMCHI)));
    public static final Item JERKY = registerItem("jerky",
            new Item(new FabricItemSettings().food(ModFoods.JERKY)));
    public static final Item PICKLED_PICKLES = registerItem("pickled_pickles",
            new Item(new FabricItemSettings().food(ModFoods.PICKLED_PICKLES)));
    public static final Item KIPPERS = registerItem("kippers",
            new Item(new FabricItemSettings().food(ModFoods.KIPPERS)));
    public static final Item COCOA_FUDGE = registerItem("cocoa_fudge",
            new Item(new FabricItemSettings().food(ModFoods.COCOA_FUDGE)));

    public static final Item SWEET_BERRY_JAM = registerItem("sweet_berry_jam",
            new JamJarItem(new FabricItemSettings().maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)
                    .food(ModFoods.SWEET_BERRY_JAM)));
    public static final Item GLOW_BERRY_MARMALADE = registerItem("glow_berry_marmalade",
            new JamJarItem(new FabricItemSettings().maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)
                    .food(ModFoods.GLOW_BERRY_MARMALADE)));
    public static final Item APPLE_JELLY = registerItem("apple_jelly",
            new JamJarItem(new FabricItemSettings().maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)
                    .food(ModFoods.APPLE_JELLY)));




    public static final Item COTTON_BOLL = registerItem("cotton_boll",
            new Item(new FabricItemSettings()));
    public static final Item BELL_PEPPER_GREEN = registerItem("bell_pepper_green",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER)));
    public static final Item BELL_PEPPER_YELLOW = registerItem("bell_pepper_yellow",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER)));
    public static final Item BELL_PEPPER_RED = registerItem("bell_pepper_red",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER)));

    public static final Item ROASTED_COFFEE_BEANS = registerItem("roasted_coffee_beans",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_COFFEE_BEANS)));
    public static final Item GOLDEN_COFFEE_BEANS = registerItem("golden_coffee_beans",
            new Item(new FabricItemSettings().food(ModFoods.GOLDEN_COFFEE_BEANS)));

    public static final Item ROASTED_BELL_PEPPER_GREEN = registerItem("roasted_bell_pepper_green",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER)));
    public static final Item ROASTED_BELL_PEPPER_YELLOW = registerItem("roasted_bell_pepper_yellow",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER)));
    public static final Item ROASTED_BELL_PEPPER_RED = registerItem("roasted_bell_pepper_red",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER)));
    public static final Item COFFEE = registerItem("coffee",
              new DrinkableItem(new FabricItemSettings().food(ModFoods.COFFEE)
                      .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16), true));
    public static final Item MILK_COFFEE = registerItem("milk_coffee",
              new MilkCoffeeItem(new FabricItemSettings().food(ModFoods.MILK_COFFEE)
                      .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item CHOCOLATE_COFFEE = registerItem("chocolate_coffee",
              new ChocolateCoffeeItem(new FabricItemSettings().food(ModFoods.CHOCOLATE_COFFEE)
                      .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item HONEY_COFFEE = registerItem("honey_coffee",
            new MilkCoffeeItem(new FabricItemSettings().food(ModFoods.HONEY_COFFEE)
                    .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item SYRUP_COFFEE = registerItem("syrup_coffee",
            new MilkCoffeeItem(new FabricItemSettings().food(ModFoods.SYRUP_COFFEE)
                    .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item DARK_COFFEE = registerItem("dark_coffee",
            new DrinkableItem(new FabricItemSettings().food(ModFoods.DARK_COFFEE)
                    .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16), true));

    public static final Item COOKING_OIL = registerItem("cooking_oil",
            new DrinkableItem(new FabricItemSettings().food(ModFoods.COOKING_OIL)
                    .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item SYRUP = registerItem("syrup",
            new DrinkableItem(new FabricItemSettings().food(ModFoods.SYRUP)
                    .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16), true));
    public static final Item BATTER = registerItem("batter",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.BATTER)
                    .recipeRemainder(Items.BOWL).maxCount(16), true));


    public static final Item POTATO_SLICES = registerItem("potato_slices",
            new Item(new FabricItemSettings().food(ModFoods.POTATO_SLICES)));
    public static final Item BAKED_POTATO_SLICES = registerItem("baked_potato_slices",
            new Item(new FabricItemSettings().food(ModFoods.BAKED_POTATO_SLICES)));

    public static final Item BELL_PEPPER_SLICE_GREEN = registerItem("bell_pepper_slice_green",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_SLICE)));
    public static final Item BELL_PEPPER_SLICE_YELLOW = registerItem("bell_pepper_slice_yellow",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_SLICE)));
    public static final Item BELL_PEPPER_SLICE_RED = registerItem("bell_pepper_slice_red",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_SLICE)));

    public static final Item ROASTED_BELL_PEPPER_SLICE_GREEN = registerItem("roasted_bell_pepper_slice_green",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER_SLICE)));
    public static final Item ROASTED_BELL_PEPPER_SLICE_YELLOW = registerItem("roasted_bell_pepper_slice_yellow",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER_SLICE)));
    public static final Item ROASTED_BELL_PEPPER_SLICE_RED = registerItem("roasted_bell_pepper_slice_red",
            new Item(new FabricItemSettings().food(ModFoods.ROASTED_BELL_PEPPER_SLICE)));

    public static final Item CALAMARI_SLICE = registerItem("calamari_slice",
            new Item(new FabricItemSettings().food(ModFoods.CALAMARI_SLICE)));
    public static final Item COOKED_CALAMARI_SLICE = registerItem("cooked_calamari_slice",
            new Item(new FabricItemSettings().food(ModFoods.COOKED_CALAMARI_SLICE)));

    public static final Item SYRUP_CHEESECAKE_SLICE = registerItem("syrup_cheesecake_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIE_SLICE)));
    public static final Item CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerItem("cherry_blossom_cheesecake_slice",
            new Item(new FabricItemSettings().food(ModFoods.PIE_SLICE)));

    public static final Item SYRUP_COOKIE = registerItem("syrup_cookie",
            new Item(new FabricItemSettings().food(ModFoods.COOKIES)));
    public static final Item CHERRY_BLOSSOM_COOKIE = registerItem("cherry_blossom_cookie",
            new Item(new FabricItemSettings().food(ModFoods.COOKIES)));
    public static final Item COFFEE_COOKIE = registerItem("coffee_cookie",
            new Item(new FabricItemSettings().food(ModFoods.COOKIES)));

    public static final Item SYRUP_SANDWICH = registerItem("syrup_sandwich",
            new Item(new FabricItemSettings().food(ModFoods.SYRUP_SANDWICH)));
    public static final Item FRUIT_BEIGNET = registerItem("fruit_beignet",
            new Item(new FabricItemSettings().food(ModFoods.FRUIT_BEIGNET)));

    public static final Item PANCAKE = registerItem("pancake",
            new Item(new FabricItemSettings().food(ModFoods.PANCAKE)));
    public static final Item HONEY_PANCAKE = registerItem("honey_pancake",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.HONEY_PANCAKE), true));
    public static final Item CHOCOLATE_PANCAKE = registerItem("chocolate_pancake",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.CHOCOLATE_PANCAKE), true));
    public static final Item CHERRY_BLOSSOM_PANCAKE = registerItem("cherry_blossom_pancake",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.CHERRY_BLOSSOM_PANCAKE), true));
    public static final Item VEGETABLE_PANCAKE = registerItem("vegetable_pancake",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.VEGETABLE_PANCAKE), true));
    public static final Item PUMPKIN_PANCAKE = registerItem("pumpkin_pancake",
            new ConsumableItem(new FabricItemSettings().food(ModFoods.PUMPKIN_PANCAKE), true));

    public static final Item POTATO_SALAD = registerItem("potato_salad",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.POTATO_SALAD).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item SWEET_SALAD = registerItem("sweet_salad",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.SWEET_SALAD).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item BELL_PEPPER_SOUP = registerItem("bell_pepper_soup",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.BELL_PEPPER_SOUP).recipeRemainder(Items.BOWL).maxCount(16), true));

    public static final Item BELL_PEPPER_PASTA = registerItem("bell_pepper_pasta",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.BELL_PEPPER_PASTA).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item FRIED_CALAMARI = registerItem("fried_calamari",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.FRIED_CALAMARI).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item FRIED_CHICKEN = registerItem("fried_chicken",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.FRIED_CHICKEN).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item FRIED_MUSHROOMS = registerItem("fried_mushrooms",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.FRIED_MUSHROOMS).recipeRemainder(Items.BOWL).maxCount(16), true));
    public static final Item COFFEE_BRAISED_BEEF = registerItem("coffee_braised_beef",
            new ConsumableItem(new FabricItemSettings()
                    .food(ModFoods.COFFEE_BRAISED_BEEF).recipeRemainder(Items.BOWL).maxCount(16), true));


    public static final Item STUFFED_BELL_PEPPER_GREEN = registerItem("stuffed_bell_pepper_green",
            new Item(new FabricItemSettings().food(ModFoods.STUFFED_BELL_PEPPER)));
    public static final Item STUFFED_BELL_PEPPER_YELLOW = registerItem("stuffed_bell_pepper_yellow",
            new Item(new FabricItemSettings().food(ModFoods.STUFFED_BELL_PEPPER)));
    public static final Item STUFFED_BELL_PEPPER_RED = registerItem("stuffed_bell_pepper_red",
            new Item(new FabricItemSettings().food(ModFoods.STUFFED_BELL_PEPPER)));


    public static final Item FRIED_DOUGH = registerItem("fried_dough",
            new Item(new FabricItemSettings().food(ModFoods.FRIED_DOUGH)));
    public static final Item FRIED_DUMPLINGS = registerItem("fried_dumplings",
            new Item(new FabricItemSettings().food(ModFoods.FRIED_DUMPLINGS)));
    public static final Item SPRING_ROLLS = registerItem("spring_rolls",
            new Item(new FabricItemSettings().food(ModFoods.SPRING_ROLLS)));

    public static final Item BELL_PEPPER_ROLL_GREEN = registerItem("bell_pepper_roll_green",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_ROLL)));
    public static final Item BELL_PEPPER_ROLL_YELLOW = registerItem("bell_pepper_roll_yellow",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_ROLL)));
    public static final Item BELL_PEPPER_ROLL_RED = registerItem("bell_pepper_roll_red",
            new Item(new FabricItemSettings().food(ModFoods.BELL_PEPPER_ROLL)));
    public static final Item SLICED_CALAMARI_ROLL = registerItem("sliced_calamari_roll",
            new Item(new FabricItemSettings().food(ModFoods.SLICED_CALAMARI_ROLL)));
    public static final Item CHERRY_BLOSSOM_ROLL = registerItem("cherry_blossom_roll",
            new Item(new FabricItemSettings().food(ModFoods.CHERRY_BLOSSOM_ROLL)));







    public static final Item CRUDE_PICKAXE = registerItem("crude_pickaxe",
            new PickaxeItem(ModToolMaterial.CRUDE, 1, -2.8f, new FabricItemSettings()));
    public static final Item CRUDE_AXE = registerItem("crude_axe",
            new AxeItem(ModToolMaterial.CRUDE, 6, -3.0f, new FabricItemSettings()));
    public static final Item CRUDE_SHOVEL = registerItem("crude_shovel",
            new ShovelItem(ModToolMaterial.CRUDE, 1, -3.0f, new FabricItemSettings()));
    public static final Item CRUDE_HOE = registerItem("crude_hoe",
            new HoeItem(ModToolMaterial.CRUDE, -1, -1.0f, new FabricItemSettings()));

    //TODO fixing the values

    public static final Item EDHEL_STEEL_PICKAXE = registerItem("edhel_steel_pickaxe",
            new PickaxeItem(ModToolMaterial.EDHEL_STEEL, 1, -2.8f, new FabricItemSettings()));
    public static final Item EDHEL_STEEL_AXE = registerItem("edhel_steel_axe",
            new AxeItem(ModToolMaterial.EDHEL_STEEL, 6, -3.0f, new FabricItemSettings()));
    public static final Item EDHEL_STEEL_SHOVEL = registerItem("edhel_steel_shovel",
            new ShovelItem(ModToolMaterial.EDHEL_STEEL, 2, -3.0f, new FabricItemSettings()));
    public static final Item EDHEL_STEEL_HOE = registerItem("edhel_steel_hoe",
            new HoeItem(ModToolMaterial.EDHEL_STEEL, -1, -1.0f, new FabricItemSettings()));
    public static final Item EDHEL_STEEL_SWORD = registerItem("edhel_steel_sword",
            new CustomSwordItem(ModToolMaterial.EDHEL_STEEL, 3, -2.4f, new FabricItemSettings()));

    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe",
            new PickaxeItem(ModToolMaterial.MITHRIL, 1, -2.7f,
                    new FabricItemSettings().fireproof()));
    public static final Item MITHRIL_AXE = registerItem("mithril_axe",
            new AxeItem(ModToolMaterial.MITHRIL, 6, -3.0f,
                    new FabricItemSettings().fireproof()));
    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel",
            new ShovelItem(ModToolMaterial.MITHRIL, 2, -3.0f,
                    new FabricItemSettings().fireproof()));
    public static final Item MITHRIL_HOE = registerItem("mithril_hoe",
            new HoeItem(ModToolMaterial.MITHRIL, -2, -1.0f,
                    new FabricItemSettings().fireproof()));


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
            new KnifeItem(ModToolMaterial.BRONZE, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item IRON_KNIFE = registerItem("iron_knife",
            new KnifeItem(ToolMaterials.IRON, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item GOLDEN_KNIFE = registerItem("golden_knife",
            new KnifeItem(ToolMaterials.GOLD, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item DIAMOND_KNIFE = registerItem("diamond_knife",
            new KnifeItem(ToolMaterials.DIAMOND, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item NETHERITE_KNIFE = registerItem("netherite_knife",
            new KnifeItem(ToolMaterials.NETHERITE, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item FLINT_KNIFE = registerItem("flint_knife",
            new KnifeItem(ModToolMaterial.FLINT, 0.5f, -2.0f, new FabricItemSettings()));
    public static final Item STONE_KNIFE = registerItem("stone_knife",
            new KnifeItem(ToolMaterials.STONE, 0.5f, -2.0f, new FabricItemSettings()));





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
