package com.ninni.uber.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class ClottonParticle extends SimpleAnimatedParticle {
    private final SpriteSet sprites;


    ClottonParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z, spriteSet,-0.01f);
        this.sprites = spriteSet;
        this.quadSize = 0.25f;
        this.lifetime = 60;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        if (age < lifetime) {
            if (alpha > 0.1F) {
                xd += 0.01F;
                zd += 0.01F;
                alpha -= 0.015F;
            }
            else this.remove();
        }
        this.setSpriteFromAge(sprites);
    }

    @Environment(value = EnvType.CLIENT)
    public record Factory(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel level, double d, double e, double f, double g, double h, double i) {
            return new ClottonParticle(level, d, e, f, this.spriteSet);
        }
    }
}
