package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.TankEntity;

public class TankRenderer extends HumanoidMobRenderer<TankEntity, ZombieModel<TankEntity>> {
	private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/tank.png");

	public TankRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new ZombieModel<>(0.0F, false), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true)));
	}

	@Override
	public ResourceLocation getTextureLocation(TankEntity Tanky) {
		return SKIN;
	}
}
