package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;

public class EasyPigRenderer<T extends Pig> extends MobRenderer<T, PigModel<T>> {
	private final ResourceLocation texture;

    public EasyPigRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PigModel<>(context.bakeLayer(ModelLayers.PIG)), 0.7F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T boar) {
        return this.texture;
    }
}