package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GChickenRenderer extends MobRenderer<Chicken, ChickenModel<Chicken>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/gchicken.png");

   public GChickenRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher, new ChickenModel<>(), 0.3F);
   }

   @Override
   public ResourceLocation getTextureLocation(Chicken chickenEntity) {
      return SKIN;
   }

   @Override
   protected float getBob(Chicken chickenEntity, float f) {
      float g = Mth.lerp(f, chickenEntity.oFlap, chickenEntity.flap);
      float h = Mth.lerp(f, chickenEntity.oFlapSpeed, chickenEntity.flapSpeed);
      return (Mth.sin(g) + 1.0F) * h;
   }
}
