package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.client.renderer.features.SpiEyes;
import net.mobz.entity.SpiEntity;

public class SpiRenderer extends SpiderRenderer<SpiEntity> {
    public SpiRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new SpiEyes<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SpiEntity spiEntity) {
        return new ResourceLocation("mobz:textures/entity/spi.png");
    }
}