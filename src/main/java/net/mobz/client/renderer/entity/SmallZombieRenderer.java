package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class SmallZombieRenderer extends EasyZombieRenderer {
	public SmallZombieRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, texture);
	}

	@Override
	protected void scale(ZombieRenderState smallZombie, PoseStack matrixStack) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
	}
}
