package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.MobZ;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.entity.ToadEntity;

public class ToadRender extends MobRenderer<ToadEntity, ToadEntityModel>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(MobZ.MODID, "textures/entity/toad.png");

	public ToadRender(EntityRendererProvider.Context context) {
		super(context, new ToadEntityModel(context.bakeLayer(ToadEntityModel.modelResLoc)), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(ToadEntity entity) {
		return TEXTURE;
	}
}
