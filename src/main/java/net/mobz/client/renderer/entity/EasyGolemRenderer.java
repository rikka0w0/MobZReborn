package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.IronGolemCrackinessLayer;
import net.minecraft.client.renderer.entity.layers.IronGolemFlowerLayer;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Axis;

public class EasyGolemRenderer extends MobRenderer<IronGolem, IronGolemModel<IronGolem>> {
	private final ResourceLocation texture;

	public EasyGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		this(context, texture, true);
	}

	protected EasyGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture, boolean useDefaultCrack) {
		super(context, new IronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
		if (useDefaultCrack) {
			this.addLayer(new IronGolemCrackinessLayer(this));
		}
		this.addLayer(new IronGolemFlowerLayer(this, context.getBlockRenderDispatcher()));
		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(IronGolem stone) {
		return this.texture;
	}

	@Override
	protected void setupRotations(IronGolem ironGolemEntity, PoseStack matrixStack, float f, float g, float h) {
		super.setupRotations(ironGolemEntity, matrixStack, f, g, h);
		if (ironGolemEntity.walkAnimation.speed() >= 0.01D) {
			float j = ironGolemEntity.walkAnimation.position() - ironGolemEntity.walkAnimation.speed() * (1.0F - h) + 6.0F;
			float k = (Math.abs(j % 13.0F - 6.5F) - 3.25F) / 3.25F;
			matrixStack.mulPose(Axis.ZP.rotationDegrees(6.5F * k));
		}
	}
}
