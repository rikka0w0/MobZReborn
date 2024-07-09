package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.DwarfEntity;

public class DwarfEntityRenderer extends EasyHumanoidRenderer<DwarfEntity> {
    public DwarfEntityRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture);
    }

    @Override
    protected void scale(DwarfEntity dwarf, PoseStack matrixStack, float f) {
        matrixStack.scale(1F, 0.75F, 1F);
    }
}