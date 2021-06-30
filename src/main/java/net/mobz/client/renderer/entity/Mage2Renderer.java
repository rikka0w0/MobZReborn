package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Mage2Entity;

public class Mage2Renderer extends EvokerRenderer<Mage2Entity> {
    public Mage2Renderer(EntityRendererManager dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(Mage2Entity mageent) {
        return new ResourceLocation("mobz:textures/entity/mage2entity.png");
    }
}