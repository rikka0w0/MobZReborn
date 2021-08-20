package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.resources.ResourceLocation;

public class BabyravagerRenderer extends MobRenderer<Ravager, RavagerModel> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/ravo.png");

    public BabyravagerRenderer(EntityRendererProvider.Context context) {
        super(context, new RavagerModel(context.bakeLayer(ModelLayers.RAVAGER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Ravager ravagerEntity) {
        return SKIN;
    }

    @Override
    protected void scale(Ravager vexEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.4F, 0.4F, 0.4F);
    }
}
