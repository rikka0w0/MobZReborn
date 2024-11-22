package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Dwarf;

public class DwarfEntityRenderer extends EasyHumanoidRenderer<Dwarf> {
	public DwarfEntityRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, texture);
	}

	@Override
	protected void scale(PlayerRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(1F, 0.75F, 1F);
	}
}