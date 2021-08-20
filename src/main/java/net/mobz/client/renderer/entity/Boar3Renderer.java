package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PigModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Boar3;

public class Boar3Renderer extends MobRenderer<Boar3, PigModel<Boar3>> {
    public Boar3Renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PigModel<>(), 0.7F);

    }

    @Override
    public ResourceLocation getTextureLocation(Boar3 boar3) {
        return new ResourceLocation("mobz:textures/entity/boar3.png");
    }

}