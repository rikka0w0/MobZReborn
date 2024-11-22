package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.resources.ResourceLocation;

public class IceGolemRenderer extends EasyGolemRenderer {
    public IceGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture);
    }

    @Override
    protected void scale(IronGolemRenderState renderState, PoseStack matrixStack) {
        matrixStack.scale(1.1F, 1.1F, 1.1F);
    }
}
