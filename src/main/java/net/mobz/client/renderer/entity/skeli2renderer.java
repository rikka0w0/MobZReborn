package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.skeli2;

public class skeli2renderer extends BipedRenderer<skeli2, SkeletonModel<skeli2>> {

    public skeli2renderer(EntityRendererManager dispatcher) {
        super(dispatcher, new SkeletonModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(skeli2 skeliii) {
        return new ResourceLocation("mobz:textures/entity/skeli2.png");
    }
}