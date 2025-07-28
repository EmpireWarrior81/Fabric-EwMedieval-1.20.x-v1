package net.empire.ewmedieval.datagen;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;



import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> TIN_SMELTABLES = List.of(ModItems.RAW_TIN,
            ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);
    private static final List<ItemConvertible> SILVER_SMELTABLES = List.of(ModItems.RAW_SILVER,
            ModBlocks.DEEPSLATE_SILVER_ORE);
        private static final List<ItemConvertible> MITHRIL_SMELTABLES = List.of(ModItems.RAW_MITHRIL);
        private static final List<ItemConvertible> LEAD_SMELTABLES = List.of(ModItems.RAW_LEAD,
                ModBlocks.DEEPSLATE_LEAD_ORE);


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    private void offerSmokingRecipe(
            Consumer<RecipeJsonProvider> exporter,
            ItemConvertible input,
            ItemConvertible output,
            float experience,
            int cookingTime,
            String group,
            String recipeName) {

        CookingRecipeJsonBuilder.createSmoking(
                        Ingredient.ofItems(input),
                        RecipeCategory.FOOD,
                        output,
                        experience,
                        cookingTime)
                .group(group)
                .criterion("has_" + input.asItem().getName().getString().toLowerCase(), conditionsFromItem(input))
                .offerTo(exporter, new Identifier("ewmedieval", recipeName));
    }

    private void offerSmeltingRecipe(
            Consumer<RecipeJsonProvider> exporter,
            ItemConvertible input,
            ItemConvertible output,
            float experience,
            int cookingTime,
            String group,
            String recipeName) {

        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(input),
                        RecipeCategory.FOOD,
                        output,
                        experience,
                        cookingTime)
                .group(group)
                .criterion("has_" + input.asItem().getName().getString().toLowerCase(), conditionsFromItem(input))
                .offerTo(exporter, new Identifier("ewmedieval", recipeName));
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {


// Metals/ores
        offerSmelting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 200, "tin_ingot");
        offerBlasting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 100, "tin_ingot");
        offerSmelting(exporter, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT,
                0.9f, 200, "silver_ingot");
        offerBlasting(exporter, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT,
                0.9f, 100, "silver_ingot");
        offerSmelting(exporter, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT,
                0.9f, 200, "silver_ingot");
        offerBlasting(exporter, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT,
                0.9f, 100, "silver_ingot");
        offerSmelting(exporter, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MITHRIL_INGOT,
                0.9f, 200, "silver_ingot");
        offerBlasting(exporter, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MITHRIL_INGOT,
                0.9f, 100, "silver_ingot");
        

// Food
        offerSmokingRecipe(exporter, ModItems.RAW_HORSE, ModItems.COOKED_HORSE, 0.35f, 100, "cooked_horse", "cooked_horse_smoking");
        offerSmeltingRecipe(exporter, ModItems.RAW_HORSE, ModItems.COOKED_HORSE, 0.35f, 200, "cooked_horse", "cooked_horse_smelting");
        offerSmokingRecipe(exporter, ModItems.RAW_SWAN, ModItems.COOKED_SWAN, 0.35f, 100, "cooked_swan", "cooked_swan_smoking");
        offerSmeltingRecipe(exporter, ModItems.RAW_SWAN, ModItems.COOKED_SWAN, 0.35f, 200, "cooked_swan", "cooked_swan_smelting");
        offerSmokingRecipe(exporter, ModItems.RAW_GOAT, ModItems.COOKED_GOAT, 0.35f, 100, "cooked_goat", "cooked_goat_smoking");
        offerSmeltingRecipe(exporter, ModItems.RAW_GOAT, ModItems.COOKED_GOAT, 0.35f, 200, "cooked_goat", "cooked_goat_smelting");
        offerSmokingRecipe(exporter, ModItems.CACTUS_FLESH, ModItems.CACTUS_STEAK, 0.35f, 100, "cactus_steak", "cactus_steak_smoking");
        offerSmeltingRecipe(exporter, ModItems.CACTUS_FLESH, ModItems.CACTUS_STEAK, 0.35f, 200, "cactus_steak", "cactus_steak_smelting");
        offerSmokingRecipe(exporter, ModItems.BAT_WING, ModItems.SMOKED_BAT_WING, 0.35f, 100, "smoked_bat_wing", "smoked_bat_wing_smoking");
        offerSmokingRecipe(exporter, ModItems.PEAR, ModItems.BAKED_PEAR, 0.35f, 100, "baked_pear", "baked_pear_smoking");
        offerSmeltingRecipe(exporter, ModItems.PEAR, ModItems.BAKED_PEAR, 0.35f, 200, "baked_pear", "baked_pear_smelting");

