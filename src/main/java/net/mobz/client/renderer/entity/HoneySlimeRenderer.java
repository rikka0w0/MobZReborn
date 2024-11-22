package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.entity.HoneySlime;

public class HoneySlimeRenderer extends MobRenderer<HoneySlime, SlimeRenderState, SlimeModel> {
	private final ResourceLocation texture;

	public HoneySlimeRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, new SlimeModel(context.bakeLayer(ModelLayers.SLIME)), 0.25F);
		this.addLayer(new SlimeOuterLayer(this, context.getModelSet()));
		this.texture = texture;
	}

	@Override
	public void render(SlimeRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSrc, int packedLight) {
		this.shadowRadius = 0.25F * (float) renderState.size;
		super.render(renderState, poseStack, bufferSrc, packedLight);
	}

	@Override
	protected void scale(SlimeRenderState renderState, PoseStack poseStack) {
		poseStack.scale(0.999F, 0.999F, 0.999F);
		poseStack.translate(0.0F, 0.001F, 0.0F);
		float h = (float) renderState.size;
		float i = renderState.squish / (h * 0.5F + 1.0F);
		float j = 1.0F / (i + 1.0F);
		poseStack.scale(j * h, 1.0F / j * h, j * h);
	}

	@Override
	public ResourceLocation getTextureLocation(SlimeRenderState renderState) {
		return this.texture;
	}

	@Override
	public SlimeRenderState createRenderState() {
		return new SlimeRenderState();
	}

	@Override
	public void extractRenderState(HoneySlime slime, SlimeRenderState renderState, float partialTick) {
		super.extractRenderState(slime, renderState, partialTick);
		renderState.squish = Mth.lerp(partialTick, slime.oSquish, slime.squish);
		renderState.size = slime.getSize();
	}

	public class SlimeOuterLayer extends RenderLayer<SlimeRenderState, SlimeModel> {
		private final SlimeModel model;

		public SlimeOuterLayer(RenderLayerParent<SlimeRenderState, SlimeModel> renderer, EntityModelSet modelSet) {
			super(renderer);
			this.model = new SlimeModel(modelSet.bakeLayer(ModelLayers.SLIME_OUTER));
		}

		public void render(PoseStack poseStack, MultiBufferSource bufferSrc, int packedLight,
				SlimeRenderState renderState, float yRot, float xRot) {
			boolean flag = renderState.appearsGlowing && renderState.isInvisible;
			if (!renderState.isInvisible || flag) {
				VertexConsumer vertexConsumer;
				if (flag) {
					vertexConsumer = bufferSrc.getBuffer(RenderType.outline(HoneySlimeRenderer.this.texture));
				} else {
					vertexConsumer = bufferSrc.getBuffer(RenderType.entityTranslucent(HoneySlimeRenderer.this.texture));
				}

				this.model.setupAnim(renderState);
				this.model.renderToBuffer(poseStack, vertexConsumer, packedLight,
						LivingEntityRenderer.getOverlayCoords(renderState, 0.0F));
			}
		}
	}
}
