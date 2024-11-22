package net.mobz.client.renderer.entity;

import net.minecraft.client.model.BeeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BeeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Wasp;

public class WaspRender extends AgeableMobRenderer<Wasp, BeeRenderState, BeeModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace(
			"textures/entity/bee/bee_angry.png");

	public WaspRender(EntityRendererProvider.Context context) {
		super(context,
				new BeeModel(context.bakeLayer(ModelLayers.BEE)),
				new BeeModel(context.bakeLayer(ModelLayers.BEE_BABY)),
				0.4F);
	}

	@Override
	public BeeRenderState createRenderState() {
		return new BeeRenderState();
	}

	@Override
	public void extractRenderState(Wasp p_362651_, BeeRenderState p_362934_, float p_366251_) {
		super.extractRenderState(p_362651_, p_362934_, p_366251_);
		p_362934_.rollAmount = p_362651_.getRollAmount(p_366251_);
		p_362934_.hasStinger = true;
		p_362934_.isOnGround = p_362651_.onGround() && p_362651_.getDeltaMovement().lengthSqr() < 1.0E-7;
		p_362934_.isAngry = true;
		p_362934_.hasNectar = false;
	}

	@Override
	public ResourceLocation getTextureLocation(BeeRenderState renderState) {
		return TEXTURE;
	}
}
