package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.resources.ResourceLocation;

public class IllusionerRenderer extends EvokerRenderer<Illusioner> {
    public IllusionerRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(Illusioner illusioner) {
        return new ResourceLocation("mobz:textures/entity/illusioner.png");
    }
}