package net.mobz.client.renderer.features;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class EnderEyes<T extends LivingEntity> extends AbstractEyesLayer<T, EndermanModel<T>> {
	private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("mobz:textures/entity/endereyes.png"));

	public EnderEyes(IEntityRenderer<T, EndermanModel<T>> context) {
		super(context);
	}

	public RenderType renderType() {
		return SKIN;
	}
}
