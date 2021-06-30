package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.VexModel;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.util.ResourceLocation;

public class IslandVexEntityRenderer extends BipedRenderer<VexEntity, VexModel> {
  private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/islandvex.png");
  private static final ResourceLocation CHARGING_TEXTURE = new ResourceLocation("mobz:textures/entity/islandvex.png");

  public IslandVexEntityRenderer(EntityRendererManager entityRenderDispatcher) {
    super(entityRenderDispatcher, new VexModel(), 0.3F);
  }

  @Override
  public ResourceLocation getTextureLocation(VexEntity vexEntity) {
    return vexEntity.isCharging() ? CHARGING_TEXTURE : TEXTURE;
  }

  @Override
  protected void scale(VexEntity vexEntity, MatrixStack matrixStack, float f) {
    matrixStack.scale(0.4F, 0.4F, 0.4F);
  }
}
