package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.resources.ResourceLocation;

public class EnderRenderer extends EndermanRenderer {
	private final ResourceLocation texture;

	public EnderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context);
		this.addLayer(new CarriedBlockLayer(this, context.getBlockRenderDispatcher()));

		this.texture = texture;
		String eyePath = texture.getPath().replace(".png", "_eyes.png");
		ResourceLocation eyeTexture = ResourceLocation.tryBuild(texture.getNamespace(), eyePath);
		this.addLayer(new EnderEyes(this, eyeTexture));
	}

	@Override
	public ResourceLocation getTextureLocation(EndermanRenderState renderState) {
		return this.texture;
	}

	public static class EnderEyes extends EyesLayer<EndermanRenderState, EndermanModel<EndermanRenderState>> {
		private final RenderType texture;

		public EnderEyes(RenderLayerParent<EndermanRenderState, EndermanModel<EndermanRenderState>> context, ResourceLocation eyeTexture) {
			super(context);
			this.texture = RenderType.eyes(eyeTexture);
		}

		@Override
		public RenderType renderType() {
			return this.texture;
		}
	}
}
