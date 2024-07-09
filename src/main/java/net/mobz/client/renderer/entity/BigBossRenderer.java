package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.BigBoss;

public class BigBossRenderer extends EasyZombieRenderer<BigBoss> {
   public BigBossRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
       super(context, texture);
   }

   @Override
   protected void scale(BigBoss smallZombie, PoseStack matrixStack, float f) {
      matrixStack.scale(2.5F, 2.5F, 2.5F);
   }
}
