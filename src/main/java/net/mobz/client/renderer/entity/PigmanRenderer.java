package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class PigmanRenderer extends PiglinRenderer {
	private final ResourceLocation texture;

	public PigmanRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context,
				ModelLayers.PIGLIN,
				ModelLayers.PIGLIN_BABY,
				ModelLayers.PIGLIN_ARMOR,
				ModelLayers.PIGLIN_BABY_ARMOR
		);

		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(PiglinRenderState renderState) {
		return this.texture;
	}
}
