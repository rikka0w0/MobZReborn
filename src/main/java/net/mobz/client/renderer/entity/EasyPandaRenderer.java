package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PandaModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Panda;

public class EasyPandaRenderer<T extends Panda> extends MobRenderer<T, PandaModel<T>> {
	private final ResourceLocation texture;

    public EasyPandaRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PandaModel<>(context.bakeLayer(ModelLayers.PANDA)), 0.8F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T Blackbear) {
        return this.texture;
    }
}