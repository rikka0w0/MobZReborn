package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.FastEntity;

public class FastRenderer extends BipedRenderer<FastEntity, ZombieModel<FastEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/fast.png");

   public FastRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new ZombieModel<>(0.0F, false), 0.5F);
      this.addLayer(
            new BipedArmorLayer<>(this, new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true)));
   }

   @Override
   public ResourceLocation getTextureLocation(FastEntity Fasty) {
      return SKIN;
   }
}
