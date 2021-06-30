package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;

public class DogRenderer extends MobRenderer<WolfEntity, WolfModel<WolfEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/dog.png");

   public DogRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new WolfModel<>(), 0.5F);
   }

   @Override
   protected float getBob(WolfEntity wolfEntity, float f) {
      return wolfEntity.getTailAngle();
   }

   @Override
   public ResourceLocation getTextureLocation(WolfEntity wolfEntity) {
      return TEXTURE;
   }
}
