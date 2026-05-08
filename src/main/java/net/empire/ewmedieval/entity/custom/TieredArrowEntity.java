package net.empire.ewmedieval.entity.custom;

import net.empire.ewmedieval.entity.ArrowTier;
import net.empire.ewmedieval.entity.ModEntityTypes;
import net.empire.ewmedieval.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class TieredArrowEntity extends ArrowEntity {

    private static final TrackedData<Byte> TIER = DataTracker.registerData(
            TieredArrowEntity.class, TrackedDataHandlerRegistry.BYTE);

    public TieredArrowEntity(EntityType<? extends TieredArrowEntity> type, World world) {
        super(type, world);
    }

    public TieredArrowEntity(World world, LivingEntity shooter, ArrowTier tier) {
        super(ModEntityTypes.TIERED_ARROW, world);
        setOwner(shooter);
        setPosition(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        if (shooter instanceof PlayerEntity) {
            this.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        }
        setTier(tier);
        setDamage(2.0 * tier.getDamageMultiplier());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(TIER, (byte) 0);
    }

    public ArrowTier getTier() {
        return ArrowTier.fromByte(dataTracker.get(TIER));
    }

    public void setTier(ArrowTier tier) {
        dataTracker.set(TIER, tier.toByte());
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(getTier() == ArrowTier.IRON ? ModItems.IRON_ARROW : ModItems.STEEL_ARROW);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("ArrowTier", getTier().toByte());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("ArrowTier")) {
            setTier(ArrowTier.fromByte(nbt.getByte("ArrowTier")));
        }
    }
}
