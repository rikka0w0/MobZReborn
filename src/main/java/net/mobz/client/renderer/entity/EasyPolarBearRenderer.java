package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PolarBearRenderer;
import net.minecraft.client.renderer.entity.state.PolarBearRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.PolarBear;

public class EasyPolarBearRenderer<T extends PolarBear>
		extends PolarBearRenderer {
	private final ResourceLocation texture;

	public EasyPolarBearRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(PolarBearRenderState renderState) {
		return this.texture;
	}
}