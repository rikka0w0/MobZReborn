package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Brownbear;

public class BrownbearRenderer extends MobRenderer<Brownbear, PolarBearModel<Brownbear>> {
    public BrownbearRenderer(EntityRendererProvider.Context context) {
        super(context, new PolarBearModel<>(context.bakeLayer(ModelLayers.POLAR_BEAR)), 0.9F);
    }

    @Override
    public ResourceLocation getTextureLocation(Brownbear brownbear) {
        return new ResourceLocation("mobz:textures/entity/brownbear.png");
    }
}