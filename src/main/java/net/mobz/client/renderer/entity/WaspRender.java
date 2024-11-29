package net.mobz.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mobz.client.renderer.entity.state.WaspRenderState;
import net.mobz.client.renderer.model.WaspModel;
import net.mobz.entity.Wasp;

public class WaspRender extends AgeableMobRenderer<Wasp, WaspRenderState, WaspModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace(
			"textures/entity/bee/bee_angry.png");

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
	public void extractRenderState(Wasp p_362651_, WaspRenderState p_362934_, float p_366251_) {
		super.extractRenderState(p_362651_, p_362934_, p_366251_);
		p_362934_.rollAmount = p_362651_.getRollAmount(p_366251_);
		p_362934_.hasStinger = true;
		p_362934_.isOnGround = p_362651_.onGround() && p_362651_.getDeltaMovement().lengthSqr() < 1.0E-7;
		p_362934_.isAngry = true;
		p_362934_.hasNectar = false;
	}

	@Override
	public ResourceLocation getTextureLocation(WaspRenderState renderState) {
		return TEXTURE;
	}
}
