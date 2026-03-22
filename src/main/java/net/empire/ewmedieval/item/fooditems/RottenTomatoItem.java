package net.empire.ewmedieval.item.fooditems;

import net.empire.ewmedieval.entity.RottenTomatoEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RottenTomatoItem extends Item {

    public RottenTomatoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getStackInHand(hand);

        world.playSound(null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW, // closest vanilla equivalent to a throw sound
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            RottenTomatoEntity projectile = new RottenTomatoEntity(world, player);
            projectile.setItem(heldStack);
            projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(projectile);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        if (!player.getAbilities().creativeMode) {
            heldStack.decrement(1);
        }

        return TypedActionResult.success(heldStack, world.isClient());
    }
}