package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.skeli4;

public class skeli4renderer extends HumanoidMobRenderer<skeli4, SkeletonModel<skeli4>> {
    public skeli4renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli4 skeliiiii) {
        return new ResourceLocation("mobz:textures/entity/skeli4.png");
    }
}