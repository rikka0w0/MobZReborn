package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PillagerRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;

public class PillagerBossRenderer extends PillagerRenderer {
	private final Identifier texture;

	public PillagerBossRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
	}

	@Override
	protected void scale(IllagerRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(1.3F, 1.3F, 1.3F);
	}

	@Override
	public Identifier getTextureLocation(IllagerRenderState renderState) {
		return this.texture;
	}
}