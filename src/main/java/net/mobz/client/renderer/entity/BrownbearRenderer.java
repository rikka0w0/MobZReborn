package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PolarBearModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Brownbear;

public abstract class BrownbearRenderer extends MobRenderer<Brownbear, PolarBearModel<Brownbear>> {
    public BrownbearRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PolarBearModel<>(), 0.9F);
    }

    @Override
    public ResourceLocation getTextureLocation(Brownbear brownbear) {
        return new ResourceLocation("mobz:textures/entity/brownbear.png");
    }
}