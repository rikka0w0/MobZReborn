package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PiglinModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.PigmanEntity;

public class PigmanRenderer extends BipedRenderer<PigmanEntity, PiglinModel<PigmanEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("mobz:textures/entity/pigman.png");

   public PigmanRenderer(EntityRendererManager dispatcher) {
      super(dispatcher, new PiglinModel<>(0.0F, 64, 64), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
      this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.02F)));
   }

   @Override
   public ResourceLocation getTextureLocation(PigmanEntity mobEntity) {
      return TEXTURE;
   }
}
