package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Withender;

public class WithenderRenderer extends MobRenderer<Withender, WitherBossModel<Withender>> {
   private static final ResourceLocation INVINCIBLE_SKIN = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation SKIN = new ResourceLocation("textures/entity/wither/wither.png");

   public WithenderRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
      super(entityRenderDispatcher_1, new WitherBossModel<>(0.0F), 1.0F);
   }

   public ResourceLocation method_4153(Withender witherEntity_1) {
      int int_1 = witherEntity_1.getInvulnerableTicks();
      return int_1 > 0 && (int_1 > 80 || int_1 / 5 % 2 != 1) ? INVINCIBLE_SKIN : SKIN;
   }

   protected void method_4152(Withender witherEntity_1, PoseStack matrixStack_1, float float_1) {
      float float_2 = 2.0F;
      int int_1 = witherEntity_1.getInvulnerableTicks();
      if (int_1 > 0) {
         float_2 -= ((float) int_1 - float_1) / 220.0F * 0.5F;
      }

      matrixStack_1.scale(float_2, float_2, float_2);
   }

   @Override
   public ResourceLocation getTextureLocation(Withender witho) {
      return new ResourceLocation("mobz:textures/entity/wither.png");
   }
}
