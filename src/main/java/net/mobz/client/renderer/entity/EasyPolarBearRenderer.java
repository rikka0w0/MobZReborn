package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PolarBear;

public class EasyPolarBearRenderer<T extends PolarBear> extends MobRenderer<T, PolarBearModel<T>> {
	private final ResourceLocation texture;

    public EasyPolarBearRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PolarBearModel<>(context.bakeLayer(ModelLayers.POLAR_BEAR)), 0.9F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(PolarBear brownbear) {
        return this.texture;
    }
}