package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.WolfModel;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.resources.ResourceLocation;

public class DogRenderer extends MobRenderer<Wolf, WolfModel<Wolf>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/dog.png");

   public DogRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher, new WolfModel<>(), 0.5F);
   }

   @Override
   protected float getBob(Wolf wolfEntity, float f) {
      return wolfEntity.getTailAngle();
   }

   @Override
   public ResourceLocation getTextureLocation(Wolf wolfEntity) {
      return TEXTURE;
   }
}
