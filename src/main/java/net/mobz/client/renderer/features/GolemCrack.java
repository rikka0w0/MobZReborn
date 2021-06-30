package net.mobz.client.renderer.features;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;

public class GolemCrack extends LayerRenderer<IronGolemEntity, IronGolemModel<IronGolemEntity>> {
	private static final Map<IronGolemEntity.Cracks, ResourceLocation> DAMAGE_TO_TEXTURE;

	public GolemCrack(IEntityRenderer<IronGolemEntity, IronGolemModel<IronGolemEntity>> featureRendererContext) {
		super(featureRendererContext);
	}

	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i,
			IronGolemEntity ironGolemEntity, float f, float g, float h, float j, float k, float l) {
		if (!ironGolemEntity.isInvisible()) {
			IronGolemEntity.Cracks crack = ironGolemEntity.getCrackiness();
			if (crack != IronGolemEntity.Cracks.NONE) {
				ResourceLocation identifier = (ResourceLocation) DAMAGE_TO_TEXTURE.get(crack);
				renderColoredCutoutModel(this.getParentModel(), identifier, matrixStack, vertexConsumerProvider, i,
						ironGolemEntity, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	static {
		DAMAGE_TO_TEXTURE = ImmutableMap.of(IronGolemEntity.Cracks.LOW,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_low.png"),
				IronGolemEntity.Cracks.MEDIUM,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_medium.png"),
				IronGolemEntity.Cracks.HIGH,
				new ResourceLocation("mobz:textures/entity/metalgolem_crackiness_high.png"));
	}
}
