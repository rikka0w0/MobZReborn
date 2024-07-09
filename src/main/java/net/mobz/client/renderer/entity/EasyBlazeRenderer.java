package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Blaze;

public class EasyBlazeRenderer extends MobRenderer<Blaze, BlazeModel<Blaze>> {
	private final ResourceLocation texture;

    public EasyBlazeRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new BlazeModel<>(context.bakeLayer(ModelLayers.BLAZE)), 0.5F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(Blaze withtentity) {
        return this.texture;
    }
}
