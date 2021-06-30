package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.PillagerBoss;

public class PillagerBossRenderer extends IllagerRenderer<PillagerBoss> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/pillagerboss.png");

   public PillagerBossRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new HeldItemLayer<>(this));
   }

   @Override
   protected void scale(PillagerBoss pilli, MatrixStack matrixStack, float f) {
      matrixStack.scale(1.3F, 1.3F, 1.3F);
   }

   @Override
   public ResourceLocation getTextureLocation(PillagerBoss entity) {
      return SKIN;
   }
}