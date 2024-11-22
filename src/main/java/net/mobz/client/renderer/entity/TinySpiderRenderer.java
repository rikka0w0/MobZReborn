package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.TinySpider;

public class TinySpiderRenderer extends EasySpiderRenderer<TinySpider> {
	public TinySpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, texture, true);
	}

	@Override
	protected void scale(LivingEntityRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(0.1F, 0.1F, 0.1F);
	}
}
