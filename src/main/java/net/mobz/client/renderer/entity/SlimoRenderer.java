package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class SlimoRenderer extends MobRenderer<SlimeEntity, SlimeModel<SlimeEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/grassslime.png");

   public SlimoRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new SlimeModel<>(1), 0.25F);
      this.addLayer(new SlimeGelLayer<>(this));
   }

   @Override
   public void render(SlimeEntity slimeEntity, float f, float g, MatrixStack matrixStack,
         IRenderTypeBuffer vertexConsumerProvider, int i) {
      this.shadowRadius = 0.25F * (float) slimeEntity.getSize();
      super.render((SlimeEntity) slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
   }

   @Override
   protected void scale(SlimeEntity slimeEntity, MatrixStack matrixStack, float f) {
      matrixStack.scale(0.999F, 0.999F, 0.999F);
      matrixStack.translate(0.0D, 0.0010000000474974513D, 0.0D);
      float h = (float) slimeEntity.getSize();
      float i = MathHelper.lerp(f, slimeEntity.oSquish, slimeEntity.squish) / (h * 0.5F + 1.0F);
      float j = 1.0F / (i + 1.0F);
      matrixStack.scale(j * h, 1.0F / j * h, j * h);
   }

   @Override
   public ResourceLocation getTextureLocation(SlimeEntity slimeEntity) {
      return SKIN;
   }
}
