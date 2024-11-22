package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class BossSkeletonRenderer extends EasySkeletonRender {
	public BossSkeletonRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, texture);

		String overlayPath = texture.getPath().replace(".png", "_overlay.png");
		ResourceLocation overlayTexture = ResourceLocation.tryBuild(texture.getNamespace(), overlayPath);
		this.addLayer(new OverlayFeature<>(this, context.getModelSet(), overlayTexture));
	}

	public static class OverlayFeature<T extends Mob & RangedAttackMob, S extends SkeletonRenderState, M extends SkeletonModel<S>>
			extends RenderLayer<S, M> {
		private final ResourceLocation texture;
		private final SkeletonModel<S> model;

		public OverlayFeature(RenderLayerParent<S, M> featureRendererContext, EntityModelSet entityModelSet,
				ResourceLocation overlayTexture) {
			super(featureRendererContext);
			this.model = new SkeletonModel<>(entityModelSet.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
			this.texture = overlayTexture;
		}

		@Override
		public void render(PoseStack poseStack, MultiBufferSource bufferSrc, int packedLight, S renderState,
				float headYaw, float headPitch) {
			coloredCutoutModelCopyLayerRender(this.model, this.texture, poseStack, bufferSrc, packedLight, renderState, -1);
		}
	}
}