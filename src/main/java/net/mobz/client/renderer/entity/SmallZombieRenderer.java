package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.SmallZombie;

public class SmallZombieRenderer extends EasyZombieRenderer<SmallZombie> {
    public SmallZombieRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture);
    }

    @Override
    protected void scale(SmallZombie smallZombie, PoseStack matrixStack, float f) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }
}
