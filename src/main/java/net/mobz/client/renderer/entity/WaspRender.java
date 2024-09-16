package net.mobz.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.client.renderer.model.WaspModel;
import net.mobz.entity.Wasp;

public class WaspRender extends MobRenderer<Wasp, WaspModel<Wasp>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace(
			"textures/entity/bee/bee_angry.png");

	public WaspRender(EntityRendererProvider.Context context) {
		super(context, new WaspModel<>(context.bakeLayer(ModelLayers.BEE)), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(Wasp wasp) {
		return TEXTURE;
	}
}
