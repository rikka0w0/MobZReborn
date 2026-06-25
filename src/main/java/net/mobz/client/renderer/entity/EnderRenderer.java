package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.resources.Identifier;

public class EnderRenderer extends EndermanRenderer {
	private final Identifier texture;

	public EnderRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.addLayer(new CarriedBlockLayer(this));

		this.texture = texture;
		String eyePath = texture.getPath().replace(".png", "_eyes.png");
		Identifier eyeTexture = Identifier.fromNamespaceAndPath(texture.getNamespace(), eyePath);
		this.addLayer(new EnderEyes(this, eyeTexture));
	}

	@Override
	public Identifier getTextureLocation(EndermanRenderState renderState) {
		return this.texture;
	}

	public static class EnderEyes extends EyesLayer<EndermanRenderState, EndermanModel<EndermanRenderState>> {
		private final RenderType texture;

		public EnderEyes(RenderLayerParent<EndermanRenderState, EndermanModel<EndermanRenderState>> context, Identifier eyeTexture) {
			super(context);
			this.texture = RenderTypes.eyes(eyeTexture);
		}

		@Override
		public RenderType renderType() {
			return this.texture;
		}
	}
}
