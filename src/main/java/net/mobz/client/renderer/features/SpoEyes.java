package net.mobz.client.renderer.features;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

public class SpoEyes<T extends Entity, M extends SpiderModel<T>> extends EyesLayer<T, M> {
	private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("mobz:textures/entity/spoeyes.png"));

	public SpoEyes(RenderLayerParent<T, M> context) {
		super(context);
	}

	public RenderType renderType() {
		return SKIN;
	}
}
