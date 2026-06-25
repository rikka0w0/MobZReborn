package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class BigBossRenderer extends EasyZombieRenderer {
	public BigBossRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context, texture);
	}

	@Override
	protected void scale(ZombieRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(2.5F, 2.5F, 2.5F);
	}
}
