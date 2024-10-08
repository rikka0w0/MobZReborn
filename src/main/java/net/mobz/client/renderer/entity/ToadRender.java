package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.MobZ;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.entity.ToadEntity;

public class ToadRender extends MobRenderer<ToadEntity, ToadEntityModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(
			MobZ.MODID, "textures/entity/toad.png");

	public static class Giant extends ToadRender {
		public Giant(Context context) {
			super(context, 4.0F);
		}

		@Override
		protected void scale(ToadEntity te, PoseStack poseStack, float f) {
			poseStack.scale(4.0F, 4.0F, 4.0F);
		}
	}

	public static class Normal extends ToadRender {
		public Normal(EntityRendererProvider.Context context) {
			super(context, 1.0F);
		}
	}

	private ToadRender(EntityRendererProvider.Context context, float bodyScale) {
		super(context, new ToadEntityModel(context.bakeLayer(ToadEntityModel.modelResLoc), bodyScale), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(ToadEntity entity) {
		return TEXTURE;
	}
}
