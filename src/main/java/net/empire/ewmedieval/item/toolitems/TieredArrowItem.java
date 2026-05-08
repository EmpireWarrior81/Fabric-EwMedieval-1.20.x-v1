package net.empire.ewmedieval.item.toolitems;

import net.empire.ewmedieval.entity.ArrowTier;
import net.empire.ewmedieval.entity.custom.TieredArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TieredArrowItem extends ArrowItem {

    private final ArrowTier tier;

    public TieredArrowItem(ArrowTier tier, Item.Settings settings) {
        super(settings);
        this.tier = tier;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new TieredArrowEntity(world, shooter, tier);
    }

    public ArrowTier getTier() {
        return tier;
    }
}
