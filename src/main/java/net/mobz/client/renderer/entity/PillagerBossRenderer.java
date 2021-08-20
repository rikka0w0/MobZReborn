package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.PillagerBoss;

public class PillagerBossRenderer extends IllagerRenderer<PillagerBoss> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/pillagerboss.png");

   public PillagerBossRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new ItemInHandLayer<>(this));
   }

   @Override
   protected void scale(PillagerBoss pilli, PoseStack matrixStack, float f) {
      matrixStack.scale(1.3F, 1.3F, 1.3F);
   }

   @Override
   public ResourceLocation getTextureLocation(PillagerBoss entity) {
      return SKIN;
   }
}