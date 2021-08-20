package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PandaModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Blackbear;

public class BlackbearRenderer extends MobRenderer<Blackbear, PandaModel<Blackbear>> {
    public BlackbearRenderer(EntityRendererProvider.Context context) {
        super(context, new PandaModel<>(context.bakeLayer(ModelLayers.PANDA)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Blackbear Blackbear) {
        return new ResourceLocation("mobz:textures/entity/blackbear.png");
    }
}