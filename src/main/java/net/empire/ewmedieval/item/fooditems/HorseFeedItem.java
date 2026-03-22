package net.empire.ewmedieval.item.fooditems;

import net.empire.ewmedieval.util.ModTags;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HorseFeedItem extends Item {

    public static final List<StatusEffectInstance> EFFECTS = List.of(
            new StatusEffectInstance(StatusEffects.SPEED, 6000, 1),
            new StatusEffectInstance(StatusEffects.JUMP_BOOST, 6000, 0));

    public HorseFeedItem(Settings settings) {
        super(settings);
    }

    // Call this in onInitialize()
    public static void init() {
        UseEntityCallback.EVENT.register(HorseFeedItem::onHorseFeedApplied);
    }

    private static ActionResult onHorseFeedApplied(PlayerEntity player, World world,
                                                   Hand hand, Entity target,
                                                   @Nullable EntityHitResult entityHitResult) {
        if (player.isSpectator()) return ActionResult.PASS;

        ItemStack heldStack = player.getStackInHand(hand);

        if (!(heldStack.getItem() instanceof HorseFeedItem)) return ActionResult.PASS;

        if (target instanceof LivingEntity entity
                && target.getType().isIn(ModTags.HORSE_FEED_USERS)) {

            boolean isTameable = entity instanceof AbstractHorseEntity;

            if (entity.isAlive() && (!isTameable || ((AbstractHorseEntity) entity).isTame())) {
                if (!world.isClient) {
                    // Restore full health
                    entity.setHealth(entity.getMaxHealth());

                    // Apply speed and jump boost effects
                    for (StatusEffectInstance effect : EFFECTS) {
                        entity.addStatusEffect(new StatusEffectInstance(effect));
                    }

                    // Play horse eating sound
                    world.playSound(null, target.getBlockPos(),
                            SoundEvents.ENTITY_HORSE_EAT,
                            SoundCategory.PLAYERS, 0.8F, 0.8F);

                    // Spawn heart particles
                    for (int i = 0; i < 5; ++i) {
                        double xSpeed = world.getRandom().nextGaussian() * 0.02D;
                        double ySpeed = world.getRandom().nextGaussian() * 0.02D;
                        double zSpeed = world.getRandom().nextGaussian() * 0.02D;
                        world.addParticle(ParticleTypes.HEART,
                                entity.getParticleX(1.0D),
                                entity.getRandomBodyY() + 0.5D,
                                entity.getParticleZ(1.0D),
                                xSpeed, ySpeed, zSpeed);
                    }

                    if (!player.getAbilities().creativeMode) {
                        heldStack.decrement(1);
                    }
                }

                return ActionResult.success(world.isClient);
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world,
                              List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("tooltip.ewmedieval.horse_feed.when_feeding")
                .formatted(Formatting.GRAY));

        for (StatusEffectInstance effectInstance : EFFECTS) {
            StatusEffect effect = effectInstance.getEffectType();
            Text effectName = effect.getName();

            var line = Text.literal(" ").append(effectName);

            if (effectInstance.getAmplifier() > 0) {
                line = line.copy().append(" ")
                        .append(Text.translatable("potion.potency." + effectInstance.getAmplifier()));
            }

            if (effectInstance.getDuration() > 20) {
                int seconds = effectInstance.getDuration() / 20;
                int minutes = seconds / 60;
                int remainingSeconds = seconds % 60;
                String duration = minutes > 0
                        ? String.format("%d:%02d", minutes, remainingSeconds)
                        : String.format("0:%02d", remainingSeconds);
                line = line.copy().append(" (").append(duration).append(")");
            }

            tooltip.add(line.formatted(effect.getCategory().getFormatting()));
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player,
                                    LivingEntity target, Hand hand) {
        if (target instanceof HorseEntity horse) {
            if (horse.isAlive() && horse.isTame()) {
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}