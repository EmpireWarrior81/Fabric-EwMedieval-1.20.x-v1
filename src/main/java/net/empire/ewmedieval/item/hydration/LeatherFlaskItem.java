/*
package net.empire.ewmedieval.item;

import net.empire.ewmedieval.effect.ModEffects;
import net.empire.ewmedieval.network.ThirstSyncPacket;
import net.empire.ewmedieval.sound.ModSounds;
import net.empire.ewmedieval.thirst.ThirstHolder;
import net.empire.ewmedieval.thirst.ThirstManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LeatherFlaskItem extends Item {

    private static final float[] SICK_CHANCES = {0.0f, 0.10f, 0.30f};

    public LeatherFlaskItem(Settings settings) {
        super(settings);
    }

    public static int getFillLevel(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains("fillLevel") ? nbt.getInt("fillLevel") : 0;
    }

    public static int getQuality(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains("quality") ? nbt.getInt("quality") : 2;
    }

    private static void setFill(ItemStack stack, int fillLevel, int quality) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("fillLevel", fillLevel);
        nbt.putInt("quality", quality);
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
        ItemStack stack = user.getStackInHand(hand);

        if (getFillLevel(stack) < 2) {
            HitResult hit = user.raycast(4.5, 0, true);
            if (hit instanceof BlockHitResult blockHit) {
                BlockPos pos = blockHit.getBlockPos();
                var fluid = world.getFluidState(pos);
                if (fluid.isIn(FluidTags.WATER) && fluid.getFluid() == Fluids.WATER) {
                    if (!world.isClient()) {
                        setFill(stack, 2, 2);
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                ModSounds.FILL_FLASK, SoundCategory.PLAYERS, 1f, 1f);
                    }
                    return TypedActionResult.success(stack, world.isClient());
                }
            }
        }

        if (getFillLevel(stack) > 0) {
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return TypedActionResult.fail(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int fillLevel = getFillLevel(stack);
        if (fillLevel <= 0) return stack;

        if (user instanceof ServerPlayerEntity player) {
            ThirstManager tm = ((ThirstHolder) player).ewmedieval$getThirstManager();
            tm.add(2);
            ThirstSyncPacket.send(player, tm.getThirstLevel());

            int quality = getQuality(stack);
            float sickChance = SICK_CHANCES[Math.max(0, Math.min(2, quality))];
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

        setFill(stack, fillLevel - 1, getQuality(stack));
        return stack;
    }
}
*/
