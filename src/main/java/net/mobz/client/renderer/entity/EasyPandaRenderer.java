package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PandaRenderer;
import net.minecraft.client.renderer.entity.state.PandaRenderState;
import net.minecraft.resources.Identifier;

public class EasyPandaRenderer extends PandaRenderer {
	private final Identifier texture;

	public EasyPandaRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.shadowRadius = 0.8F;
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(PandaRenderState renderState) {
		return this.texture;
	}
}