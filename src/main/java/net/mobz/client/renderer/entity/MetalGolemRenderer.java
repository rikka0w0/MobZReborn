package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.IronGolemCracksLayer;
import net.minecraft.client.renderer.entity.layers.IronGolenFlowerLayer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class MetalGolemRenderer extends MobRenderer<IronGolemEntity, IronGolemModel<IronGolemEntity>> {
	private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/metalgolem.png");

	public MetalGolemRenderer(EntityRendererManager entityRenderDispatcher) {
		super(entityRenderDispatcher, new IronGolemModel<>(), 0.7F);
		this.addLayer(new IronGolemCracksLayer(this));
		this.addLayer(new IronGolenFlowerLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(IronGolemEntity lava) {
		return SKIN;
	}

	@Override
	protected void scale(IronGolemEntity golem, MatrixStack matrixStack, float f) {
		matrixStack.scale(1.15F, 1.15F, 1.15F);
	}

	@Override
	protected void setupRotations(IronGolemEntity ironGolemEntity, MatrixStack matrixStack, float f, float g, float h) {
		super.setupRotations(ironGolemEntity, matrixStack, f, g, h);
		if ((double) ironGolemEntity.animationSpeed >= 0.01D) {
			float j = ironGolemEntity.animationPosition - ironGolemEntity.animationSpeed * (1.0F - h) + 6.0F;
			float k = (Math.abs(j % 13.0F - 6.5F) - 3.25F) / 3.25F;
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * k));
		}
	}
}
