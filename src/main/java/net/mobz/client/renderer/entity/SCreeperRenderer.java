package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SCreeperRenderer extends MobRenderer<Creeper, CreeperModel<Creeper>> {
  private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/soulcreeper.png");

  public SCreeperRenderer(EntityRenderDispatcher entityRenderDispatcher) {
    super(entityRenderDispatcher, new CreeperModel<>(), 0.5F);
    this.addLayer(new CreeperPowerLayer(this));
  }

  @Override
  protected void scale(Creeper creeperEntity, PoseStack matrixStack, float f) {
    float g = creeperEntity.getSwelling(f);
    float h = 1.0F + Mth.sin(g * 100.0F) * g * 0.01F;
    g = Mth.clamp(g, 0.0F, 1.0F);
    g *= g;
    g *= g;
    float i = (1.0F + g * 0.4F) * h;
    float j = (1.0F + g * 0.1F) / h;
    matrixStack.scale(i, j, i);
  }

  @Override
  protected float getBob(Creeper creeperEntity, float f) {
    float g = creeperEntity.getSwelling(f);
    return (int) (g * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(g, 0.5F, 1.0F);
  }

  @Override
  public ResourceLocation getTextureLocation(Creeper creeperEntity) {
    return SKIN;
  }
}
