package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyChickenRenderer extends ChickenRenderer {
	private final ResourceLocation texture;

	public EasyChickenRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(ChickenRenderState renderState) {
		return this.texture;
	}
}
