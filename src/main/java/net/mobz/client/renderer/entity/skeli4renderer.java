package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.skeli4;

public class skeli4renderer extends BipedRenderer<skeli4, SkeletonModel<skeli4>> {
    public skeli4renderer(EntityRendererManager dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli4 skeliiiii) {
        return new ResourceLocation("mobz:textures/entity/skeli4.png");
    }
}