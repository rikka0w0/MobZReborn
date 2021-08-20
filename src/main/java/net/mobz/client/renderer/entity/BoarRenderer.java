package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Boar;

public class BoarRenderer extends MobRenderer<Boar, PigModel<Boar>> {
    public BoarRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel<>(context.bakeLayer(ModelLayers.PIG)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boar boar) {
        return new ResourceLocation("mobz:textures/entity/boar.png");
    }
}