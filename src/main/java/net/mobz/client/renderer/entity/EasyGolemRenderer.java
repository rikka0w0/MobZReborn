package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.layers.IronGolemCrackinessLayer;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyGolemRenderer extends IronGolemRenderer {
	private final ResourceLocation texture;

	public EasyGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		this(context, texture, true);
	}

	protected EasyGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture, boolean useDefaultCrack) {
		super(context);
		if (!useDefaultCrack) {
			this.layers.removeIf(layer -> layer instanceof IronGolemCrackinessLayer);
		}
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(IronGolemRenderState renderState) {
		return this.texture;
	}
}