// Crafting Blocks
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TIN_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.TIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TIN, RecipeCategory.DECORATIONS,
                ModBlocks.RAW_TIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SILVER_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.SILVER_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_SILVER, RecipeCategory.DECORATIONS,
                ModBlocks.RAW_SILVER_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.KHAZAD_STEEL_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.KHAZAD_STEEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.BURZUM_STEEL_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.BURZUM_STEEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.EDHEL_STEEL_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.EDHEL_STEEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CRUDE_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.CRUDE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_LEAD, RecipeCategory.DECORATIONS,
                ModBlocks.RAW_LEAD_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.LEAD_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.LEAD_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_MITHRIL, RecipeCategory.DECORATIONS,
                ModBlocks.RAW_MITHRIL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.MITHRIL_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.MITHRIL_BLOCK);



// Nugget → Ingot
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KHAZAD_STEEL_INGOT)
                .input(ModItems.KHAZAD_STEEL_NUGGET, 9)
                .criterion("has_khazad_steel_nugget", conditionsFromItem(ModItems.KHAZAD_STEEL_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "khazad_steel_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BURZUM_STEEL_INGOT)
                .input(ModItems.BURZUM_STEEL_NUGGET, 9)
                .criterion("has_burzum_steel_nugget", conditionsFromItem(ModItems.BURZUM_STEEL_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "burzum_steel_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EDHEL_STEEL_INGOT)
                .input(ModItems.EDHEL_STEEL_NUGGET, 9)
                .criterion("has_edhel_steel_nugget", conditionsFromItem(ModItems.EDHEL_STEEL_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "edhel_steel_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRUDE_INGOT)
                .input(ModItems.CRUDE_NUGGET, 9)
                .criterion("has_crude_nugget", conditionsFromItem(ModItems.CRUDE_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "crude_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LEAD_INGOT)
                .input(ModItems.LEAD_NUGGET, 9)
                .criterion("has_lead_nugget", conditionsFromItem(ModItems.LEAD_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "lead_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_INGOT)
                .input(ModItems.MITHRIL_NUGGET, 9)
                .criterion("has_mithril_nugget", conditionsFromItem(ModItems.MITHRIL_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "mithril_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TIN_INGOT)
                .input(ModItems.TIN_NUGGET, 9)
                .criterion("has_tin_nugget", conditionsFromItem(ModItems.TIN_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "tin_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SILVER_INGOT)
                .input(ModItems.SILVER_NUGGET, 9)
                .criterion("has_silver_nugget", conditionsFromItem(ModItems.SILVER_NUGGET))
                .offerTo(exporter, new Identifier("ewmedieval", "silver_ingot_from_nuggets"));

// Ingot → Nugget
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KHAZAD_STEEL_NUGGET, 9)
                .input(ModItems.KHAZAD_STEEL_INGOT)
                .criterion("has_khazad_steel_ingot", conditionsFromItem(ModItems.KHAZAD_STEEL_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "khazad_steel_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BURZUM_STEEL_NUGGET, 9)
                .input(ModItems.BURZUM_STEEL_INGOT)
                .criterion("has_burzum_steel_ingot", conditionsFromItem(ModItems.BURZUM_STEEL_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "burzum_steel_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EDHEL_STEEL_NUGGET, 9)
                .input(ModItems.EDHEL_STEEL_INGOT)
                .criterion("has_edhel_steel_ingot", conditionsFromItem(ModItems.EDHEL_STEEL_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "edhel_steel_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRUDE_NUGGET, 9)
                .input(ModItems.CRUDE_INGOT)
                .criterion("has_crude_ingot", conditionsFromItem(ModItems.CRUDE_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "crude_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LEAD_NUGGET, 9)
                .input(ModItems.LEAD_INGOT)
                .criterion("has_lead_ingot", conditionsFromItem(ModItems.LEAD_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "lead_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_NUGGET, 9)
                .input(ModItems.MITHRIL_INGOT)
                .criterion("has_mithril_ingot", conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "mithril_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TIN_NUGGET, 9)
                .input(ModItems.TIN_INGOT)
                .criterion("has_tin_ingot", conditionsFromItem(ModItems.TIN_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "tin_nugget_from_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SILVER_NUGGET, 9)
                .input(ModItems.SILVER_INGOT)
                .criterion("has_silver_ingot", conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter, new Identifier("ewmedieval", "silver_nugget_from_ingot"));


// Cut recipes
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CUT_LEAD,
                ModBlocks.LEAD_BLOCK , 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CUT_SILVER,
                ModBlocks.SILVER_BLOCK , 1);


// Cactus → Cactus Flesh
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CACTUS_FLESH, 3)
                .input(Items.CACTUS)
                .criterion("has_cactus", conditionsFromItem(Items.CACTUS))
                .offerTo(exporter, new Identifier("ewmedieval", "cactus_flesh_from_cactus"));

// Stone version Block recipes
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_SLATE,
                ModBlocks.SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_STAIRS,
                ModBlocks.SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_WALL,
                ModBlocks.SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_BRICKS,
                ModBlocks.POLISHED_SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_TRAPDOOR,
                ModBlocks.SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COBBLED_SLATE,
                ModBlocks.SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_SLAB,
                ModBlocks.SLATE , 2);


        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_SLATE_STAIRS,
                ModBlocks.POLISHED_SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_SLATE_WALL,
                ModBlocks.POLISHED_SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_SLATE_SLAB,
                ModBlocks.POLISHED_SLATE , 2);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_BRICKS_STAIRS,
                ModBlocks.SLATE_BRICKS , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_BRICKS_WALL,
                ModBlocks.SLATE_BRICKS , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SLATE_BRICKS_SLAB,
                ModBlocks.SLATE_BRICKS , 2);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COBBLED_SLATE_STAIRS,
                ModBlocks.COBBLED_SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COBBLED_SLATE_WALL,
                ModBlocks.COBBLED_SLATE , 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COBBLED_SLATE_SLAB,
                ModBlocks.COBBLED_SLATE , 2);

        createStairsRecipe(ModBlocks.SLATE_STAIRS, Ingredient.ofItems(ModBlocks.SLATE))
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, Ingredient.ofItems(ModBlocks.SLATE))
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.POLISHED_SLATE_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_SLATE))
                .criterion("has_polished_slate", conditionsFromItem(ModBlocks.POLISHED_SLATE.asItem()))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_SLATE))
                .criterion("has_polished_slate", conditionsFromItem(ModBlocks.POLISHED_SLATE.asItem()))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.SLATE_BRICKS_STAIRS, Ingredient.ofItems(ModBlocks.SLATE_BRICKS))
                .criterion("has_slate_bricks", conditionsFromItem(ModBlocks.SLATE_BRICKS.asItem()))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_BRICKS_SLAB, Ingredient.ofItems(ModBlocks.SLATE_BRICKS))
                .criterion("has_slate_bricks", conditionsFromItem(ModBlocks.SLATE_BRICKS.asItem()))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.COBBLED_SLATE_STAIRS, Ingredient.ofItems(ModBlocks.COBBLED_SLATE))
                .criterion("has_cobbled_slate", conditionsFromItem(ModBlocks.COBBLED_SLATE.asItem()))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_SLATE_SLAB, Ingredient.ofItems(ModBlocks.COBBLED_SLATE))
                .criterion("has_cobbled_slate", conditionsFromItem(ModBlocks.COBBLED_SLATE.asItem()))
                .offerTo(exporter);

        createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.SLATE))
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.SLATE)
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.POLISHED_SLATE)
                .criterion("has_polished_slate", conditionsFromItem(ModBlocks.POLISHED_SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_BRICKS_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.SLATE_BRICKS)
                .criterion("has_slate_bricks", conditionsFromItem(ModBlocks.SLATE_BRICKS.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_SLATE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.COBBLED_SLATE)
                .criterion("has_cobbled_slate_bricks", conditionsFromItem(ModBlocks.COBBLED_SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TRAPDOOR, 3)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.SLATE)
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.SLATE)
                .criterion("has_slate", conditionsFromItem(ModBlocks.SLATE.asItem()))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.POLISHED_SLATE)
                .criterion("has_polished_slate", conditionsFromItem(ModBlocks.POLISHED_SLATE.asItem()))
                .offerTo(exporter);

    }
}
