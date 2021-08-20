package net.mobz.client.renderer.features;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;

public class EnderEyes<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
	private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("mobz:textures/entity/endereyes.png"));

	public EnderEyes(RenderLayerParent<T, EndermanModel<T>> context) {
		super(context);
	}

	public RenderType renderType() {
		return SKIN;
	}
}
