package net.empire.ewmedieval.item.fooditems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;


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

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getStackInHand(hand);

        if (heldStack.isFood()) {
            boolean alwaysEdible = heldStack.getItem().getFoodComponent() != null
                    && heldStack.getItem().getFoodComponent().isAlwaysEdible();

            if (player.canConsume(alwaysEdible)) {
                player.setCurrentHand(hand);
                return TypedActionResult.consume(heldStack);
            } else {
                return TypedActionResult.fail(heldStack);
            }
        }

        return ItemUsage.consumeHeldItem(world, player, hand);
    }
}