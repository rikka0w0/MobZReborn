package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.SmallSpider;

public class SmallSpiderRenderer extends EasySpiderRenderer<SmallSpider> {
    public SmallSpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture, true);
        this.shadowRadius *= 0.7F;
    }

    @Override
    protected void scale(LivingEntityRenderState renderState, PoseStack matrixStack) {
        matrixStack.scale(0.7F, 0.7F, 0.7F);
    }
}
