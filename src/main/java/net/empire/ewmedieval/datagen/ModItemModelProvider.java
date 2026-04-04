package net.empire.ewmedieval.datagen;

import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModItemModelProvider extends FabricModelProvider {

    public ModItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public String getName() {
        return "Ew medieval Item Models";
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(ModItems.KHAZAD_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.KHAZAD_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURZUM_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURZUM_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LEAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEAD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEAD_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_MITHRIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_MIXTURE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_IRON_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_ASH_MIXTURE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_HORSE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SWAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HORSE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SWAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_GOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAT_WING, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_BAT_WING, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_WOLF, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_WOLF, Models.GENERATED);

        itemModelGenerator.register(ModItems.LEMBAS, Models.GENERATED);

        itemModelGenerator.register(ModItems.CAKE_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_PUMPKIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROAST_CHICKEN, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_STEAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRANBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIG, Models.GENERATED);
        itemModelGenerator.register(ModItems.KIWI, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGO, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAKED_PEAR, Models.GENERATED);

        itemModelGenerator.register(ModItems.ARKENSTONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASH_PIECE, Models.GENERATED);

        itemModelGenerator.register(ModItems.STONE_PEBBLE, Models.GENERATED);

        itemModelGenerator.register(ModItems.CRUDE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRUDE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRUDE_SHOVEL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.EDHEL_STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EDHEL_STEEL_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MITHRIL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRIL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRIL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRIL_AXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BRONZE_SHEARS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_KNIFE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BRONZE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_SHOVEL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.IRON_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLINT_KNIFE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINCED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.CABBAGE_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CABBAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ONION, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE_PANICLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_CUTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTTON_CHOPS, Models.GENERATED);

        itemModelGenerator.register(ModItems.TOMATO_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CABBAGE_ROLLS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAMBURGER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.RATATOUILLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MIXED_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.BARBECUE_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_MUTTON_CHOPS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CHICKEN_CUTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_COD_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SALMON_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIE_CRUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_PATTY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_HAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.COD_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHEAT_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGG_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.CABBAGE_LEAF, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KELP_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.KELP_ROLL_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.FISH_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAKED_COD_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.NOODLE_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.BACON_AND_EGGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEAK_AND_POTATOES, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_NOODLES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BONE_BROTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PASTA_WITH_MEATBALLS, Models.GENERATED);
        itemModelGenerator.register(ModItems.PASTA_WITH_MUTTON_CHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSHROOM_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_MUTTON_CHOPS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SQUID_INK_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRILLED_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_GLAZED_HAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO_SAUCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHEPHERDS_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BACON_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTTON_WRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUMPLINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COD_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_POPSICLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_TOMATO, Models.GENERATED);

        itemModelGenerator.register(ModItems.MELON_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_CIDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOT_COCOA, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_CUSTARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_PIE_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_PIE_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_CHEESECAKE_SLICE, Models.GENERATED);

        itemModelGenerator.register(ModItems.MILK_BOTTLE, Models.GENERATED);


        itemModelGenerator.register(ModItems.BAMBOO_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_OAK_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.JUNGLE_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGROVE_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ACACIA_BARK, Models.GENERATED);



        itemModelGenerator.register(ModItems.ROPE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_JUG, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_CHALICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOG_FOOD, Models.GENERATED);
        itemModelGenerator.register(ModItems.HORSE_FEED, Models.GENERATED);

        itemModelGenerator.register(ModItems.CUCUMBER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGGPLANT_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_KERNELS, Models.GENERATED);

        itemModelGenerator.register(ModItems.AVOCADO, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUCUMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.PICKLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_WHITE_EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHITE_EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_COB, Models.GENERATED);
        itemModelGenerator.register(ModItems.GINGER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUT_AVOCADO, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUT_CUCUMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUT_PICKLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUT_EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_CUT_EGGPLANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MIDORI_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MIDORI_ROLL_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGG_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_ROLL_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUFFERFISH_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TROPICAL_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.TORTILLA, Models.GENERATED);
        itemModelGenerator.register(ModItems.POPCORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TORTILLA_CHIPS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELOTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPANADA, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_BURRITO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTTON_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.AVOCADO_TOAST, Models.GENERATED);
        itemModelGenerator.register(ModItems.CREAMED_CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEARTY_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPICY_CURRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.POACHED_EGGPLANTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_TACO, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORK_WRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.FISH_TACO, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_TOMATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGGPLANT_BURGER, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGGPLANT_PARMESAN, Models.GENERATED);


        itemModelGenerator.register(ModItems.QUICHE_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIZZA_SLICE, Models.GENERATED);

        itemModelGenerator.register(ModItems.FIERY_FONDUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLAXEN_CHEESE_WEDGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCARLET_CHEESE_WEDGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DORBLU_CHEESE_WEDGE, Models.GENERATED);



        itemModelGenerator.register(ModItems.VEGETABLE_OMELET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CREAMY_ONION_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESY_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.HORROR_LASAGNA, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCARLET_PIEROGI, Models.GENERATED);


        itemModelGenerator.register(ModItems.HAM_AND_CHEESE_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.KIMCHI, Models.GENERATED);
        itemModelGenerator.register(ModItems.JERKY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PICKLED_PICKLES, Models.GENERATED);
        itemModelGenerator.register(ModItems.KIPPERS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COCOA_FUDGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_MARMALADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JELLY, Models.GENERATED);

        itemModelGenerator.register(ModItems.PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MILK_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKING_OIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BATTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CALAMARI_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLICED_CALAMARI_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_BRAISED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SLICE_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SLICE_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SLICE_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTATO_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAKED_POTATO_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTATO_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_ROLL_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_ROLL_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_ROLL_YELLOW, Models.GENERATED);

        itemModelGenerator.register(ModItems.SYRUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SYRUP_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_SLICE_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_MUSHROOMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SYRUP_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_BEIGNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_DUMPLINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRING_ROLLS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_ROLL, Models.GENERATED);

        itemModelGenerator.register(ModItems.BELL_PEPPER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COTTON_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COTTON_BOLL, Models.GENERATED);

        itemModelGenerator.register(ModItems.ZUCCHINI, Models.GENERATED);
        itemModelGenerator.register(ModItems.ZUCCHINI_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROCCOLI, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROCCOLI_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.TURNIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.TURNIP_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.DANDELION_LEAF, Models.GENERATED);





    }
}
