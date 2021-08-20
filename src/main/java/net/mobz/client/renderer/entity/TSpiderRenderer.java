package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.TSpider;

public class TSpiderRenderer extends SpiderRenderer<TSpider> {
    public TSpiderRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new SpiderEyesLayer<>(this));
    }

    @Override
    protected void scale(TSpider caveSpiderEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.1F, 0.1F, 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(TSpider TSpiderl) {
        return new ResourceLocation("mobz:textures/entity/tspider.png");
    }
}
