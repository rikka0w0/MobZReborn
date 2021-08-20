package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.client.renderer.features.SpoEyes;
import net.mobz.entity.SpoEntity;

public class SpoRenderer extends SpiderRenderer<SpoEntity> {
    public SpoRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
        this.addLayer(new SpoEyes<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SpoEntity spoEntity) {
        return new ResourceLocation("mobz:textures/entity/spo.png");
    }
}