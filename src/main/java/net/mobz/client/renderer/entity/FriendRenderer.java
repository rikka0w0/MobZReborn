package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.FriendEntity;

public abstract class FriendRenderer<T extends FriendEntity> extends HumanoidMobRenderer<T, PlayerModel<T>> {
	public FriendRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
	}

	public abstract ResourceLocation getTextureLocation(T entity);

	public static class KatherineRenderer extends FriendRenderer<FriendEntity.KatherineEntity> {
		public KatherineRenderer(EntityRendererProvider.Context context) {
			super(context);
		}

		@Override
		public ResourceLocation getTextureLocation(FriendEntity.KatherineEntity entity) {
			return new ResourceLocation("mobz:textures/entity/katherine.png");
		}
	}

	public static class FioraRenderer extends FriendRenderer<FriendEntity.FioraEntity> {
		public FioraRenderer(EntityRendererProvider.Context context) {
			super(context);
		}

		@Override
		public ResourceLocation getTextureLocation(FriendEntity.FioraEntity entity) {
			return new ResourceLocation("mobz:textures/entity/fiora.png");
		}
	}
}
