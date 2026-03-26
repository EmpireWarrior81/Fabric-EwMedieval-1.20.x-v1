package net.empire.ewmedieval.item.fooditems;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConsumableItem extends Item {

    private final boolean hasFoodEffectTooltip;
    private final boolean hasCustomTooltip;

    /**
     * Items that can be consumed by an entity.
     * When consumed, they may affect the consumer somehow, and will give back
     * containers if applicable, regardless of their stack size.
     */
    public ConsumableItem(Settings settings) {
        super(settings);
        this.hasFoodEffectTooltip = false;
        this.hasCustomTooltip = false;
    }

    public ConsumableItem(Settings settings, boolean hasFoodEffectTooltip) {
        super(settings);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = false;
    }

    public ConsumableItem(Settings settings, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(settings);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = hasCustomTooltip;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity consumer) {
        if (!world.isClient) {
            this.affectConsumer(stack, world, consumer);
        }

        Item remainderItem = this.getRecipeRemainder();
        ItemStack containerStack = remainderItem != null ? new ItemStack(remainderItem) : ItemStack.EMPTY;

        if (this.isFood()) {
            // Let vanilla handle hunger/saturation/effects
            // super.finishUsing already decrements the stack
            ItemStack result = super.finishUsing(stack, world, consumer);

            // Now give back the container
            if (consumer instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
                if (!containerStack.isEmpty()) {
                    if (!player.getInventory().insertStack(containerStack)) {
                        player.dropItem(containerStack, false);
                    }
                }
            }
            return result;
        } else {
            // Non-food path stays the same
            PlayerEntity player = consumer instanceof PlayerEntity ? (PlayerEntity) consumer : null;

            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            }

            if (player != null) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            if (stack.isEmpty()) {
                return containerStack;
            } else {
                if (consumer instanceof PlayerEntity player2 && !player2.getAbilities().creativeMode) {
                    if (!containerStack.isEmpty() && !player2.getInventory().insertStack(containerStack)) {
                        player2.dropItem(containerStack, false);
                    }
                }
                return stack;
            }
        }
    }

    /**
     * Override this in subclasses to apply custom effects to the consumer,
     * for example curing status effects or triggering special behaviour.
     */
    public void affectConsumer(ItemStack stack, World world, LivingEntity consumer) {
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world,
                              List<Text> tooltip, TooltipContext context) {
        if (this.hasCustomTooltip) {
            String itemPath = Registries.ITEM.getId(this).getPath();
            tooltip.add(Text.translatable("tooltip.ewmedieval." + itemPath)
                    .formatted(Formatting.BLUE));
        }

        if (this.hasFoodEffectTooltip && this.isFood() && this.getFoodComponent() != null) {
            this.getFoodComponent().getStatusEffects().forEach(pair -> {
                StatusEffectInstance effect = pair.getFirst();
                StatusEffect effectType = effect.getEffectType();

                MutableText line = Text.translatable(effectType.getTranslationKey());

                if (effect.getAmplifier() > 0) {
                    line = line.append(" ")
                            .append(Text.translatable("potion.potency." + effect.getAmplifier()));
                }

                if (effect.getDuration() > 20) {
                    int seconds = effect.getDuration() / 20;
                    int minutes = seconds / 60;
                    int remainingSeconds = seconds % 60;
                    String duration = minutes > 0
                            ? String.format("%d:%02d", minutes, remainingSeconds)
                            : String.format("0:%02d", remainingSeconds);
                    line = line.append(" (").append(duration).append(")");
                }

                tooltip.add(line.formatted(effectType.getCategory().getFormatting()));
            });
        }
    }
}