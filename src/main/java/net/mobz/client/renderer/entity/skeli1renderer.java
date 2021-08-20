package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.client.renderer.features.OverlayFeature;
import net.mobz.entity.skeli1;

public class skeli1renderer extends HumanoidMobRenderer<skeli1, SkeletonModel<skeli1>> {
    public skeli1renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
        this.addLayer(new OverlayFeature<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(skeli1 skeliiiiii) {
        return new ResourceLocation("mobz:textures/entity/skeli1.png");
    }
}