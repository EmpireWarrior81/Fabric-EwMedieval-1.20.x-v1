package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.forging.ForgingQuality;
import net.empire.ewmedieval.forging.ForgingQualityHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ForgingQualityTooltipMixin {

    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendForgingQualityTooltip(ItemStack stack, @Nullable World world,
                                              List<Text> tooltip, TooltipContext context,
                                              CallbackInfo ci) {
        ForgingQuality quality = ForgingQualityHelper.getQuality(stack);
        if (quality == ForgingQuality.NONE) return;

        tooltip.add(quality.getDisplayName());

        if (ForgingQualityHelper.needsGrinding(stack)) {
            tooltip.add(Text.translatable("forging.ewmedieval.needs_grinding").formatted(Formatting.RED));
        }

        if (ForgingQualityHelper.isHeated(stack)) {
            tooltip.add(Text.translatable("forging.ewmedieval.heated").formatted(Formatting.RED));
        }
    }
}
