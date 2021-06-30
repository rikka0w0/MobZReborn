package net.mobz.client.renderer.entity;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldBlockLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.mobz.client.renderer.features.EnderEyes;

public class EnderRenderer extends MobRenderer<EndermanEntity, EndermanModel<EndermanEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/ender.png");
   private final Random random = new Random();

   public EnderRenderer(EntityRendererManager entityRenderDispatcher) {
      super(entityRenderDispatcher, new EndermanModel<>(0.0F), 0.5F);
      this.addLayer(new EnderEyes<>(this));
      this.addLayer(new HeldBlockLayer(this));
   }

   @Override
   public void render(EndermanEntity endermanEntity, float f, float g, MatrixStack matrixStack,
         IRenderTypeBuffer vertexConsumerProvider, int i) {
      BlockState blockState = endermanEntity.getCarriedBlock();
      EndermanModel<EndermanEntity> endermanEntityModel = this.getModel();
      endermanEntityModel.carrying = blockState != null;
      endermanEntityModel.creepy = endermanEntity.isCreepy();
      super.render((EndermanEntity) endermanEntity, f, g, matrixStack, vertexConsumerProvider, i);
   }

   @Override
   public Vector3d getRenderOffset(EndermanEntity endermanEntity, float f) {
      if (endermanEntity.isCreepy()) {
         return new Vector3d(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
      } else {
         return super.getRenderOffset(endermanEntity, f);
      }
   }

   public ResourceLocation getTextureLocation(EndermanEntity endermanEntity) {
      return SKIN;
   }
}
