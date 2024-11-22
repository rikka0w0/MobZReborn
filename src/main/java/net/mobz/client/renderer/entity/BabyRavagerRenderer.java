package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RavagerRenderer;
import net.minecraft.client.renderer.entity.state.RavagerRenderState;
import net.minecraft.resources.ResourceLocation;

public class BabyRavagerRenderer extends RavagerRenderer {
	private final ResourceLocation texture;

	public BabyRavagerRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.shadowRadius = 0.4F;
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(RavagerRenderState ravagerEntity) {
		return this.texture;
	}

	@Override
	protected void scale(RavagerRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(0.4F, 0.4F, 0.4F);
	}
}
