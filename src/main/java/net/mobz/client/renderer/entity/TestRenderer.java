package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.VexModel;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.resources.ResourceLocation;

public class TestRenderer extends HumanoidMobRenderer<Vex, VexModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/test.png");
	private static final ResourceLocation CHARGING_TEXTURE = new ResourceLocation("mobz:textures/entity/test.png");

	public TestRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new VexModel(), 0.3F);
	}

	@Override
	public ResourceLocation getTextureLocation(Vex vexEntity) {
		return vexEntity.isCharging() ? CHARGING_TEXTURE : TEXTURE;
	}

	@Override
	protected void scale(Vex vexEntity, PoseStack matrixStack, float f) {
		matrixStack.scale(0.4F, 0.4F, 0.4F);
	}
}
