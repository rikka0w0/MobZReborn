package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.RavagerModel;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.util.ResourceLocation;

public class BabyravagerRenderer extends MobRenderer<RavagerEntity, RavagerModel> {
  private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/ravo.png");

  public BabyravagerRenderer(EntityRendererManager entityRenderDispatcher) {
    super(entityRenderDispatcher, new RavagerModel(), 0.4F);
  }

  @Override
  public ResourceLocation getTextureLocation(RavagerEntity ravagerEntity) {
    return SKIN;
  }

  @Override
  protected void scale(RavagerEntity vexEntity, MatrixStack matrixStack, float f) {
    matrixStack.scale(0.4F, 0.4F, 0.4F);
  }
}
