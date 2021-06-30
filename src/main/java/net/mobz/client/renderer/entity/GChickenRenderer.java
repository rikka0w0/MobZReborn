package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GChickenRenderer extends MobRenderer<ChickenEntity, ChickenModel<ChickenEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/gchicken.png");

   public GChickenRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new ChickenModel<>(), 0.3F);
   }

   @Override
   public ResourceLocation getTextureLocation(ChickenEntity chickenEntity) {
      return SKIN;
   }

   @Override
   protected float getBob(ChickenEntity chickenEntity, float f) {
      float g = MathHelper.lerp(f, chickenEntity.oFlap, chickenEntity.flap);
      float h = MathHelper.lerp(f, chickenEntity.oFlapSpeed, chickenEntity.flapSpeed);
      return (MathHelper.sin(g) + 1.0F) * h;
   }
}
