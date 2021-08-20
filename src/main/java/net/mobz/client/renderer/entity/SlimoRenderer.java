package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SlimoRenderer extends MobRenderer<Slime, SlimeModel<Slime>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/grassslime.png");

   public SlimoRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher, new SlimeModel<>(1), 0.25F);
      this.addLayer(new SlimeOuterLayer<>(this));
   }

   @Override
   public void render(Slime slimeEntity, float f, float g, PoseStack matrixStack,
         MultiBufferSource vertexConsumerProvider, int i) {
      this.shadowRadius = 0.25F * (float) slimeEntity.getSize();
      super.render((Slime) slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
   }

   @Override
   protected void scale(Slime slimeEntity, PoseStack matrixStack, float f) {
      matrixStack.scale(0.999F, 0.999F, 0.999F);
      matrixStack.translate(0.0D, 0.0010000000474974513D, 0.0D);
      float h = (float) slimeEntity.getSize();
      float i = Mth.lerp(f, slimeEntity.oSquish, slimeEntity.squish) / (h * 0.5F + 1.0F);
      float j = 1.0F / (i + 1.0F);
      matrixStack.scale(j * h, 1.0F / j * h, j * h);
   }

   @Override
   public ResourceLocation getTextureLocation(Slime slimeEntity) {
      return SKIN;
   }
}
