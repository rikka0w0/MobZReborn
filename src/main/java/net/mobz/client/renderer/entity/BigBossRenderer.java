package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.BigBossEntity;

public class BigBossRenderer extends BipedRenderer<BigBossEntity, ZombieModel<BigBossEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/bigboss.png");

   public BigBossRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new ZombieModel<>(0.0F, false), 0.5F);
      this.addLayer(
            new BipedArmorLayer<>(this, new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true)));
   }

   @Override
   protected void scale(BigBossEntity smallZombie, MatrixStack matrixStack, float f) {
      matrixStack.scale(2.5F, 2.5F, 2.5F);
   }

   @Override
   public ResourceLocation getTextureLocation(BigBossEntity Bigy) {
      return SKIN;
   }
}
