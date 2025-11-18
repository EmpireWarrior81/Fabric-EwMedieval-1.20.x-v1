package net.empire.ewmedieval.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_TYPE,
                new Identifier("ewmedieval", ForgeRecipe.Type.ID), ForgeRecipe.Type.INSTANCE);
        
        Registry.register(Registries.RECIPE_SERIALIZER,
                ForgeRecipe.Serializer.ID, ForgeRecipe.Serializer.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER,
                EarlyForgeRecipe.Serializer.ID, EarlyForgeRecipe.Serializer.INSTANCE);
    }
}
