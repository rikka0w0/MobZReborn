package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class EasySkeletonRender extends SkeletonRenderer {
	private final Identifier texture;

	public EasySkeletonRender(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(SkeletonRenderState renderState) {
		return this.texture;
	}
}