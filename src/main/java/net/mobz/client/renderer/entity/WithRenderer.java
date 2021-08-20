package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.WithEntity;

public class WithRenderer extends MobRenderer<WithEntity, BlazeModel<WithEntity>> {
    public WithRenderer(EntityRendererProvider.Context context) {
        super(context, new BlazeModel<>(context.bakeLayer(ModelLayers.BLAZE)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WithEntity withtentity) {
        return new ResourceLocation("mobz:textures/entity/with.png");
    }
}
