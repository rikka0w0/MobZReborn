package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.entity.monster.IllusionerEntity;
import net.minecraft.util.ResourceLocation;

public class IllusionerRenderer extends EvokerRenderer<IllusionerEntity> {
    public IllusionerRenderer(EntityRendererManager dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(IllusionerEntity illusioner) {
        return new ResourceLocation("mobz:textures/entity/illusioner.png");
    }
}