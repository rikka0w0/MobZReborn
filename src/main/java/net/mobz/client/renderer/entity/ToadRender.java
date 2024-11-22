package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.mobz.MathUtils;
import net.mobz.MobZ;
import net.mobz.client.renderer.entity.state.ToadRenderState;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.entity.ToadEntity;

public class ToadRender extends MobRenderer<ToadEntity, ToadRenderState, ToadEntityModel> {
	private static final ResourceLocation TEXTURE = MobZ.resLoc("textures/entity/toad.png");

	public static class Giant extends ToadRender {
		public Giant(Context context) {
			super(context, 4.0F);
		}

		@Override
		protected void scale(ToadRenderState renderState, PoseStack poseStack) {
			poseStack.scale(4.0F, 4.0F, 4.0F);
		}
	}

	public static class Normal extends ToadRender {
		public Normal(EntityRendererProvider.Context context) {
			super(context, 1.0F);
		}
	}

	private ToadRender(EntityRendererProvider.Context context, float bodyScale) {
		super(context, new ToadEntityModel(context.bakeLayer(ToadEntityModel.MODEL_LAYER_LOC), bodyScale), 0.25F);
	}

	@Override
	public ToadRenderState createRenderState() {
		return new ToadRenderState();
	}

	@Override
	public void extractRenderState(ToadEntity toad, ToadRenderState renderState, float partialTick) {
		super.extractRenderState(toad, renderState, partialTick);

		renderState.onGround = toad.onGround();
		if(toad.hasTongueEntity()) {
			renderState.mouthDistance = MathUtils.approachValue(renderState.mouthDistance, 1, 0.5F);
		} else {
			renderState.mouthDistance = MathUtils.approachValue(renderState.mouthDistance, 0, 0.10F);
		}
		renderState.tongueDistance = toad.tongueDistance;
		renderState.targetTongueDistance = toad.targetTongueDistance;
	}

	@Override
	public ResourceLocation getTextureLocation(ToadRenderState renderState) {
		return TEXTURE;
	}
}
