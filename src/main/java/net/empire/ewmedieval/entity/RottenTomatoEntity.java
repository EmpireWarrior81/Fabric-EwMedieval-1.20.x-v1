package net.empire.ewmedieval.entity;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class RottenTomatoEntity extends ThrownItemEntity {

    public RottenTomatoEntity(EntityType<? extends RottenTomatoEntity> entityType, World world) {
        super(entityType, world);
    }

    public RottenTomatoEntity(World world, LivingEntity thrower) {
        super(ModEntityTypes.ROTTEN_TOMATO, thrower, world);
    }

    public RottenTomatoEntity(World world, double x, double y, double z) {
        super(ModEntityTypes.ROTTEN_TOMATO, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ROTTEN_TOMATO;
    }

    // Spawn item particles on impact — triggered by sendEntityStatus byte 3
    @Override
    public void handleStatus(byte status) {
        if (status == 3) {
            ItemStack entityStack = new ItemStack(this.getDefaultItem());
            ParticleEffect particleData = new ItemStackParticleEffect(ParticleTypes.ITEM, entityStack);

            for (int i = 0; i < 12; ++i) {
                this.getWorld().addParticle(particleData,
                        this.getX(), this.getY(), this.getZ(),
                        ((double) this.random.nextFloat() * 2.0D - 1.0D) * 0.1F,
                        ((double) this.random.nextFloat() * 2.0D - 1.0D) * 0.1F + 0.1F,
                        ((double) this.random.nextFloat() * 2.0D - 1.0D) * 0.1F);
            }
        }
    }

    // Deal 0 damage on entity hit and play hit sound
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0);
        // Replace SoundEvents.ENTITY_EGG_THROW with your custom sound when added
        this.playSound(SoundEvents.ENTITY_SNOWBALL_THROW,
                1.0F,
                (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    // On any collision — broadcast particle event, play sound, discard
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.playSound(SoundEvents.ENTITY_SLIME_ATTACK,
                    1.0F,
                    (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.discard();
        }
    }
}