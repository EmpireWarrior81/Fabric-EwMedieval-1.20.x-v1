/*
package net.empire.ewmedieval.item;

import net.empire.ewmedieval.effect.ModEffects;
import net.empire.ewmedieval.network.ThirstSyncPacket;
import net.empire.ewmedieval.thirst.ThirstHolder;
import net.empire.ewmedieval.thirst.ThirstManager;
import net.empire.ewmedieval.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class WaterItem extends Item {

    private final int thirstAmount;
    private final float sickChance;

    public WaterItem(int thirstAmount, float sickChance, Settings settings) {
        super(settings);
        this.thirstAmount = thirstAmount;
        this.sickChance   = sickChance;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity player) {
            ThirstManager tm = ((ThirstHolder) player).ewmedieval$getThirstManager();
            tm.add(thirstAmount);
            ThirstSyncPacket.send(player, tm.getThirstLevel());

            if (sickChance > 0 && world.random.nextFloat() < sickChance) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.THIRST_EFFECT, 1200, 0, false, true, true));
            }
        }

        if (!world.isClient()) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    ModSounds.WATER_SIP, SoundCategory.PLAYERS,
                    1f, 0.9f + world.random.nextFloat() * 0.2f);
        }

        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            if (stack.getCount() == 1) return new ItemStack(Items.GLASS_BOTTLE);
            stack.decrement(1);
            player.getInventory().offerOrDrop(new ItemStack(Items.GLASS_BOTTLE));
        }
        return stack;
    }
}
*/
