package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class EasyCreeperRenderer extends CreeperRenderer {
	private final Identifier texture;

	public EasyCreeperRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(CreeperRenderState renderState) {
		return this.texture;
	}
}
