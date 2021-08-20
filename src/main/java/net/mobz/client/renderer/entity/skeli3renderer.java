package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.skeli3;

public class skeli3renderer extends HumanoidMobRenderer<skeli3, SkeletonModel<skeli3>> {
    public skeli3renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli3 skeliiii) {
        return new ResourceLocation("mobz:textures/entity/skeli3.png");
    }
}