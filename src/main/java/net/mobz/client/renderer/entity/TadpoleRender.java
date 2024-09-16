package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.MobZ;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.entity.TadpoleEntity;

public class TadpoleRender extends MobRenderer<TadpoleEntity, TadpoleEntityModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(MobZ.MODID, "textures/entity/tadpole.png");

	public TadpoleRender(EntityRendererProvider.Context context) {
		super(context, new TadpoleEntityModel(context.bakeLayer(TadpoleEntityModel.modelResLoc)), 0.15F);
	}

	@Override
	protected void setupRotations(TadpoleEntity tadpole, PoseStack matrixStack, float f, float g, float h, float i) {
		super.setupRotations(tadpole, matrixStack, f, g, h, i);
		float yRotate = 4.3F * Mth.sin(0.6F * f);
		matrixStack.mulPose(Axis.YP.rotationDegrees(yRotate));
		if (!tadpole.isInWater()) {
			matrixStack.translate(0.10000000149011612D, 0.10000000149011612D, -0.10000000149011612D);
			matrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(TadpoleEntity entity) {
		return TEXTURE;
	}
}
