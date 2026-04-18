package net.empire.ewmedieval.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SteamParticle extends SpriteBillboardParticle {

    protected SteamParticle(ClientWorld world, double x, double y, double z,
                             double vx, double vy, double vz) {
        super(world, x, y, z);
        this.scale = 0.5f;
        this.setBoundingBoxSpacing(0.25f, 0.25f);
        this.maxAge = this.random.nextInt(50) + 80;
        this.gravityStrength = 3.0E-6f;
        this.velocityX = vx;
        this.velocityY = vy + (this.random.nextFloat() / 500.0f);
        this.velocityZ = vz;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ < this.maxAge && !(this.alpha <= 0.0f)) {
            this.velocityX += this.random.nextFloat() / 5000.0f * (this.random.nextBoolean() ? 1 : -1);
            this.velocityZ += this.random.nextFloat() / 5000.0f * (this.random.nextBoolean() ? 1 : -1);
            this.velocityY -= this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.age >= this.maxAge - 60 && this.alpha > 0.01f) {
                this.alpha -= 0.02f;
            }
        } else {
            this.markDead();
        }
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            SteamParticle particle = new SteamParticle(world, x, y + 0.3, z, vx, vy, vz);
            particle.setAlpha(0.6f);
            particle.setSprite(spriteProvider.getSprite(world.random));
            return particle;
        }
    }
}