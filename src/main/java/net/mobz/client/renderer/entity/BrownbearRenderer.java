package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Brownbear;

public class BrownbearRenderer extends MobRenderer<Brownbear, PolarBearModel<Brownbear>> {
    public BrownbearRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PolarBearModel<>(), 0.9F);
    }

    @Override
    public ResourceLocation getTextureLocation(Brownbear brownbear) {
        return new ResourceLocation("mobz:textures/entity/brownbear.png");
    }
}