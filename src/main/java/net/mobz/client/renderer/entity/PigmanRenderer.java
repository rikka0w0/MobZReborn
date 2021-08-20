package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.PigmanEntity;

public class PigmanRenderer extends HumanoidMobRenderer<PigmanEntity, PiglinModel<PigmanEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/pigman.png");

   public PigmanRenderer(EntityRenderDispatcher dispatcher) {
      super(dispatcher, new PiglinModel<>(0.0F, 64, 64), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
      this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(0.5F), new HumanoidModel<>(1.02F)));
   }

   @Override
   public ResourceLocation getTextureLocation(PigmanEntity mobEntity) {
      return TEXTURE;
   }
}
