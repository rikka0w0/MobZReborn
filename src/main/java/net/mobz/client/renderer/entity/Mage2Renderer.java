package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Mage2Entity;

public class Mage2Renderer extends EvokerRenderer<Mage2Entity> {
    public Mage2Renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(Mage2Entity mageent) {
        return new ResourceLocation("mobz:textures/entity/mage2entity.png");
    }
}