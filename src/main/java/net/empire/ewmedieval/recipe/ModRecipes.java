package net.empire.ewmedieval.recipe;

import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static RecipeType<KnappingRecipe> KNAPPING_TYPE;
    public static KnappingRecipe.Serializer KNAPPING_SERIALIZER;

    public static RecipeType<CuttingBoardRecipe> CUTTING_TYPE;
    public static CuttingBoardRecipe.Serializer CUTTING_SERIALIZER;

    public static RecipeType<CookingPotRecipe> COOKING_TYPE;
    public static CookingPotRecipe.Serializer COOKING_SERIALIZER;

 //   public static RecipeType<StoveRecipe> STOVE_TYPE;
  //  public static StoveRecipe.Serializer STOVE_SERIALIZER;

    public static void registerRecipes() {
        KNAPPING_TYPE = Registry.register(Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", KnappingRecipe.Type.ID), KnappingRecipe.Type.INSTANCE);
        KNAPPING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER,
                KnappingRecipe.Serializer.ID, KnappingRecipe.Serializer.INSTANCE);

        Registry.register(Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", ForgeRecipe.Type.ID), ForgeRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER,
                ForgeRecipe.Serializer.ID, ForgeRecipe.Serializer.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER,
                EarlyForgeRecipe.Serializer.ID, EarlyForgeRecipe.Serializer.INSTANCE);


        CUTTING_TYPE = Registry.register(
                Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", "cutting"),
                new RecipeType<CuttingBoardRecipe>() {
                    @Override
                    public String toString() {
                        return "ewmedieval:cutting";
                    }
                });

        CUTTING_SERIALIZER = Registry.register(
                Registries.RECIPE_SERIALIZER,
                new Identifier("ewmedieval", "cutting"),
                new CuttingBoardRecipe.Serializer());

        COOKING_TYPE = Registry.register(
                Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", "cooking"),
                new RecipeType<CookingPotRecipe>() {
                    @Override
                    public String toString() { return "ewmedieval:cooking"; }
                });

        COOKING_SERIALIZER = Registry.register(
                Registries.RECIPE_SERIALIZER,
                new Identifier("ewmedieval", "cooking"),
                new CookingPotRecipe.Serializer());

        Registry.register(Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", ForgingRecipe.Type.ID), ForgingRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER,
                ForgingRecipe.Serializer.ID, ForgingRecipe.Serializer.INSTANCE);
/*
        STOVE_TYPE = Registry.register(
                Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", "stove"),
                new RecipeType<StoveRecipe>() {
                    @Override
                    public String toString() {return "ewmedieval:stove"; }
                });

        STOVE_SERIALIZER = Registry.register(
                Registries.RECIPE_SERIALIZER,
                new Identifier("ewmedieval", "stove"),
                new StoveRecipe.Serializer());
    }


 */

    }
}
