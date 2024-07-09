package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.TSpider;

public class TSpiderRenderer extends MobRenderer<TSpider, SpiderModel<TSpider>> {
    private final ResourceLocation texture;

	public TSpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.1F);
		this.addLayer(new SpiderEyesLayer<>(this));
		this.texture = texture;
	}

	@Override
	protected void scale(TSpider caveSpiderEntity, PoseStack matrixStack, float f) {
		matrixStack.scale(0.1F, 0.1F, 0.1F);
	}

	@Override
	protected float getFlipDegrees(TSpider p_116011_) {
		return 180.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(TSpider TSpiderl) {
		return this.texture;
	}
}
