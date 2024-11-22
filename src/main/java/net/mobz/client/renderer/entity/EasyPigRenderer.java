package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyPigRenderer extends PigRenderer {
	private final ResourceLocation texture;

	public EasyPigRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(PigRenderState renderState) {
		return this.texture;
	}
}