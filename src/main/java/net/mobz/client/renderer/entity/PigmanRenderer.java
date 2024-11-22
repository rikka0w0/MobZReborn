package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class PigmanRenderer extends PiglinRenderer {
	private final ResourceLocation texture;
	public static final CustomHeadLayer.Transforms CUSTOM_HEAD_TRANSFORMS =
			new CustomHeadLayer.Transforms(1.0019531F, 1.0F, 1.0019531F);

	public PigmanRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context,
				ModelLayers.PIGLIN,
				ModelLayers.PIGLIN_BABY,
				ModelLayers.PIGLIN_INNER_ARMOR,
				ModelLayers.PIGLIN_OUTER_ARMOR,
				ModelLayers.PIGLIN_BABY_INNER_ARMOR,
				ModelLayers.PIGLIN_BABY_OUTER_ARMOR
		);

		this.layers.removeIf(layer -> layer instanceof CustomHeadLayer);
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), CUSTOM_HEAD_TRANSFORMS, context.getItemRenderer()));
//		super(context, new PiglinModel<>(context.bakeLayer(ModelLayers.PIGLIN)), 0.5F, 1.0019531F, 1.0F, 1.0019531F);

		this.texture = texture;
	}

	@Override
	public ResourceLocation getTextureLocation(PiglinRenderState renderState) {
		return this.texture;
	}
}
