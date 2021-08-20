package net.mobz.client.renderer.features;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.resources.ResourceLocation;

public class GolemCrack extends RenderLayer<IronGolem, IronGolemModel<IronGolem>> {
	private static final Map<IronGolem.Crackiness, ResourceLocation> DAMAGE_TO_TEXTURE;

	public GolemCrack(RenderLayerParent<IronGolem, IronGolemModel<IronGolem>> featureRendererContext) {
		super(featureRendererContext);
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i,
			IronGolem ironGolemEntity, float f, float g, float h, float j, float k, float l) {
		if (!ironGolemEntity.isInvisible()) {
			IronGolem.Crackiness crack = ironGolemEntity.getCrackiness();
			if (crack != IronGolem.Crackiness.NONE) {
				ResourceLocation identifier = (ResourceLocation) DAMAGE_TO_TEXTURE.get(crack);
				renderColoredCutoutModel(this.getParentModel(), identifier, matrixStack, vertexConsumerProvider, i,
						ironGolemEntity, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	static {
		DAMAGE_TO_TEXTURE = ImmutableMap.of(IronGolem.Crackiness.LOW,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_low.png"),
				IronGolem.Crackiness.MEDIUM,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_medium.png"),
				IronGolem.Crackiness.HIGH,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_high.png"));
	}
}
