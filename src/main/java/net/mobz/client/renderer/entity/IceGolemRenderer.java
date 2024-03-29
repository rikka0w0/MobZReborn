package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.IronGolemCrackinessLayer;
import net.minecraft.client.renderer.entity.layers.IronGolemFlowerLayer;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Axis;

public class IceGolemRenderer extends MobRenderer<IronGolem, IronGolemModel<IronGolem>> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/icegolem.png");

    public IceGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new IronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
        this.addLayer(new IronGolemCrackinessLayer(this));
        this.addLayer(new IronGolemFlowerLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(IronGolem ice) {
        return SKIN;
    }

    @Override
    protected void scale(IronGolem golem, PoseStack matrixStack, float f) {
        matrixStack.scale(1.1F, 1.1F, 1.1F);
    }

    @Override
    protected void setupRotations(IronGolem ironGolemEntity, PoseStack matrixStack, float f, float g, float h) {
        super.setupRotations(ironGolemEntity, matrixStack, f, g, h);
        if ((double) ironGolemEntity.walkAnimation.speed() >= 0.01D) {
            float j = ironGolemEntity.walkAnimation.position() - ironGolemEntity.walkAnimation.speed() * (1.0F - h) + 6.0F;
            float k = (Math.abs(j % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.mulPose(Axis.ZP.rotationDegrees(6.5F * k));
        }
    }
}
