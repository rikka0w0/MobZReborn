package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PandaRenderer;
import net.minecraft.client.renderer.entity.state.PandaRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyPandaRenderer extends PandaRenderer {
	private final ResourceLocation texture;

	public EasyPandaRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.shadowRadius = 0.8F;
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(PandaRenderState renderState) {
		return this.texture;
	}
}