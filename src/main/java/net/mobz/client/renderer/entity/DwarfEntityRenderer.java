package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.mobz.entity.Dwarf;

public class DwarfEntityRenderer extends EasyHumanoidRenderer<Dwarf> {
	public DwarfEntityRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context, texture);
	}

	@Override
	protected void scale(HumanoidRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(1F, 0.75F, 1F);
	}
}
