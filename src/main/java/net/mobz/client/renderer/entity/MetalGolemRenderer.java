package net.mobz.client.renderer.entity;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Crackiness;

import net.mobz.MobZ;

public class MetalGolemRenderer extends EasyGolemRenderer {
	private static final Map<Crackiness.Level, ResourceLocation> DAMAGE_TO_TEXTURE = ImmutableMap.of(
			Crackiness.Level.LOW,
			MobZ.resLoc("textures/entity/metal_golem_crackiness_low.png"),
			Crackiness.Level.MEDIUM,
			MobZ.resLoc("textures/entity/metal_golem_crackiness_medium.png"),
			Crackiness.Level.HIGH,
			MobZ.resLoc("textures/entity/metal_golem_crackiness_high.png"));

	public MetalGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
		super(context, texture, false);
	}

	@Override
	protected void scale(IronGolemRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(1.15F, 1.15F, 1.15F);
	}

	@Override
	public ResourceLocation getTextureLocation(IronGolemRenderState renderState) {
		return renderState.crackiness == Crackiness.Level.NONE ? super.getTextureLocation(renderState)
				: DAMAGE_TO_TEXTURE.get(renderState.crackiness);
	}
}
