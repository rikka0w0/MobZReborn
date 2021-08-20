package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.MageEntity;

public class MageRenderer extends EvokerRenderer<MageEntity> {
    public MageRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(MageEntity mageentity) {
        return new ResourceLocation("mobz:textures/entity/mageentity.png");
    }
}