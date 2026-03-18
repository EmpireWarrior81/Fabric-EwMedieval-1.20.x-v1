package net.empire.ewmedieval.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

// DrinkableItem — like ConsumableItem but plays the drinking animation instead of eating.
// Used for liquids like apple cider, bone broth, drinks etc.
public class DrinkableItem extends ConsumableItem {

    public DrinkableItem(Settings settings) {
        super(settings);
    }

    public DrinkableItem(Settings settings, boolean hasFoodEffectTooltip) {
        super(settings, hasFoodEffectTooltip);
    }

    public DrinkableItem(Settings settings, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(settings, hasFoodEffectTooltip, hasCustomTooltip);
    }

    // Drinks are consumed faster than food — 32 ticks instead of 40
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    // Play the drinking animation instead of the eating animation
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getStackInHand(hand);

        if (heldStack.isFood()) {
            // Only allow drinking if the player can eat
            // (alwaysEdible items can be consumed even when not hungry)
            boolean alwaysEdible = heldStack.getItem().getFoodComponent() != null
                    && heldStack.getItem().getFoodComponent().isAlwaysEdible();

            if (player.canConsume(alwaysEdible)) {
                player.setCurrentHand(hand);
                return TypedActionResult.consume(heldStack);
            } else {
                return TypedActionResult.fail(heldStack);
            }
        }

        // For non-food drinkables, start using instantly
        return ItemUsage.consumeHeldItem(world, player, hand);
    }
}