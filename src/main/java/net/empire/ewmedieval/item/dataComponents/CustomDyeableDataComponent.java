package net.empire.ewmedieval.item.dataComponents;

import net.empire.ewmedieval.util.ModTags;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ColorHelper;

import java.util.List;

public class CustomDyeableDataComponent {
    public static final String NBT_KEY = "CustomColor";
    public static final int DEFAULT_COLOR = -6265536;

    public static int getColor(ItemStack stack, int defaultColor) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains(NBT_KEY)) {
            return ColorHelper.Argb.getAlpha(nbt.getInt(NBT_KEY));
        }
        return defaultColor;
    }

    public static ItemStack setColor(ItemStack stack, List<DyeItem> dyes) {
        if (!stack.isIn(ModTags.DYEABLE)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack = stack.copyWithCount(1);
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int m = 0;


            int existing = getColor(itemStack, DEFAULT_COLOR);
            if (existing != DEFAULT_COLOR) {
                int r = ColorHelper.Argb.getRed(existing);
                int g = ColorHelper.Argb.getGreen(existing);
                int b = ColorHelper.Argb.getBlue(existing);
                l += Math.max(r, Math.max(g, b));
                i += r;
                j += g;
                k += b;
                ++m;
            }


            for (DyeItem dye : dyes) {
                float[] components = dye.getColor().getColorComponents();
                int r = (int)(components[0] * 255);
                int g = (int)(components[1] * 255);
                int b = (int)(components[2] * 255);

                l += Math.max(r, Math.max(g, b));
                i += r;
                j += g;
                k += b;
                m++;
            }


            int r = i / m;
            int g = j / m;
            int b = k / m;
            float f = (float) l / (float) m;
            float gmax = (float) Math.max(r, Math.max(g, b));
            r = (int)((float)r * f / gmax);
            g = (int)((float)g * f / gmax);
            b = (int)((float)b * f / gmax);

            int finalColor = ColorHelper.Argb.getArgb(0, r, g, b);


            itemStack.getOrCreateNbt().putInt(NBT_KEY, finalColor);

            return itemStack;
        }
    }
}
