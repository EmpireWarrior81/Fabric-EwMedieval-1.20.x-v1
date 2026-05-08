package net.empire.ewmedieval.client.renderer;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.entity.ArrowTier;
import net.empire.ewmedieval.entity.custom.TieredArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class TieredArrowEntityRenderer extends ProjectileEntityRenderer<TieredArrowEntity> {

    private static final Identifier IRON_TEXTURE = new Identifier(EwMedieval.MOD_ID,
            "textures/entity/projectiles/iron_arrow.png");
    private static final Identifier STEEL_TEXTURE = new Identifier(EwMedieval.MOD_ID,
            "textures/entity/projectiles/steel_arrow.png");

    public TieredArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(TieredArrowEntity entity) {
        return entity.getTier() == ArrowTier.IRON ? IRON_TEXTURE : STEEL_TEXTURE;
    }
}
