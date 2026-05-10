package net.empire.ewmedieval.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ForgingRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    /** Shaped 3×3 grid; EMPTY = must be empty slot. */
    private final Ingredient[][] pattern;
    private final int hits;
    private final boolean mirrored;
    /** Simple recipes skip the minigame — each hammer press counts as one perfect hit. */
    private final boolean simple;
    /** Whether the output item should come out heated (default true). Set false for cold-forged items like copper tools. */
    private final boolean heated;
    /** Whether to stamp quality/grinding/ForgedBy NBT on the output (default true). Set false for simple material conversions. */
    private final boolean trackQuality;
    /** Shapeless recipes match ingredients in any slot position. */
    private final boolean shapeless;

    public ForgingRecipe(Identifier id, ItemStack output, Ingredient[][] pattern, int hits, boolean mirrored, boolean simple, boolean heated, boolean trackQuality, boolean shapeless) {
        this.id = id;
        this.output = output;
        this.pattern = pattern;
        this.hits = hits;
        this.mirrored = mirrored;
        this.simple = simple;
        this.heated = heated;
        this.trackQuality = trackQuality;
        this.shapeless = shapeless;
    }

    public int getHits()            { return hits; }
    public boolean isSimple()       { return simple; }
    public boolean isOutputHeated() { return heated; }
    public boolean tracksQuality()  { return trackQuality; }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (inventory.size() < 9) return false;
        if (shapeless) return matchesShapeless(inventory);
        if (matchesGrid(inventory, false)) return true;
        return mirrored && matchesGrid(inventory, true);
    }

    private boolean matchesShapeless(SimpleInventory inv) {
        DefaultedList<Ingredient> needed = getIngredients();
        boolean[] matched = new boolean[needed.size()];
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.isEmpty()) continue;
            boolean found = false;
            for (int j = 0; j < needed.size(); j++) {
                if (!matched[j] && needed.get(j).test(stack)) {
                    matched[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        for (boolean m : matched) if (!m) return false;
        return true;
    }

    private boolean matchesGrid(SimpleInventory inv, boolean mirror) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int invIdx = row * 3 + col;
                int patCol = mirror ? 2 - col : col;
                Ingredient ing = pattern[row][patCol];
                ItemStack stack = inv.getStack(invIdx);
                if (ing.isEmpty()) {
                    if (!stack.isEmpty()) return false;
                } else {
                    if (!ing.test(stack)) return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) { return true; }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) { return output; }

    @Override
    public Identifier getId() { return id; }

    /** Flat list of non-empty ingredients — used by craftItem to consume inputs. */
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> flat = DefaultedList.of();
        for (Ingredient[] row : pattern) {
            for (Ingredient ing : row) {
                if (!ing.isEmpty()) flat.add(ing);
            }
        }
        return flat;
    }

    @Override
    public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }

    @Override
    public RecipeType<?> getType() { return Type.INSTANCE; }

    public static class Type implements RecipeType<ForgingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "smithing_anvil";
        @Override public String toString() { return "ewmedieval:smithing_anvil"; }
    }

    public static class Serializer implements RecipeSerializer<ForgingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID = new Identifier("ewmedieval", "smithing_anvil");

        @Override
        public ForgingRecipe read(Identifier id, JsonObject json) {
            ItemStack output = net.minecraft.recipe.ShapedRecipe.outputFromJson(json.getAsJsonObject("result"));
            int hits = json.has("hits") ? json.get("hits").getAsInt() : 5;
            boolean mirrored = json.has("mirrored") && json.get("mirrored").getAsBoolean();

            Ingredient[][] pattern = new Ingredient[3][3];
            for (int r = 0; r < 3; r++)
                for (int c = 0; c < 3; c++)
                    pattern[r][c] = Ingredient.EMPTY;

            if (json.has("pattern") && json.has("key")) {
                Map<Character, Ingredient> keys = new HashMap<>();
                JsonObject keyObj = json.getAsJsonObject("key");
                for (Map.Entry<String, com.google.gson.JsonElement> entry : keyObj.entrySet()) {
                    keys.put(entry.getKey().charAt(0), Ingredient.fromJson(entry.getValue()));
                }
                JsonArray patternArray = json.getAsJsonArray("pattern");
                for (int r = 0; r < Math.min(patternArray.size(), 3); r++) {
                    String row = patternArray.get(r).getAsString();
                    for (int c = 0; c < Math.min(row.length(), 3); c++) {
                        char ch = row.charAt(c);
                        if (ch != ' ' && keys.containsKey(ch)) {
                            pattern[r][c] = keys.get(ch);
                        }
                    }
                }
            } else if (json.has("ingredients")) {
                // Legacy shapeless — map flat list to row 0 left-to-right
                JsonArray arr = json.getAsJsonArray("ingredients");
                for (int i = 0; i < Math.min(arr.size(), 9); i++) {
                    JsonObject elem = arr.get(i).getAsJsonObject();
                    if (!elem.entrySet().isEmpty()) {
                        pattern[i / 3][i % 3] = Ingredient.fromJson(elem);
                    }
                }
            }

            boolean simple       = json.has("simple") && json.get("simple").getAsBoolean();
            boolean heated       = !json.has("heated") || json.get("heated").getAsBoolean();
            boolean trackQuality = !json.has("quality") || json.get("quality").getAsBoolean();
            boolean shapeless    = json.has("shapeless") && json.get("shapeless").getAsBoolean();
            return new ForgingRecipe(id, output, pattern, hits, mirrored, simple, heated, trackQuality, shapeless);
        }

        @Override
        public ForgingRecipe read(Identifier id, PacketByteBuf buf) {
            int hits = buf.readInt();
            boolean mirrored     = buf.readBoolean();
            boolean simple       = buf.readBoolean();
            boolean heated       = buf.readBoolean();
            boolean trackQuality = buf.readBoolean();
            boolean shapeless    = buf.readBoolean();
            Ingredient[][] pattern = new Ingredient[3][3];
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    pattern[r][c] = buf.readBoolean() ? Ingredient.EMPTY : Ingredient.fromPacket(buf);
                }
            }
            ItemStack output = buf.readItemStack();
            return new ForgingRecipe(id, output, pattern, hits, mirrored, simple, heated, trackQuality, shapeless);
        }

        @Override
        public void write(PacketByteBuf buf, ForgingRecipe recipe) {
            buf.writeInt(recipe.hits);
            buf.writeBoolean(recipe.mirrored);
            buf.writeBoolean(recipe.simple);
            buf.writeBoolean(recipe.heated);
            buf.writeBoolean(recipe.trackQuality);
            buf.writeBoolean(recipe.shapeless);
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    Ingredient ing = recipe.pattern[r][c];
                    if (ing.isEmpty()) {
                        buf.writeBoolean(true);
                    } else {
                        buf.writeBoolean(false);
                        ing.write(buf);
                    }
                }
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
