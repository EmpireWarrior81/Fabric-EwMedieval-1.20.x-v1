package net.empire.ewmedieval.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe for the Cooking Pot.
 *
 * JSON format example:
 * {
 *   "type": "ewmedieval:cooking",
 *   "group": "",
 *   "ingredients": [
 *     {"item": "minecraft:beef"},
 *     {"item": "ewmedieval:carrot"}
 *   ],
 *   "container": {"item": "minecraft:bowl"},
 *   "result": {"item": "ewmedieval:beef_stew", "count": 1},
 *   "cookingtime": 400,
 *   "experience": 0.35
 * }
 *
 * Matching: all listed ingredients must be present in slots 0-5 (order-independent).
 * The container item must match slot 6.
 */
public class CookingPotRecipe implements Recipe<SimpleInventory> {

    /** Number of ingredient input slots in the cooking pot. */
    public static final int INGREDIENT_SLOTS = 6;
    /** Index into the SimpleInventory used for matching (slots 0-5 = ingredients, 6 = container). */
    public static final int CONTAINER_SLOT   = 6;

    private final Identifier id;
    private final String group;
    private final DefaultedList<Ingredient> ingredients;
    private final Ingredient container;
    private final ItemStack result;
    private final int cookingTime;
    private final float experience;

    public CookingPotRecipe(Identifier id, String group,
                            DefaultedList<Ingredient> ingredients,
                            Ingredient container,
                            ItemStack result,
                            int cookingTime,
                            float experience) {
        this.id = id;
        this.group = group;
        this.ingredients = ingredients;
        this.container = container;
        this.result = result;
        this.cookingTime = cookingTime;
        this.experience = experience;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getContainer() {
        return container;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public float getExperience() {
        return experience;
    }

    /**
     * Checks whether all required ingredients are present in slots 0-5 (order-independent)
     * and the container slot matches.
     *
     * @param inv SimpleInventory of size 7: slots 0-5 = ingredients, slot 6 = container.
     */
    @Override
    public boolean matches(SimpleInventory inv, World world) {
        // Container must match
        if (!container.test(inv.getStack(CONTAINER_SLOT))) return false;

        // Build a mutable list of available ingredient stacks
        List<ItemStack> available = new ArrayList<>();
        for (int i = 0; i < INGREDIENT_SLOTS; i++) {
            ItemStack s = inv.getStack(i);
            if (!s.isEmpty()) available.add(s);
        }

        // Each required ingredient must be matched by an available stack (one-to-one)
        List<ItemStack> remaining = new ArrayList<>(available);
        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < remaining.size(); i++) {
                if (ing.test(remaining.get(i))) {
                    remaining.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COOKING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COOKING_TYPE;
    }

    // -------------------------------------------------------------------------
    // Serializer
    // -------------------------------------------------------------------------

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {

        public static final Identifier ID = new Identifier("ewmedieval", "cooking");

        @Override
        public CookingPotRecipe read(Identifier recipeId, JsonObject json) {
            String group = JsonHelper.getString(json, "group", "");

            JsonArray ingArray = JsonHelper.getArray(json, "ingredients");
            if (ingArray.size() < 1 || ingArray.size() > INGREDIENT_SLOTS) {
                throw new JsonParseException(
                        "Cooking pot recipe must have 1-" + INGREDIENT_SLOTS + " ingredients");
            }
            DefaultedList<Ingredient> ingredients = DefaultedList.of();
            for (JsonElement el : ingArray) {
                ingredients.add(Ingredient.fromJson(el));
            }

            Ingredient container = Ingredient.fromJson(JsonHelper.getObject(json, "container"));

            JsonObject resultObj = JsonHelper.getObject(json, "result");
            Identifier resultId = new Identifier(JsonHelper.getString(resultObj, "item"));
            int resultCount = JsonHelper.getInt(resultObj, "count", 1);
            ItemStack result = new ItemStack(Registries.ITEM.get(resultId), resultCount);

            int cookingTime = JsonHelper.getInt(json, "cookingtime", 400);
            float experience = JsonHelper.getFloat(json, "experience", 0.0f);

            return new CookingPotRecipe(recipeId, group, ingredients, container, result,
                    cookingTime, experience);
        }

        @Nullable
        @Override
        public CookingPotRecipe read(Identifier recipeId, PacketByteBuf buf) {
            String group = buf.readString(32767);
            int count = buf.readVarInt();
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(count, Ingredient.EMPTY);
            for (int i = 0; i < count; i++) {
                ingredients.set(i, Ingredient.fromPacket(buf));
            }
            Ingredient container = Ingredient.fromPacket(buf);
            ItemStack result = buf.readItemStack();
            int cookingTime = buf.readVarInt();
            float experience = buf.readFloat();
            return new CookingPotRecipe(recipeId, group, ingredients, container, result,
                    cookingTime, experience);
        }

        @Override
        public void write(PacketByteBuf buf, CookingPotRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ing : recipe.ingredients) {
                ing.write(buf);
            }
            recipe.container.write(buf);
            buf.writeItemStack(recipe.result);
            buf.writeVarInt(recipe.cookingTime);
            buf.writeFloat(recipe.experience);
        }
    }
}