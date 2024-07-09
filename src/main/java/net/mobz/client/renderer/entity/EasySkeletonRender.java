package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;

public class EasySkeletonRender<T extends Skeleton> extends HumanoidMobRenderer<T, SkeletonModel<T>> {
	private final ResourceLocation texture;

    public EasySkeletonRender(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T skeleton) {
        return this.texture;
    }
}