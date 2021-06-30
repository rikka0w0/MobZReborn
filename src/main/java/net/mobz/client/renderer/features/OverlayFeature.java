package net.mobz.client.renderer.features;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class OverlayFeature<T extends MobEntity & IRangedAttackMob, M extends EntityModel<T>>
		extends LayerRenderer<T, M> {
	private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/skeli1_overlay.png");
	private final SkeletonModel<T> model = new SkeletonModel<>(0.25F, true);

	public OverlayFeature(IEntityRenderer<T, M> featureRendererContext) {
		super(featureRendererContext);
	}

	public void render(MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, T mobEntity, float f,
			float g, float h, float j, float k, float l) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrixStack, vertexConsumerProvider,
				i, mobEntity, f, g, j, k, l, h, 1.0F, 1.0F, 1.0F);
	}
}
