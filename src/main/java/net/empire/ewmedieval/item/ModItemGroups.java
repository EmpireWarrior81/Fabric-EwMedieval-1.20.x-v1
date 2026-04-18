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
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.artifacts"))
                    .icon(() -> new ItemStack(ModItems.ARKENSTONE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ARKENSTONE);
                        entries.add(ModItems.FUR);
                        entries.add(ModItems.STONE_PEBBLE);
                        entries.add(ModItems.ASH);
                        entries.add(ModItems.ASH_PIECE);
                        entries.add(ModItems.ROPE);
                        entries.add(ModItems.GLASS_JUG);
                        entries.add(ModItems.GLASS_CHALICE);
                    }).build());

    public static final ItemGroup METALS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "metals"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.metals"))
                    .icon(() -> new ItemStack(ModItems.TIN_INGOT))
                    .entries((displayContext, entries) -> {
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
                        entries.add(ModItems.BRONZE_ASH_MIXTURE);
                        entries.add(ModItems.CRUDE_INGOT);
                        entries.add(ModItems.CRUDE_NUGGET);
                        entries.add(ModItems.RAW_LEAD);
                        entries.add(ModItems.LEAD_INGOT);
                        entries.add(ModItems.LEAD_NUGGET);
                        entries.add(ModItems.RAW_MITHRIL);
                        entries.add(ModItems.MITHRIL_INGOT);
                        entries.add(ModItems.MITHRIL_NUGGET);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.STEEL_NUGGET);
                        entries.add(ModItems.RAW_IRON_NUGGET);
                        entries.add(ModItems.BRONZE_MIXTURE);
                        entries.add(ModItems.BRONZE_NUGGET);
                        entries.add(ModItems.BRONZE_INGOT);
                    }).build());

    public static final ItemGroup TOOLS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "tools"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.tools"))
                    .icon(() -> new ItemStack(ModItems.BRONZE_PICKAXE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CRUDE_PICKAXE);
                        entries.add(ModItems.CRUDE_AXE);
                        entries.add(ModItems.CRUDE_SHOVEL);
                        entries.add(ModItems.CRUDE_HOE);
                        entries.add(ModItems.BRONZE_PICKAXE);
                        entries.add(ModItems.BRONZE_AXE);
                        entries.add(ModItems.BRONZE_SHOVEL);
                        entries.add(ModItems.BRONZE_HOE);
                        entries.add(ModItems.BRONZE_SHEARS);
                        entries.add(ModItems.BRONZE_KNIFE);
                        entries.add(ModItems.FLINT_KNIFE);
                        entries.add(ModItems.STONE_KNIFE);
                        entries.add(ModItems.IRON_KNIFE);
                        entries.add(ModItems.GOLDEN_KNIFE);
                        entries.add(ModItems.DIAMOND_KNIFE);
                        entries.add(ModItems.NETHERITE_KNIFE);
                        entries.add(ModItems.MITHRIL_AXE);
                        entries.add(ModItems.MITHRIL_SHOVEL);
                        entries.add(ModItems.MITHRIL_PICKAXE);
                        entries.add(ModItems.MITHRIL_HOE);
                        entries.add(ModItems.EDHEL_STEEL_HOE);
                        entries.add(ModItems.EDHEL_STEEL_AXE);
                        entries.add(ModItems.EDHEL_STEEL_PICKAXE);
                        entries.add(ModItems.EDHEL_STEEL_SHOVEL);
                        entries.add(ModItems.EDHEL_STEEL_SWORD);
                    }).build());

    public static final ItemGroup FOOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "food"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.food"))
                    .icon(() -> new ItemStack(ModItems.LEMBAS))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.RAW_HORSE);
                        entries.add(ModItems.COOKED_HORSE);
                        entries.add(ModItems.RAW_SWAN);
                        entries.add(ModItems.COOKED_SWAN);
                        entries.add(ModItems.RAW_GOAT);
                        entries.add(ModItems.COOKED_GOAT);
                        entries.add(ModItems.BAT_WING);
                        entries.add(ModItems.SMOKED_BAT_WING);
                        entries.add(ModItems.RAW_WOLF);
                        entries.add(ModItems.COOKED_WOLF);
                        entries.add(ModItems.BACON);
                        entries.add(ModItems.COOKED_BACON);
                        entries.add(ModItems.MUTTON_CHOPS);
                        entries.add(ModItems.COOKED_MUTTON_CHOPS);
                        entries.add(ModItems.CHICKEN_CUTS);
                        entries.add(ModItems.COOKED_CHICKEN_CUTS);
                        entries.add(ModItems.COD_SLICE);
                        entries.add(ModItems.COOKED_COD_SLICE);
                        entries.add(ModItems.SALMON_SLICE);
                        entries.add(ModItems.COOKED_SALMON_SLICE);
                        entries.add(ModItems.HAM);
                        entries.add(ModItems.SMOKED_HAM);
                        entries.add(ModItems.MINCED_BEEF);
                        entries.add(ModItems.BEEF_PATTY);


                        entries.add(ModItems.CRANBERRY);
                        entries.add(ModItems.FIG);
                        entries.add(ModItems.KIWI);
                        entries.add(ModItems.LEMON);
                        entries.add(ModItems.MANGO);
                        entries.add(ModItems.ORANGE);
                        entries.add(ModItems.PEACH);
                        entries.add(ModItems.PEAR);
                        entries.add(ModItems.BAKED_PEAR);


                        entries.add(ModItems.LEMBAS);
                        entries.add(ModItems.CACTUS_FLESH);
                        entries.add(ModItems.CACTUS_STEAK);
                        entries.add(ModItems.CAKE_SLICE);
                        entries.add(ModItems.PUMPKIN_SLICE);
                        entries.add(ModItems.PIE_CRUST);
                        entries.add(ModItems.FRIED_EGG);
                        entries.add(ModItems.WHEAT_DOUGH);
                        entries.add(ModItems.RAW_PASTA);
                        entries.add(ModItems.CABBAGE_LEAF);

                        entries.add(ModItems.BARBECUE_STICK);
                        entries.add(ModItems.EGG_SANDWICH);
                        entries.add(ModItems.CHICKEN_SANDWICH);
                        entries.add(ModItems.HAMBURGER);
                        entries.add(ModItems.BACON_SANDWICH);
                        entries.add(ModItems.MUTTON_WRAP);
                        entries.add(ModItems.DUMPLINGS);
                        entries.add(ModItems.STUFFED_POTATO);
                        entries.add(ModItems.CABBAGE_ROLLS);
                        entries.add(ModItems.SALMON_ROLL);
                        entries.add(ModItems.COD_ROLL);
                        entries.add(ModItems.KELP_ROLL);
                        entries.add(ModItems.KELP_ROLL_SLICE);
                        entries.add(ModItems.MIXED_SALAD);
                        entries.add(ModItems.NETHER_SALAD);
                        entries.add(ModItems.FRUIT_SALAD);

                        entries.add(ModItems.COOKED_RICE);
                        entries.add(ModItems.BONE_BROTH);
                        entries.add(ModItems.BEEF_STEW);
                        entries.add(ModItems.VEGETABLE_SOUP);
                        entries.add(ModItems.FISH_STEW);
                        entries.add(ModItems.CHICKEN_SOUP);
                        entries.add(ModItems.FRIED_RICE);
                        entries.add(ModItems.PUMPKIN_SOUP);
                        entries.add(ModItems.BAKED_COD_STEW);
                        entries.add(ModItems.NOODLE_SOUP);

                        entries.add(ModItems.BACON_AND_EGGS);
                        entries.add(ModItems.RATATOUILLE);
                        entries.add(ModItems.STEAK_AND_POTATOES);
                        entries.add(ModItems.PASTA_WITH_MEATBALLS);
                        entries.add(ModItems.PASTA_WITH_MUTTON_CHOP);
                        entries.add(ModItems.MUSHROOM_RICE);
                        entries.add(ModItems.ROASTED_MUTTON_CHOPS);
                        entries.add(ModItems.VEGETABLE_NOODLES);
                        entries.add(ModItems.SQUID_INK_PASTA);
                        entries.add(ModItems.GRILLED_SALMON);
                        entries.add(ModItems.GLOW_BERRY_CUSTARD);

                        entries.add(ModItems.STUFFED_PUMPKIN);
                        entries.add(ModItems.ROAST_CHICKEN);
                        entries.add(ModItems.HONEY_GLAZED_HAM);
                        entries.add(ModItems.SHEPHERDS_PIE);

                        entries.add(ModItems.APPLE_CIDER);
                        entries.add(ModItems.MELON_JUICE);
                        entries.add(ModItems.HOT_COCOA);
                        entries.add(ModItems.MELON_POPSICLE);
                        entries.add(ModItems.TOMATO_SAUCE);
                        entries.add(ModItems.DOG_FOOD);
                        entries.add(ModItems.MILK_BOTTLE);
                        entries.add(ModItems.SWEET_BERRY_COOKIE);
                        entries.add(ModItems.HONEY_COOKIE);
                        entries.add(ModItems.APPLE_PIE_SLICE);
                        entries.add(ModBlocks.APPLE_PIE);
                        entries.add(ModItems.CHOCOLATE_PIE_SLICE);
                        entries.add(ModBlocks.CHOCOLATE_PIE);
                        entries.add(ModItems.SWEET_BERRY_CHEESECAKE_SLICE);
                        entries.add(ModBlocks.SWEET_BERRY_CHEESECAKE);
                        entries.add(ModBlocks.ROAST_CHICKEN_BLOCK);
                        entries.add(ModBlocks.STUFFED_PUMPKIN_BLOCK);
                        entries.add(ModBlocks.SHEPHERDS_PIE_BLOCK);
                        entries.add(ModBlocks.HONEY_GLAZED_HAM_BLOCK);
                        entries.add(ModBlocks.RICE_ROLL_MEDLEY_BLOCK);
                        entries.add(ModItems.HORSE_FEED);

                        entries.add(ModItems.PICKLE);
                        entries.add(ModItems.SMOKED_EGGPLANT);
                        entries.add(ModItems.SMOKED_TOMATO);
                        entries.add(ModItems.SMOKED_WHITE_EGGPLANT);
                        entries.add(ModItems.GINGER);
                        entries.add(ModItems.SQUID);
                        entries.add(ModItems.COOKED_SQUID);
                        entries.add(ModItems.GLOW_SQUID);
                        entries.add(ModItems.RAW_CALAMARI);
                        entries.add(ModItems.COOKED_CALAMARI);
                        entries.add(ModItems.CUT_AVOCADO);
                        entries.add(ModItems.CUT_CUCUMBER);
                        entries.add(ModItems.CUT_PICKLE);
                        entries.add(ModItems.CUT_EGGPLANT);
                        entries.add(ModItems.SMOKED_CUT_EGGPLANT);
                        entries.add(ModItems.MIDORI_ROLL);
                        entries.add(ModItems.MIDORI_ROLL_SLICE);
                        entries.add(ModItems.EGG_ROLL);
                        entries.add(ModItems.CHICKEN_ROLL);
                        entries.add(ModItems.CHICKEN_ROLL_SLICE);
                        entries.add(ModItems.PUFFERFISH_ROLL);
                        entries.add(ModItems.TROPICAL_ROLL);
                        entries.add(ModItems.RICE_BALL);
                        entries.add(ModItems.CALAMARI_ROLL);
                        entries.add(ModItems.CORN_DOUGH);
                        entries.add(ModItems.TORTILLA);
                        entries.add(ModItems.POPCORN);
                        entries.add(ModItems.TORTILLA_CHIPS);
                        entries.add(ModItems.ELOTE);
                        entries.add(ModItems.EMPANADA);
                        entries.add(ModItems.BEEF_BURRITO);
                        entries.add(ModItems.MUTTON_SANDWICH);
                        entries.add(ModItems.AVOCADO_TOAST);
                        entries.add(ModItems.CREAMED_CORN);
                        entries.add(ModItems.CHICKEN_TACO);
                        entries.add(ModItems.PORK_WRAP);
                        entries.add(ModItems.FISH_TACO);
                        entries.add(ModItems.HEARTY_SALAD);
                        entries.add(ModItems.EGGPLANT_PARMESAN);
                        entries.add(ModItems.EGGPLANT_BURGER);
                        entries.add(ModItems.SPICY_CURRY);
                        entries.add(ModItems.POACHED_EGGPLANTS);
                        entries.add(ModBlocks.EGGPLANT_PARMESAN_BLOCK);
                        entries.add(ModBlocks.EXOTIC_ROLL_MEDLEY);

                        entries.add(ModBlocks.QUICHE);
                        entries.add(ModBlocks.PIZZA);
                        entries.add(ModBlocks.FIERY_FONDUE_POT);
                        entries.add(ModItems.QUICHE_SLICE);
                        entries.add(ModItems.PIZZA_SLICE);
                        entries.add(ModItems.FIERY_FONDUE);
                        entries.add(ModItems.FLAXEN_CHEESE_WEDGE);
                        entries.add(ModItems.SCARLET_CHEESE_WEDGE);
                        entries.add(ModBlocks.UNRIPE_FLAXEN_CHEESE_WHEEL);
                        entries.add(ModBlocks.UNRIPE_SCARLET_CHEESE_WHEEL);
                        entries.add(ModBlocks.FLAXEN_CHEESE_WHEEL);
                        entries.add(ModBlocks.SCARLET_CHEESE_WHEEL);

                        entries.add(ModItems.VEGETABLE_OMELET);
                        entries.add(ModItems.CREAMY_ONION_SOUP);
                        entries.add(ModItems.CHEESY_PASTA);
                        entries.add(ModItems.HORROR_LASAGNA);
                        entries.add(ModItems.SCARLET_PIEROGI);

                        entries.add(ModItems.HAM_AND_CHEESE_SANDWICH);
                        entries.add(ModItems.KIMCHI);
                        entries.add(ModItems.JERKY);
                        entries.add(ModItems.PICKLED_PICKLES);
                        entries.add(ModItems.KIPPERS);
                        entries.add(ModItems.COCOA_FUDGE);
                        entries.add(ModItems.SWEET_BERRY_JAM);
                        entries.add(ModItems.GLOW_BERRY_MARMALADE);
                        entries.add(ModItems.APPLE_JELLY);

                        entries.add(ModItems.PANCAKE);
                        entries.add(ModItems.PUMPKIN_PANCAKE);
                        entries.add(ModItems.CHERRY_BLOSSOM_PANCAKE);
                        entries.add(ModItems.VEGETABLE_PANCAKE);
                        entries.add(ModItems.CHOCOLATE_PANCAKE);
                        entries.add(ModItems.HONEY_PANCAKE);

                        entries.add(ModItems.DARK_COFFEE);
                        entries.add(ModItems.COFFEE);
                        entries.add(ModItems.MILK_COFFEE);
                        entries.add(ModItems.CHOCOLATE_COFFEE);
                        entries.add(ModItems.HONEY_COFFEE);

                        entries.add(ModItems.COFFEE_COOKIE);

                        entries.add(ModItems.COOKING_OIL);
                        entries.add(ModItems.BATTER);

                        entries.add(ModItems.COOKED_CALAMARI_SLICE);
                        entries.add(ModItems.CALAMARI_SLICE);
                        entries.add(ModItems.SLICED_CALAMARI_ROLL);

                        entries.add(ModItems.COFFEE_BEANS);
                        entries.add(ModItems.COFFEE_BRAISED_BEEF);

                        entries.add(ModItems.BELL_PEPPER_GREEN);
                        entries.add(ModItems.BELL_PEPPER_RED);
                        entries.add(ModItems.BELL_PEPPER_YELLOW);

                        entries.add(ModItems.BELL_PEPPER_SLICE_GREEN);
                        entries.add(ModItems.BELL_PEPPER_SLICE_RED);
                        entries.add(ModItems.BELL_PEPPER_SLICE_YELLOW);

                        entries.add(ModItems.POTATO_SLICES);
                        entries.add(ModItems.BAKED_POTATO_SLICES);
                        entries.add(ModItems.POTATO_SALAD);

                        entries.add(ModItems.BELL_PEPPER_PASTA);

                        entries.add(ModItems.BELL_PEPPER_ROLL_GREEN);
                        entries.add(ModItems.BELL_PEPPER_ROLL_RED);
                        entries.add(ModItems.BELL_PEPPER_ROLL_YELLOW);

                        entries.add(ModItems.SYRUP);
                        entries.add(ModItems.SYRUP_COFFEE);

                        entries.add(ModItems.ROASTED_COFFEE_BEANS);
                        entries.add(ModItems.GOLDEN_COFFEE_BEANS);

                        entries.add(ModItems.ROASTED_BELL_PEPPER_GREEN);
                        entries.add(ModItems.ROASTED_BELL_PEPPER_YELLOW);
                        entries.add(ModItems.ROASTED_BELL_PEPPER_RED);

                        entries.add(ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN);
                        entries.add(ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW);
                        entries.add(ModItems.ROASTED_BELL_PEPPER_SLICE_RED);

                        entries.add(ModItems.STUFFED_BELL_PEPPER_GREEN);
                        entries.add(ModItems.STUFFED_BELL_PEPPER_YELLOW);
                        entries.add(ModItems.STUFFED_BELL_PEPPER_RED);

                        entries.add(ModItems.FRIED_CALAMARI);
                        entries.add(ModItems.FRIED_CHICKEN);
                        entries.add(ModItems.FRIED_MUSHROOMS);

                        entries.add(ModItems.SYRUP_COOKIE);
                        entries.add(ModItems.CHERRY_BLOSSOM_COOKIE);

                        entries.add(ModItems.FRUIT_BEIGNET);

                        entries.add(ModItems.SWEET_SALAD);
                        entries.add(ModItems.BELL_PEPPER_SOUP);

                        entries.add(ModItems.FRIED_DOUGH);
                        entries.add(ModItems.FRIED_DUMPLINGS);
                        entries.add(ModItems.SPRING_ROLLS);

                        entries.add(ModItems.CHERRY_BLOSSOM_ROLL);
                        entries.add(ModItems.AVOCADO);
                        entries.add(ModItems.CABBAGE);
                        entries.add(ModItems.ONION);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.RICE);
                        entries.add(ModItems.CORN_COB);
                        entries.add(ModItems.CUCUMBER);
                        entries.add(ModItems.EGGPLANT);
                        entries.add(ModItems.WHITE_EGGPLANT);
                        entries.add(ModItems.SWEET_POTATO);
                        entries.add(ModItems.BROCCOLI);
                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.TURNIP);
                        entries.add(ModItems.ZUCCHINI);
                        entries.add(ModItems.DANDELION_LEAF);
                        entries.add(ModItems.GARLIC);
                        entries.add(ModItems.GARLIC_CLOVE);
                        entries.add(ModItems.SOYA_BEAN_POD);
                        entries.add(ModItems.SOYA_BEANS);
                        entries.add(ModItems.YUCA);
                        entries.add(ModItems.AJI_AMARILLO);


                    }).build());

    public static final ItemGroup NATURE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "nature"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.nature"))
                    .icon(() -> new ItemStack(ModItems.TOMATO))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.AVOCADO);
                        entries.add(ModItems.CABBAGE);
                        entries.add(ModItems.CABBAGE_SEEDS);
                        entries.add(ModItems.ONION);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.TOMATO_SEEDS);
                        entries.add(ModItems.RICE);
                        entries.add(ModItems.RICE_PANICLE);
                        entries.add(ModItems.CORN_KERNELS);
                        entries.add(ModItems.CORN_COB);
                        entries.add(ModItems.CUCUMBER);
                        entries.add(ModItems.CUCUMBER_SEEDS);
                        entries.add(ModItems.EGGPLANT);
                        entries.add(ModItems.WHITE_EGGPLANT);
                        entries.add(ModItems.EGGPLANT_SEEDS);
                        entries.add(ModItems.BELL_PEPPER_GREEN);
                        entries.add(ModItems.BELL_PEPPER_RED);
                        entries.add(ModItems.BELL_PEPPER_YELLOW);
                        entries.add(ModItems.BELL_PEPPER_SEEDS);
                        entries.add(ModItems.COTTON_BOLL);
                        entries.add(ModItems.COTTON_SEEDS);
                        entries.add(ModItems.COFFEE_BEANS);
                        entries.add(ModItems.BROCCOLI);
                        entries.add(ModItems.BROCCOLI_SEEDS);
                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.CAULIFLOWER_SEEDS);
                        entries.add(ModItems.SWEET_POTATO);
                        entries.add(ModItems.TURNIP);
                        entries.add(ModItems.TURNIP_SEEDS);
                        entries.add(ModItems.ZUCCHINI);
                        entries.add(ModItems.ZUCCHINI_SEEDS);
                        entries.add(ModItems.GARLIC_CLOVE);
                        entries.add(ModItems.GARLIC);
                        entries.add(ModItems.SOYA_BEANS);
                        entries.add(ModItems.SOYA_BEAN_POD);
                        entries.add(ModItems.YUCA);
                        entries.add(ModItems.AJI_AMARILLO_SEEDS);
                        entries.add(ModItems.AJI_AMARILLO);

                        entries.add(ModItems.STRAW);
                        entries.add(ModBlocks.STRAW_BALE);

                        entries.add(ModBlocks.WILD_CABBAGES);
                        entries.add(ModBlocks.WILD_ONIONS);
                        entries.add(ModBlocks.WILD_TOMATOES);
                        entries.add(ModBlocks.WILD_CARROTS);
                        entries.add(ModBlocks.WILD_POTATOES);
                        entries.add(ModBlocks.WILD_BEETROOTS);
                        entries.add(ModBlocks.WILD_RICE);
                        entries.add(ModBlocks.WILD_COFFEE);
                        entries.add(ModBlocks.WILD_COTTON);
                        entries.add(ModBlocks.WILD_BELL_PEPPERS);
                        entries.add(ModBlocks.WILD_BROCCOLI);
                        entries.add(ModBlocks.WILD_CAULIFLOWERS);
                        entries.add(ModBlocks.WILD_SWEET_POTATOES);
                        entries.add(ModBlocks.WILD_TURNIPS);
                        entries.add(ModBlocks.WILD_ZUCCHINIS);
                        entries.add(ModBlocks.WILD_SOYA);
                        entries.add(ModBlocks.WILD_YUCA);
                        entries.add(ModBlocks.WILD_AJI_AMARILLO);
                        entries.add(ModBlocks.SANDY_SHRUB);

                        entries.add(ModItems.OAK_BARK);
                        entries.add(ModItems.SPRUCE_BARK);
                        entries.add(ModItems.DARK_OAK_BARK);
                        entries.add(ModItems.BIRCH_BARK);
                        entries.add(ModItems.JUNGLE_BARK);
                        entries.add(ModItems.ACACIA_BARK);
                        entries.add(ModItems.CHERRY_BARK);
                        entries.add(ModItems.BAMBOO_BARK);


                    }).build());

    public static final ItemGroup BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.blocks"))
                    .icon(() -> new ItemStack(ModBlocks.TIN_BLOCK))
                    .entries((displayContext, entries) -> {
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
                        entries.add(ModBlocks.SLATE_COPPER_ORE);
                        entries.add(ModBlocks.SLATE_COAL_ORE);

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
                        entries.add(ModBlocks.DEEPSLATE_TRAPDOOR);

                        entries.add(ModBlocks.POLISHED_TUFF);
                        entries.add(ModBlocks.POLISHED_TUFF_SLAB);
                        entries.add(ModBlocks.POLISHED_TUFF_STAIRS);
                        entries.add(ModBlocks.POLISHED_TUFF_WALL);
                        entries.add(ModBlocks.CHISELED_TUFF);
                        entries.add(ModBlocks.TUFF_BRICKS);
                        entries.add(ModBlocks.TUFF_BRICK_SLAB);
                        entries.add(ModBlocks.TUFF_BRICK_STAIRS);
                        entries.add(ModBlocks.TUFF_BRICK_WALL);
                        entries.add(ModBlocks.CHISELED_TUFF_BRICKS);
                        entries.add(ModBlocks.TUFF_TRAPDOOR);
                        entries.add(ModBlocks.TUFF_SLAB);
                        entries.add(ModBlocks.TUFF_STAIRS);
                        entries.add(ModBlocks.TUFF_WALL);
                        entries.add(ModBlocks.TUFF_CARVED_WINDOW);
                        entries.add(ModBlocks.TUFF_CARVED_WINDOW_PANE);

                        entries.add(ModBlocks.STONE_PILLAR);
                        entries.add(ModBlocks.MOSSY_STONE_PILLAR);
                        entries.add(ModBlocks.CRACKED_STONE_PILLAR);
                        entries.add(ModBlocks.CHISELED_SMOOTH_STONE);
                        entries.add(ModBlocks.POLISHED_STONE);
                        entries.add(ModBlocks.POLISHED_STONE_SLAB);
                        entries.add(ModBlocks.POLISHED_STONE_STAIRS);
                        entries.add(ModBlocks.POLISHED_STONE_WALL);
                        entries.add(ModBlocks.MOSSY_POLISHED_STONE);
                        entries.add(ModBlocks.MOSSY_POLISHED_STONE_SLAB);
                        entries.add(ModBlocks.MOSSY_POLISHED_STONE_STAIRS);
                        entries.add(ModBlocks.MOSSY_POLISHED_STONE_WALL);
                        entries.add(ModBlocks.CRACKED_POLISHED_STONE);
                        entries.add(ModBlocks.CRACKED_POLISHED_STONE_SLAB);
                        entries.add(ModBlocks.CRACKED_POLISHED_STONE_STAIRS);
                        entries.add(ModBlocks.CRACKED_POLISHED_STONE_WALL);
                        entries.add(ModBlocks.STONE_TILES);
                        entries.add(ModBlocks.STONE_TILES_SLAB);
                        entries.add(ModBlocks.STONE_TILES_STAIRS);
                        entries.add(ModBlocks.STONE_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_STONE_TILES);
                        entries.add(ModBlocks.MOSSY_STONE_TILES_SLAB);
                        entries.add(ModBlocks.MOSSY_STONE_TILES_STAIRS);
                        entries.add(ModBlocks.MOSSY_STONE_TILES_WALL);
                        entries.add(ModBlocks.CRACKED_STONE_TILES);
                        entries.add(ModBlocks.CRACKED_STONE_TILES_SLAB);
                        entries.add(ModBlocks.CRACKED_STONE_TILES_STAIRS);
                        entries.add(ModBlocks.CRACKED_STONE_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_SMOOTH_STONE);
                        entries.add(ModBlocks.MOSSY_SMOOTH_STONE_SLAB);
                        entries.add(ModBlocks.MOSSY_SMOOTH_STONE_STAIRS);
                        entries.add(ModBlocks.MOSSY_SMOOTH_STONE_WALL);
                        entries.add(ModBlocks.CRACKED_SMOOTH_STONE);
                        entries.add(ModBlocks.CRACKED_SMOOTH_STONE_SLAB);
                        entries.add(ModBlocks.CRACKED_SMOOTH_STONE_STAIRS);
                        entries.add(ModBlocks.CRACKED_SMOOTH_STONE_WALL);
                        entries.add(ModBlocks.OLD_STONE);
                        entries.add(ModBlocks.OLD_STONE_SLAB);
                        entries.add(ModBlocks.OLD_STONE_STAIRS);
                        entries.add(ModBlocks.OLD_STONE_WALL);
                        entries.add(ModBlocks.STONE_BRICKWORK);
                        entries.add(ModBlocks.STONE_BRICKWORK_SLAB);
                        entries.add(ModBlocks.STONE_BRICKWORK_STAIRS);
                        entries.add(ModBlocks.STONE_BRICKWORK_WALL);
                        entries.add(ModBlocks.CHISELED_STONE);
                        entries.add(ModBlocks.CHISELED_POLISHED_STONE);
                        entries.add(ModBlocks.CHISELED_STONE_TILES);

                        entries.add(ModBlocks.BLUE_TUFF);
                        entries.add(ModBlocks.BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.BLUE_TUFF_WALL);
                        entries.add(ModBlocks.BLUE_TUFF_PILLAR);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_PILLAR);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_PILLAR);
                        entries.add(ModBlocks.BLUE_TUFF_BUTTON);
                        entries.add(ModBlocks.BLUE_TUFF_PRESSURE_PLATE);
                        entries.add(ModBlocks.BLUE_TUFF_TRAPDOOR);
                        entries.add(ModBlocks.BLUE_TUFF_BRICKS);
                        entries.add(ModBlocks.BLUE_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.BLUE_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.BLUE_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_BRICKS);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_BRICKS);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.BLUE_TUFF_TILES);
                        entries.add(ModBlocks.BLUE_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.BLUE_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.BLUE_TUFF_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_TILES);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.MOSSY_BLUE_TUFF_TILES_WALL);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_TILES);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.CRACKED_BLUE_TUFF_TILES_WALL);
                        entries.add(ModBlocks.POLISHED_BLUE_TUFF);
                        entries.add(ModBlocks.POLISHED_BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.POLISHED_BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.POLISHED_BLUE_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_POLISHED_BLUE_TUFF);
                        entries.add(ModBlocks.MOSSY_POLISHED_BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_POLISHED_BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_POLISHED_BLUE_TUFF_WALL);
                        entries.add(ModBlocks.CRACKED_POLISHED_BLUE_TUFF);
                        entries.add(ModBlocks.CRACKED_POLISHED_BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.CRACKED_POLISHED_BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.CRACKED_POLISHED_BLUE_TUFF_WALL);
                        entries.add(ModBlocks.CHISELED_POLISHED_BLUE_TUFF);
                        entries.add(ModBlocks.CHISELED_BLUE_TUFF_BRICKS);
                        entries.add(ModBlocks.COBBLED_BLUE_TUFF);
                        entries.add(ModBlocks.COBBLED_BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.COBBLED_BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.COBBLED_BLUE_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_COBBLED_BLUE_TUFF);
                        entries.add(ModBlocks.MOSSY_COBBLED_BLUE_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_COBBLED_BLUE_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_COBBLED_BLUE_TUFF_WALL);

                        entries.add(ModBlocks.GREEN_TUFF);
                        entries.add(ModBlocks.GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.GREEN_TUFF_WALL);
                        entries.add(ModBlocks.GREEN_TUFF_PILLAR);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_PILLAR);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_PILLAR);
                        entries.add(ModBlocks.GREEN_TUFF_BUTTON);
                        entries.add(ModBlocks.GREEN_TUFF_PRESSURE_PLATE);
                        entries.add(ModBlocks.GREEN_TUFF_TRAPDOOR);
                        entries.add(ModBlocks.GREEN_TUFF_BRICKS);
                        entries.add(ModBlocks.GREEN_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.GREEN_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.GREEN_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_BRICKS);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_BRICKS);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.GREEN_TUFF_TILES);
                        entries.add(ModBlocks.GREEN_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.GREEN_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.GREEN_TUFF_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_TILES);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.MOSSY_GREEN_TUFF_TILES_WALL);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_TILES);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.CRACKED_GREEN_TUFF_TILES_WALL);
                        entries.add(ModBlocks.POLISHED_GREEN_TUFF);
                        entries.add(ModBlocks.POLISHED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.POLISHED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.POLISHED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_POLISHED_GREEN_TUFF);
                        entries.add(ModBlocks.MOSSY_POLISHED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_POLISHED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_POLISHED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.CRACKED_POLISHED_GREEN_TUFF);
                        entries.add(ModBlocks.CRACKED_POLISHED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.CRACKED_POLISHED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.CRACKED_POLISHED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.CHISELED_POLISHED_GREEN_TUFF);
                        entries.add(ModBlocks.CHISELED_GREEN_TUFF);
                        entries.add(ModBlocks.CHISELED_GREEN_TUFF_BRICKS);
                        entries.add(ModBlocks.CHISELED_GREEN_TUFF_TILES);
                        entries.add(ModBlocks.CHISELED_SMOOTH_GREEN_TUFF);
                        entries.add(ModBlocks.SMOOTH_GREEN_TUFF);
                        entries.add(ModBlocks.SMOOTH_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.SMOOTH_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.SMOOTH_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.CRACKED_SMOOTH_GREEN_TUFF);
                        entries.add(ModBlocks.CRACKED_SMOOTH_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.CRACKED_SMOOTH_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.CRACKED_SMOOTH_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_COBBLED_GREEN_TUFF);
                        entries.add(ModBlocks.MOSSY_COBBLED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_COBBLED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_COBBLED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.COBBLED_GREEN_TUFF);
                        entries.add(ModBlocks.COBBLED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.COBBLED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.COBBLED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_SLAB);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_STAIRS);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_WALL);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_BUTTON);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_PRESSURE_PLATE);
                        entries.add(ModBlocks.GILDED_GREEN_TUFF_TRAPDOOR);
                        entries.add(ModBlocks.GILDED_CHISELED_GREEN_TUFF);
                        entries.add(ModBlocks.GILDED_CHISELED_GREEN_TUFF_BRICKS);
                        entries.add(ModBlocks.GILDED_CHISELED_GREEN_TUFF_TILES);
                        entries.add(ModBlocks.GILDED_CHISELED_POLISHED_GREEN_TUFF);
                        entries.add(ModBlocks.GILDED_CHISELED_SMOOTH_GREEN_TUFF);

                        entries.add(ModBlocks.OLD_TUFF);
                        entries.add(ModBlocks.OLD_TUFF_SLAB);
                        entries.add(ModBlocks.OLD_TUFF_STAIRS);
                        entries.add(ModBlocks.OLD_TUFF_WALL);
                        entries.add(ModBlocks.SMOOTH_TUFF);
                        entries.add(ModBlocks.SMOOTH_TUFF_SLAB);
                        entries.add(ModBlocks.SMOOTH_TUFF_STAIRS);
                        entries.add(ModBlocks.SMOOTH_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_SMOOTH_TUFF);
                        entries.add(ModBlocks.MOSSY_SMOOTH_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_SMOOTH_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_SMOOTH_TUFF_WALL);
                        entries.add(ModBlocks.CRACKED_SMOOTH_TUFF);
                        entries.add(ModBlocks.CRACKED_SMOOTH_TUFF_SLAB);
                        entries.add(ModBlocks.CRACKED_SMOOTH_TUFF_STAIRS);
                        entries.add(ModBlocks.CRACKED_SMOOTH_TUFF_WALL);
                        entries.add(ModBlocks.COBBLED_TUFF);
                        entries.add(ModBlocks.COBBLED_TUFF_SLAB);
                        entries.add(ModBlocks.COBBLED_TUFF_STAIRS);
                        entries.add(ModBlocks.COBBLED_TUFF_WALL);
                        entries.add(ModBlocks.MOSSY_COBBLED_TUFF);
                        entries.add(ModBlocks.MOSSY_COBBLED_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_COBBLED_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_COBBLED_TUFF_WALL);
                        entries.add(ModBlocks.TUFF_PILLAR);
                        entries.add(ModBlocks.MOSSY_TUFF_PILLAR);
                        entries.add(ModBlocks.CRACKED_TUFF_PILLAR);
                        entries.add(ModBlocks.MOSSY_TUFF_BRICKS);
                        entries.add(ModBlocks.MOSSY_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.MOSSY_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.MOSSY_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.CRACKED_TUFF_BRICKS);
                        entries.add(ModBlocks.CRACKED_TUFF_BRICKS_SLAB);
                        entries.add(ModBlocks.CRACKED_TUFF_BRICKS_STAIRS);
                        entries.add(ModBlocks.CRACKED_TUFF_BRICKS_WALL);
                        entries.add(ModBlocks.TUFF_TILES);
                        entries.add(ModBlocks.TUFF_TILES_SLAB);
                        entries.add(ModBlocks.TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.TUFF_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_TUFF_TILES);
                        entries.add(ModBlocks.MOSSY_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.MOSSY_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.MOSSY_TUFF_TILES_WALL);
                        entries.add(ModBlocks.CRACKED_TUFF_TILES);
                        entries.add(ModBlocks.CRACKED_TUFF_TILES_SLAB);
                        entries.add(ModBlocks.CRACKED_TUFF_TILES_STAIRS);
                        entries.add(ModBlocks.CRACKED_TUFF_TILES_WALL);
                        entries.add(ModBlocks.MOSSY_POLISHED_TUFF);
                        entries.add(ModBlocks.MOSSY_POLISHED_TUFF_SLAB);
                        entries.add(ModBlocks.MOSSY_POLISHED_TUFF_STAIRS);
                        entries.add(ModBlocks.MOSSY_POLISHED_TUFF_WALL);
                        entries.add(ModBlocks.CRACKED_POLISHED_TUFF);
                        entries.add(ModBlocks.CRACKED_POLISHED_TUFF_SLAB);
                        entries.add(ModBlocks.CRACKED_POLISHED_TUFF_STAIRS);
                        entries.add(ModBlocks.CRACKED_POLISHED_TUFF_WALL);
                        entries.add(ModBlocks.CHISELED_POLISHED_TUFF);
                        entries.add(ModBlocks.CHISELED_TUFF_TILES);
                        entries.add(ModBlocks.CHISELED_SMOOTH_TUFF);
                        entries.add(ModBlocks.TUFF_BRICKWORK);
                        entries.add(ModBlocks.TUFF_BRICKWORK_SLAB);
                        entries.add(ModBlocks.TUFF_BRICKWORK_STAIRS);
                        entries.add(ModBlocks.TUFF_BRICKWORK_WALL);
                    }).build());

    public static final ItemGroup FUNCTIONAL_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EwMedieval.MOD_ID, "functional_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ewmedieval.functional_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.STOVE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.STOVE);
                        entries.add(ModBlocks.COOKING_POT);
                        entries.add(ModBlocks.CUTTING_BOARD);
                        entries.add(ModBlocks.FORGE);
                        entries.add(ModBlocks.EARLY_FORGE);
                    }).build());

    public static void registerItemGroups() {
        EwMedieval.LOGGER.info("Registering Item Groups for " + EwMedieval.MOD_ID);
    }
}