package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Withender;

public class WithenderRenderer extends MobRenderer<Withender, WitherBossModel<Withender>> {
    private final ResourceLocation texture;

   public WithenderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
      super(context, new WitherBossModel<>(context.bakeLayer(ModelLayers.WITHER)), 1.0F);
      this.texture = texture;
   }

   @Override
protected void scale(Withender witherEntity_1, PoseStack matrixStack_1, float float_1) {
      float float_2 = 2.0F;
      int int_1 = witherEntity_1.getInvulnerableTicks();
      if (int_1 > 0) {
         float_2 -= (int_1 - float_1) / 220.0F * 0.5F;
      }

      matrixStack_1.scale(float_2, float_2, float_2);
   }

   @Override
   public ResourceLocation getTextureLocation(Withender witho) {
      return this.texture;
   }
}
