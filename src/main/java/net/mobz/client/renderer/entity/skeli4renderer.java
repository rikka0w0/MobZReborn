package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.skeli4;

public class skeli4renderer extends HumanoidMobRenderer<skeli4, SkeletonModel<skeli4>> {
    public skeli4renderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli4 skeliiiii) {
        return new ResourceLocation("mobz:textures/entity/skeli4.png");
    }
}