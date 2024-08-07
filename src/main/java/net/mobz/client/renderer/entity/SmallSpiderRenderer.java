package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.SmallSpider;

public class SmallSpiderRenderer extends SpiderRenderer<SmallSpider> {
    private final ResourceLocation texture;

    public SmallSpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.shadowRadius *= 0.7F;
        this.addLayer(new SpiderEyesLayer<>(this));
        this.texture = texture;
    }

    @Override
    protected void scale(SmallSpider caveSpiderEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SmallSpider SpiSmalll) {
        return this.texture;
    }
}
