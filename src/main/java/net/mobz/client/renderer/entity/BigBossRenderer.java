package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.BigBossEntity;

public class BigBossRenderer extends HumanoidMobRenderer<BigBossEntity, ZombieModel<BigBossEntity>> {
   private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/bigboss.png");

   public BigBossRenderer(EntityRendererProvider.Context context) {
       super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
       this.addLayer(new HumanoidArmorLayer<>(this,
               new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
               new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
               context.getModelManager()));
   }

   @Override
   protected void scale(BigBossEntity smallZombie, PoseStack matrixStack, float f) {
      matrixStack.scale(2.5F, 2.5F, 2.5F);
   }

   @Override
   public ResourceLocation getTextureLocation(BigBossEntity Bigy) {
      return SKIN;
   }
}
