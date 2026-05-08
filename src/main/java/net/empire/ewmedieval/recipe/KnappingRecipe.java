package net.empire.ewmedieval.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
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
import net.minecraft.world.World;

public class KnappingRecipe implements Recipe<Inventory> {

    private final Identifier id;
    private final ItemStack output;
    private final Ingredient ingredient;
    private final boolean[][] pattern;
    private final int width;
    private final int height;
    private final boolean mirrored;

    public KnappingRecipe(Identifier id, ItemStack output, Ingredient ingredient,
                          boolean[][] pattern, int width, int height, boolean mirrored) {
        this.id = id;
        this.output = output;
        this.ingredient = ingredient;
        this.pattern = pattern;
        this.width = width;
        this.height = height;
        this.mirrored = mirrored;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        if (inv.size() != 9) return false;

        for (int i = 0; i < 9; i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty() && !ingredient.test(stack)) return false;
        }

        // true = slot is empty = has been chipped away
        boolean[][] input = new boolean[3][3];
        for (int i = 0; i < 9; i++) {
            input[i / 3][i % 3] = inv.getStack(i).isEmpty();
        }

        for (int y = 0; y <= 3 - height; y++) {
            for (int x = 0; x <= 3 - width; x++) {
                if (matchesAt(input, x, y, false)) return true;
                if (mirrored && matchesAt(input, x, y, true)) return true;
            }
        }
        return false;
    }

    private boolean matchesAt(boolean[][] input, int ox, int oy, boolean mirror) {
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                int sx = mirror ? width - 1 - px : px;
                // pattern[y][x] = true means cell must remain (unchipped = empty slot)
                if (pattern[py][sx] != input[oy + py][ox + px]) return false;
            }
        }
        // Cells outside the pattern must be chipped (slot has marker item = not empty)
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                boolean inside = x >= ox && x < ox + width && y >= oy && y < oy + height;
                if (!inside && input[y][x]) return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width == 3 && height == 3;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean[][] getPattern() {
        return pattern;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.KNAPPING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.KNAPPING_TYPE;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Type implements RecipeType<KnappingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "knapping";
    }

    public static class Serializer implements RecipeSerializer<KnappingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID = new Identifier("ewmedieval", "knapping");

        @Override
        public KnappingRecipe read(Identifier id, JsonObject json) {
            JsonObject resultObj = JsonHelper.getObject(json, "result");
            Identifier resultId = new Identifier(JsonHelper.getString(resultObj, "item"));
            int resultCount = JsonHelper.getInt(resultObj, "count", 1);
            ItemStack result = new ItemStack(Registries.ITEM.get(resultId), resultCount);

            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));

            JsonArray patternArray = JsonHelper.getArray(json, "pattern");
            int height = patternArray.size();
            int width = patternArray.get(0).getAsString().length();

            boolean[][] pattern = new boolean[height][width];
            for (int y = 0; y < height; y++) {
                String row = patternArray.get(y).getAsString();
                if (row.length() != width) throw new IllegalArgumentException("Pattern rows must be same width");
                for (int x = 0; x < width; x++) {
                    char c = row.charAt(x);
                    // 'x' = cell must remain unchipped; ' ' = cell must be chipped
                    pattern[y][x] = (c == 'x' || c == 'X');
                }
            }

            boolean mirrored = JsonHelper.getBoolean(json, "mirrored", false);
            return new KnappingRecipe(id, result, ingredient, pattern, width, height, mirrored);
        }

        @Override
        public KnappingRecipe read(Identifier id, PacketByteBuf buf) {
            ItemStack output = buf.readItemStack();
            Ingredient ingredient = Ingredient.fromPacket(buf);
            int width = buf.readVarInt();
            int height = buf.readVarInt();
            boolean[][] pattern = new boolean[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pattern[y][x] = buf.readBoolean();
                }
            }
            boolean mirrored = buf.readBoolean();
            return new KnappingRecipe(id, output, ingredient, pattern, width, height, mirrored);
        }

        @Override
        public void write(PacketByteBuf buf, KnappingRecipe recipe) {
            buf.writeItemStack(recipe.output);
            recipe.ingredient.write(buf);
            buf.writeVarInt(recipe.width);
            buf.writeVarInt(recipe.height);
            for (int y = 0; y < recipe.height; y++) {
                for (int x = 0; x < recipe.width; x++) {
                    buf.writeBoolean(recipe.pattern[y][x]);
                }
            }
            buf.writeBoolean(recipe.mirrored);
        }
    }
}
