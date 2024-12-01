package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import net.mobz.MobZ;
import net.mobz.client.renderer.entity.state.WaspRenderState;
import net.mobz.client.renderer.model.WaspModel;
import net.mobz.entity.Wasp;

public class WaspRender extends AgeableMobRenderer<Wasp, WaspRenderState, WaspModel> {
	private static final ResourceLocation TEXTURE = MobZ.resLoc("textures/entity/wasp.png");

	public WaspRender(EntityRendererProvider.Context context) {
		super(context,
				new WaspModel(context.bakeLayer(WaspModel.MODEL_LAYER_LOC)),
				new WaspModel(context.bakeLayer(WaspModel.MODEL_LAYER_LOC)),
				0.4F);
	}

	@Override
	public WaspRenderState createRenderState() {
		return new WaspRenderState();
	}

	@Override
	public void extractRenderState(Wasp entity, WaspRenderState renderState, float p_366251_) {
		super.extractRenderState(entity, renderState, p_366251_);
		renderState.rollAmount = entity.getRollAmount(p_366251_);
		renderState.hasStinger = true;
		renderState.isOnGround = entity.onGround() && entity.getDeltaMovement().lengthSqr() < 1.0E-7;
		renderState.isAngry = true;
		renderState.hasNectar = false;

		renderState.flyAnimationState.copyFrom(entity.flyAnimationState);
		renderState.restAnimationState.copyFrom(entity.restAnimationState);
	}

	@Override
	public ResourceLocation getTextureLocation(WaspRenderState renderState) {
		return TEXTURE;
	}
}
