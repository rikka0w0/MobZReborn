package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class EasyChickenRenderer extends ChickenRenderer {
	private final Identifier texture;

	public EasyChickenRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(ChickenRenderState renderState) {
		return this.texture;
	}
}
