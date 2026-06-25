package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

public class EasyHumanoidRenderer<T extends Mob> extends HumanoidMobRenderer<T, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {
	private final Identifier texture;

	public EasyHumanoidRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(
				this,
				ArmorModelSet.bake(ModelLayers.PLAYER_ARMOR, context.getModelSet(),
						modelPart -> new HumanoidModel<HumanoidRenderState>(modelPart)),
				context.getEquipmentRenderer()));
		this.texture = texture;
	}

	@Override
	public Identifier getTextureLocation(HumanoidRenderState renderState) {
		return this.texture;
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	protected void scale(HumanoidRenderState renderState, PoseStack matrixStack) {
		if (renderState.isBaby)
			matrixStack.scale(0.5F, 0.5F, 0.5F);
	}
}
