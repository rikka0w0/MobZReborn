package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.resources.ResourceLocation;

public class BabyravagerRenderer extends MobRenderer<Ravager, RavagerModel> {
  private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/ravo.png");

  public BabyravagerRenderer(EntityRenderDispatcher entityRenderDispatcher) {
    super(entityRenderDispatcher, new RavagerModel(), 0.4F);
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
