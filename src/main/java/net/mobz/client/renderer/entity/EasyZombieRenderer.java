package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class EasyZombieRenderer extends ZombieRenderer {
	private final ResourceLocation texture;

	public EasyZombieRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieRenderState renderState) {
		return this.texture;
	}
}
