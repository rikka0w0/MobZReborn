package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class SCreeperRenderer extends MobRenderer<CreeperEntity, CreeperModel<CreeperEntity>> {
  private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/soulcreeper.png");

  public SCreeperRenderer(EntityRendererManager entityRenderDispatcher) {
    super(entityRenderDispatcher, new CreeperModel<>(), 0.5F);
    this.addLayer(new CreeperChargeLayer(this));
  }

  @Override
  protected void scale(CreeperEntity creeperEntity, MatrixStack matrixStack, float f) {
    float g = creeperEntity.getSwelling(f);
    float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
    g = MathHelper.clamp(g, 0.0F, 1.0F);
    g *= g;
    g *= g;
    float i = (1.0F + g * 0.4F) * h;
    float j = (1.0F + g * 0.1F) / h;
    matrixStack.scale(i, j, i);
  }

  @Override
  protected float getBob(CreeperEntity creeperEntity, float f) {
    float g = creeperEntity.getSwelling(f);
    return (int) (g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
  }

  @Override
  public ResourceLocation getTextureLocation(CreeperEntity creeperEntity) {
    return SKIN;
  }
}
