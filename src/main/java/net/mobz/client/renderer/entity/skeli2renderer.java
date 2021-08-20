package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.skeli2;

public class skeli2renderer extends HumanoidMobRenderer<skeli2, SkeletonModel<skeli2>> {

    public skeli2renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli2 skeliii) {
        return new ResourceLocation("mobz:textures/entity/skeli2.png");
    }
}