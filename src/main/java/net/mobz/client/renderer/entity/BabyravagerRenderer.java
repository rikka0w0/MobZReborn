package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.resources.ResourceLocation;

public class BabyravagerRenderer extends MobRenderer<Ravager, RavagerModel> {
	private final ResourceLocation texture;

    public BabyravagerRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new RavagerModel(context.bakeLayer(ModelLayers.RAVAGER)), 0.4F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(Ravager ravagerEntity) {
        return this.texture;
    }

    @Override
    protected void scale(Ravager vexEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.4F, 0.4F, 0.4F);
    }
}
