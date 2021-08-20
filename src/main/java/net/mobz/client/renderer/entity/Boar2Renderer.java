package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PigModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Boar2;

public class Boar2Renderer extends MobRenderer<Boar2, PigModel<Boar2>> {
    public Boar2Renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PigModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boar2 boared) {
        return new ResourceLocation("mobz:textures/entity/badboar.png");
    }
}