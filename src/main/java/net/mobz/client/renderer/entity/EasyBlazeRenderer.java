package net.mobz.client.renderer.entity;

import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import net.mobz.entity.BlazeLike;

public class EasyBlazeRenderer extends MobRenderer<BlazeLike, LivingEntityRenderState, BlazeModel> {
	private final ResourceLocation texture;

	public EasyBlazeRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, new BlazeModel(context.bakeLayer(ModelLayers.BLAZE)), 0.5F);
		this.texture = texture;
	}

	@Override
	protected int getBlockLightLevel(BlazeLike entity, BlockPos pos) {
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {
		return this.texture;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
