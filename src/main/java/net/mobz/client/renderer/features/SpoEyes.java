package net.mobz.client.renderer.features;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SpoEyes<T extends Entity, M extends SpiderModel<T>> extends AbstractEyesLayer<T, M> {
	private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("mobz:textures/entity/spoeyes.png"));

	public SpoEyes(IEntityRenderer<T, M> context) {
		super(context);
	}

	public RenderType renderType() {
		return SKIN;
	}
}
