package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EasySkeletonRender extends SkeletonRenderer {
	private final ResourceLocation texture;

	public EasySkeletonRender(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(SkeletonRenderState renderState) {
		return this.texture;
	}
}