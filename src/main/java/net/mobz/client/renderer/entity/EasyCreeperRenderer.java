package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyCreeperRenderer extends CreeperRenderer {
	private final ResourceLocation texture;

	public EasyCreeperRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperRenderState renderState) {
		return this.texture;
	}
}
