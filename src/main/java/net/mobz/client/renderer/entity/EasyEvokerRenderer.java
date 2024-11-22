package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.resources.ResourceLocation;

public class EasyEvokerRenderer extends EvokerRenderer<SpellcasterIllager> {
	private final ResourceLocation texture;

	public EasyEvokerRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(EvokerRenderState renderState) {
		return this.texture;
	}
}