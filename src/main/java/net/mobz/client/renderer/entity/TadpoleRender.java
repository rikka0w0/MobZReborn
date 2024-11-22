package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.MobZ;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.entity.TadpoleEntity;

public class TadpoleRender extends MobRenderer<TadpoleEntity, LivingEntityRenderState, TadpoleEntityModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(MobZ.MODID, "textures/entity/tadpole.png");

	public TadpoleRender(EntityRendererProvider.Context context) {
		super(context, new TadpoleEntityModel(context.bakeLayer(TadpoleEntityModel.MODEL_LAYER_LOC)), 0.15F);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	protected void setupRotations(LivingEntityRenderState renderState, PoseStack matrixStack, float f, float g) {
		super.setupRotations(renderState, matrixStack, f, g);
		float yRotate = 4.3F * Mth.sin(0.6F * f);
		matrixStack.mulPose(Axis.YP.rotationDegrees(yRotate));
		if (!renderState.isInWater) {
			matrixStack.translate(0.10000000149011612D, 0.10000000149011612D, -0.10000000149011612D);
			matrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {
		return TEXTURE;
	}
}
