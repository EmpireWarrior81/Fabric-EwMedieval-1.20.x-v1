package net.empire.ewmedieval.item;

import net.empire.ewmedieval.effect.ModEffects;
import net.empire.ewmedieval.item.fooditems.FoodValues;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoods {


    public static final FoodComponent RAW_HORSE = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).meat().build();
    public static final FoodComponent RAW_SWAN = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).meat().build();
    public static final FoodComponent RAW_GOAT = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).meat().build();
    public static final FoodComponent COOKED_HORSE = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).meat().build();
    public static final FoodComponent COOKED_SWAN = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).meat().build();
    public static final FoodComponent COOKED_GOAT = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).meat().build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).meat().build();
    public static final FoodComponent SMOKED_BAT_WING = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).meat().build();
    public static final FoodComponent RAW_WOLF = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).meat().build();
    public static final FoodComponent COOKED_WOLF = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).meat().build();


    public static final FoodComponent CABBAGE = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.4f).build();
    public static final FoodComponent TOMATO = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.3f).build();
    public static final FoodComponent ONION = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.4f).build();


    public static final FoodComponent FRIED_EGG = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent TOMATO_SAUCE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent WHEAT_DOUGH = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, FoodValues.BRIEF_DURATION, 0), 0.3F).build();
    public static final FoodComponent RAW_PASTA = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, FoodValues.BRIEF_DURATION, 0), 0.3F).build();
    public static final FoodComponent PIE_CRUST = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent PUMPKIN_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent CABBAGE_LEAF = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.4f).snack().build();
    public static final FoodComponent MINCED_BEEF = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f).meat().snack().build();
    public static final FoodComponent BEEF_PATTY = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.8f).meat().snack().build();
    public static final FoodComponent CHICKEN_CUTS = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, FoodValues.BRIEF_DURATION, 0), 0.3F)
            .meat().snack().build();
    public static final FoodComponent COOKED_CHICKEN_CUTS = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.6f).meat().snack().build();
    public static final FoodComponent BACON = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f).meat().snack().build();
    public static final FoodComponent COOKED_BACON = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.8f).meat().snack().build();
    public static final FoodComponent COD_SLICE = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.1f).snack().build();
    public static final FoodComponent COOKED_COD_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.5f).snack().build();
    public static final FoodComponent SALMON_SLICE = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.1f).snack().build();
    public static final FoodComponent COOKED_SALMON_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.8f).snack().build();
    public static final FoodComponent MUTTON_CHOPS = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.3f).meat().snack().build();
    public static final FoodComponent COOKED_MUTTON_CHOPS = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.8f).meat().snack().build();
    public static final FoodComponent HAM = new FoodComponent.Builder()
            .hunger(5).saturationModifier(0.3f).meat().build();
    public static final FoodComponent SMOKED_HAM = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.8f).meat().build();


    public static final FoodComponent POPSICLE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.2f).snack().alwaysEdible().build();
    public static final FoodComponent COOKIES = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.1f).snack().build();
    public static final FoodComponent CAKE_SLICE = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.1f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 0, false, false), 1.0F).build();
    public static final FoodComponent PIE_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.3f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, FoodValues.BRIEF_DURATION, 0, false, false), 1.0F).build();
    public static final FoodComponent FRUIT_SALAD = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
    public static final FoodComponent GLOW_BERRY_CUSTARD = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.6f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0), 1.0F).build();


    public static final FoodComponent MIXED_SALAD = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
    public static final FoodComponent NETHER_SALAD = new FoodComponent.Builder()
            .hunger(5).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 240, 0), 0.3F).build();
    public static final FoodComponent BARBECUE_STICK = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.9f).build();
    public static final FoodComponent EGG_SANDWICH = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.8f).build();
    public static final FoodComponent CHICKEN_SANDWICH = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent HAMBURGER = new FoodComponent.Builder()
            .hunger(11).saturationModifier(0.8f).build();
    public static final FoodComponent BACON_SANDWICH = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent MUTTON_WRAP = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent DUMPLINGS = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.8f).build();
    public static final FoodComponent STUFFED_POTATO = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.7f).build();
    public static final FoodComponent CABBAGE_ROLLS = new FoodComponent.Builder()
            .hunger(5).saturationModifier(0.5f).build();
    public static final FoodComponent SALMON_ROLL = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.6f).build();
    public static final FoodComponent COD_ROLL = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.6f).build();
    public static final FoodComponent KELP_ROLL = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.6f).build();
    public static final FoodComponent KELP_ROLL_SLICE = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.5f).snack().build();


    public static final FoodComponent COOKED_RICE = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.BRIEF_DURATION, 0), 1.0F).build();
    public static final FoodComponent BONE_BROTH = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.SHORT_DURATION, 0), 1.0F).build();
    public static final FoodComponent BEEF_STEW = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent VEGETABLE_SOUP = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent FISH_STEW = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent CHICKEN_SOUP = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent FRIED_RICE = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent PUMPKIN_SOUP = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent BAKED_COD_STEW = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent NOODLE_SOUP = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0), 1.0F).build();


    public static final FoodComponent BACON_AND_EGGS = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.SHORT_DURATION, 0), 1.0F).build();
    public static final FoodComponent RATATOUILLE = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.SHORT_DURATION, 0), 1.0F).build();
    public static final FoodComponent STEAK_AND_POTATOES = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent PASTA_WITH_MEATBALLS = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent PASTA_WITH_MUTTON_CHOP = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent MUSHROOM_RICE = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodComponent ROASTED_MUTTON_CHOPS = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent VEGETABLE_NOODLES = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent SQUID_INK_PASTA = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent GRILLED_SALMON = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0F).build();


    public static final FoodComponent ROAST_CHICKEN = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent STUFFED_PUMPKIN = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent HONEY_GLAZED_HAM = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent SHEPHERDS_PIE = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();


    public static final FoodComponent APPLE_CIDER = new FoodComponent.Builder().alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, FoodValues.SHORT_DURATION, 0), 1.0F).build();

    public static final FoodComponent DOG_FOOD = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.2f).meat().build();


    public static final FoodComponent AVOCADO =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.6f).build();
    public static final FoodComponent CUCUMBER =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.5f).build();
    public static final FoodComponent PICKLE =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.7f).build();
    public static final FoodComponent EGGPLANT =
            new FoodComponent.Builder().hunger(3).saturationModifier(0.2f).build();
    public static final FoodComponent SMOKED_EGGPLANT =
            new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();
    public static final FoodComponent SMOKED_TOMATO =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.6f).build();
    public static final FoodComponent SMOKED_WHITE_EGGPLANT =
            new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).build();
    public static final FoodComponent WHITE_EGGPLANT =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent CORN_COB =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.4f).build();
    public static final FoodComponent GINGER =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.5f).build();
    public static final FoodComponent SQUID =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent COOKED_SQUID =
            new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent GLOW_SQUID =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 40, 1), 1.0f).snack().build();
    public static final FoodComponent RAW_CALAMARI =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).build();
    public static final FoodComponent COOKED_CALAMARI =
            new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).build();

    public static final FoodComponent CUT_AVOCADO =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).snack().build();
    public static final FoodComponent CUT_CUCUMBER =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).snack().build();
    public static final FoodComponent CUT_PICKLE =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.5f).snack().build();
    public static final FoodComponent CUT_EGGPLANT =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().build();
    public static final FoodComponent SMOKED_CUT_EGGPLANT =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).snack().build();

    public static final FoodComponent MIDORI_ROLL =
            new FoodComponent.Builder().hunger(14).saturationModifier(0.7f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent MIDORI_ROLL_SLICE =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.4f).snack().build();
    public static final FoodComponent EGG_ROLL =
            new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).snack().build();
    public static final FoodComponent CHICKEN_ROLL =
            new FoodComponent.Builder().hunger(16).saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent CHICKEN_ROLL_SLICE =
            new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).snack().build();
    public static final FoodComponent PUFFERFISH_ROLL =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.6f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 1), 1.0f)
                    .snack().build();
    public static final FoodComponent TROPICAL_ROLL =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.6f).snack().build();
    public static final FoodComponent RICE_BALL =
            new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).snack().build();
    public static final FoodComponent CALAMARI_ROLL =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.6f).snack().build();

    public static final FoodComponent CORN_DOUGH =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().build();
    public static final FoodComponent TORTILLA =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.6f).build();
    public static final FoodComponent POPCORN =
            new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).snack().build();
    public static final FoodComponent TORTILLA_CHIPS =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).snack().build();
    public static final FoodComponent ELOTE =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.7f).build();
    public static final FoodComponent EMPANADA =
            new FoodComponent.Builder().hunger(6).saturationModifier(0.6f).build();
    public static final FoodComponent BEEF_BURRITO =
            new FoodComponent.Builder().hunger(14).saturationModifier(0.7f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F).build();
    public static final FoodComponent MUTTON_SANDWICH =
            new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent AVOCADO_TOAST =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build();
    public static final FoodComponent CREAMED_CORN =
            new FoodComponent.Builder().hunger(6).saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.SHORT_DURATION, 0), 1.0F).build();
    public static final FoodComponent CHICKEN_TACO =
            new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent PORK_WRAP =
            new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent FISH_TACO =
            new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();

    public static final FoodComponent HEARTY_SALAD =
            new FoodComponent.Builder().hunger(7).saturationModifier(0.7f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent EGGPLANT_PARMESAN =
            new FoodComponent.Builder().hunger(12).saturationModifier(0.9f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent EGGPLANT_BURGER =
            new FoodComponent.Builder().hunger(12).saturationModifier(0.7f).build();
    public static final FoodComponent SPICY_CURRY =
            new FoodComponent.Builder().hunger(12).saturationModifier(1.3f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent POACHED_EGGPLANTS =
            new FoodComponent.Builder().hunger(8).saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();

    public static final FoodComponent FIERY_FONDUE =
            new FoodComponent.Builder().hunger(14).saturationModifier(0.75f)
                    .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.VERY_LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent VEGETABLE_OMELET =
            new FoodComponent.Builder().hunger(12).saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent CREAMY_ONION_SOUP =
            new FoodComponent.Builder().hunger(12).saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent CHEESY_PASTA =
            new FoodComponent.Builder().hunger(14).saturationModifier(0.75f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent HORROR_LASAGNA =
            new FoodComponent.Builder().hunger(18).saturationModifier(0.55f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();
    public static final FoodComponent SCARLET_PIEROGI =
            new FoodComponent.Builder().hunger(12).saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.VERY_LONG_DURATION, 0), 1.0f).build();

    public static final FoodComponent PIZZA_SLICE =
            new FoodComponent.Builder().hunger(5).saturationModifier(1.0f).build();
    public static final FoodComponent QUICHE_SLICE =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).build();
    public static final FoodComponent HAM_AND_CHEESE_SANDWICH =
            new FoodComponent.Builder().hunger(9).saturationModifier(1.0f).build();

    public static final FoodComponent KIMCHI =
            new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).build();
    public static final FoodComponent JERKY =
            new FoodComponent.Builder().hunger(3).saturationModifier(0.7f).build();
    public static final FoodComponent PICKLED_PICKLES =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).build();
    public static final FoodComponent KIPPERS =
            new FoodComponent.Builder().hunger(6).saturationModifier(0.5f).build();

    public static final FoodComponent COCOA_FUDGE =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,
                            FoodValues.MINIMAL_DURATION, 0), 1.0f).build();

    public static final FoodComponent SWEET_BERRY_JAM =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).build();
    public static final FoodComponent GLOW_BERRY_MARMALADE =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).build();
    public static final FoodComponent APPLE_JELLY =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).build();

    public static final FoodComponent FLAXEN_CHEESE =
            new FoodComponent.Builder().hunger(4).saturationModifier(1f).build();
    public static final FoodComponent SCARLET_CHEESE =
            new FoodComponent.Builder().hunger(4).saturationModifier(1f).build();
    public static final FoodComponent DORBLU_CHEESE =
            new FoodComponent.Builder().hunger(4).saturationModifier(1f).build();


    public static final FoodComponent LEMBAS = new FoodComponent.Builder()
            .hunger(20).saturationModifier(1f).build();
    public static final FoodComponent CACTUS_FLESH = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent CACTUS_STEAK = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent CRANBERRY = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f).build();
    public static final FoodComponent FIG = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent KIWI = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent LEMON = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent MANGO = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f).build();
    public static final FoodComponent ORANGE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.5f).build();
    public static final FoodComponent PEACH = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.5f).build();
    public static final FoodComponent PEAR = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.5f).build();
    public static final FoodComponent BAKED_PEAR = new FoodComponent.Builder()
            .hunger(5).saturationModifier(0.7f).build();


    public static final FoodComponent MELON_JUICE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.3f).alwaysEdible().build();
    public static final FoodComponent HOT_COCOA = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.4f).alwaysEdible().build();
    public static final FoodComponent MILK_BOTTLE = new FoodComponent.Builder()
            .hunger(0).saturationModifier(0.0f).alwaysEdible().build();


    public static final FoodComponent BATTER = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3f).build();
    public static final FoodComponent COOKING_OIL = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.3f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 0.5f).build();
    public static final FoodComponent SYRUP = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 0, false, false), 1.0f).build();


    public static final FoodComponent BELL_PEPPER = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent BELL_PEPPER_SLICE = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent ROASTED_COFFEE_BEANS = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent GOLDEN_COFFEE_BEANS = new FoodComponent.Builder()
            .hunger(5).saturationModifier(1.2f).build();
    public static final FoodComponent ROASTED_BELL_PEPPER = new FoodComponent.Builder()
            .hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent ROASTED_BELL_PEPPER_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.6f).build();
    public static final FoodComponent POTATO_SLICES = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.3f).build();
    public static final FoodComponent BAKED_POTATO_SLICES = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.6f).build();
    public static final FoodComponent CALAMARI_SLICE = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.1f).snack().build();
    public static final FoodComponent COOKED_CALAMARI_SLICE = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.6f).snack().build();


    public static final FoodComponent COFFEE = new FoodComponent.Builder()
            .alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1800, 0), 1.0f).build();
    public static final FoodComponent MILK_COFFEE = new FoodComponent.Builder()
            .alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0), 1.0f).build();
    public static final FoodComponent CHOCOLATE_COFFEE = new FoodComponent.Builder()
            .alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 0), 1.0f).build();
    public static final FoodComponent HONEY_COFFEE = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.1f).alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 0), 1.0f).build();
    public static final FoodComponent SYRUP_COFFEE = new FoodComponent.Builder()
            .alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1800, 0), 1.0f).build();
    public static final FoodComponent DARK_COFFEE = new FoodComponent.Builder()
            .alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 1), 1.0f).build();


    public static final FoodComponent SYRUP_SANDWICH = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.7f).build();
    public static final FoodComponent FRUIT_BEIGNET = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.6f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0, false, false), 1.0f).build();
    public static final FoodComponent PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f).build();
    public static final FoodComponent HONEY_PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0, false, false), 1.0f).build();
    public static final FoodComponent CHOCOLATE_PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0, false, false), 1.0f).build();
    public static final FoodComponent CHERRY_BLOSSOM_PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 600, 0, false, false), 1.0f).build();
    public static final FoodComponent VEGETABLE_PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0, false, false), 1.0f).build();
    public static final FoodComponent PUMPKIN_PANCAKE = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 600, 0, false, false), 1.0f).build();


    public static final FoodComponent SLICED_CALAMARI_ROLL = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.6f).build();
    public static final FoodComponent CHERRY_BLOSSOM_ROLL = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.5f).build();
    public static final FoodComponent BELL_PEPPER_ROLL = new FoodComponent.Builder()
            .hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent STUFFED_BELL_PEPPER = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.7f).build();


    public static final FoodComponent FRIED_DOUGH = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.5f).build();
    public static final FoodComponent SPRING_ROLLS = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.6f).build();
    public static final FoodComponent FRIED_DUMPLINGS = new FoodComponent.Builder()
            .hunger(10).saturationModifier(0.8f).build();


    public static final FoodComponent POTATO_SALAD = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f).build();
    public static final FoodComponent SWEET_SALAD = new FoodComponent.Builder()
            .hunger(8).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f).build();
    public static final FoodComponent BELL_PEPPER_SOUP = new FoodComponent.Builder()
            .hunger(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();

    public static final FoodComponent BELL_PEPPER_PASTA = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent FRIED_CHICKEN = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent FRIED_CALAMARI = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent FRIED_MUSHROOMS = new FoodComponent.Builder()
            .hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0), 1.0f).build();
    public static final FoodComponent COFFEE_BRAISED_BEEF = new FoodComponent.Builder()
            .hunger(14).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0f).build();

    public static final FoodComponent SWEET_POTATO = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.4f).build();
    public static final FoodComponent BROCCOLI = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.4f).build();
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder()
            .hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent TURNIP = new FoodComponent.Builder()
            .hunger(2).saturationModifier(0.4f).build();
    public static final FoodComponent ZUCCHINI = new FoodComponent.Builder()
            .hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent DANDELION_LEAF = new FoodComponent.Builder()
            .hunger(1).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f).build();





}
