package net.mobz.client.renderer.entity;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public class EasySpiderRenderer<T extends Spider> extends SpiderRenderer<T> {
	private final ResourceLocation texture;

	public EasySpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		this(context, texture, false);
	}

	public EasySpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture, boolean useVanillaEyes) {
		super(context);

		this.texture = texture;
		if (!useVanillaEyes) {
			this.layers.removeIf(layer -> layer instanceof SpiderEyesLayer);
			String eyePath = texture.getPath().replace(".png", "_eyes.png");
			ResourceLocation eyeTexture = ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), eyePath);
			this.addLayer(new SpiderEyes<>(this, eyeTexture));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {
		return this.texture;
	}

	public static class SpiderEyes<M extends SpiderModel> extends SpiderEyesLayer<M> {
		private final RenderType texture;

		public SpiderEyes(RenderLayerParent<LivingEntityRenderState, M> context, ResourceLocation eyeTexture) {
			super(context);
			this.texture = RenderType.eyes(eyeTexture);
		}

		@Override
		public RenderType renderType() {
			return texture;
		}
	}
}