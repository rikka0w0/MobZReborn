package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.skeli3;

public class skeli3renderer extends BipedRenderer<skeli3, SkeletonModel<skeli3>> {
    public skeli3renderer(EntityRendererManager dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli3 skeliiii) {
        return new ResourceLocation("mobz:textures/entity/skeli3.png");
    }
}