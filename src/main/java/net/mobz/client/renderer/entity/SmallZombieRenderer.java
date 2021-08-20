package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.SmallZombie;

public class SmallZombieRenderer extends AbstractZombieRenderer<SmallZombie, ZombieModel<SmallZombie>> {
   public SmallZombieRenderer(EntityRenderDispatcher dispatcher) {
      super(dispatcher, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true),
            new ZombieModel<>(0.5F, true));
   }

   @Override
   protected void scale(SmallZombie smallZombie, PoseStack matrixStack, float f) {
      matrixStack.scale(0.5F, 0.5F, 0.5F);
   }

   @Override
   public ResourceLocation getTextureLocation(SmallZombie stone) {
      return new ResourceLocation("mobz:textures/entity/smallzombie.png");
   }
}
