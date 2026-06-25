package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.world.entity.monster.illager.SpellcasterIllager;
import net.minecraft.resources.Identifier;

public class EasyEvokerRenderer extends EvokerRenderer<SpellcasterIllager> {
	private final Identifier texture;

	public EasyEvokerRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(EvokerRenderState renderState) {
		return this.texture;
	}
}