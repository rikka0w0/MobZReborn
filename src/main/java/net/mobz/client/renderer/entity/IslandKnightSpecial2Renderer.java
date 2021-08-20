package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.IslandKnightSpecial2;

public class IslandKnightSpecial2Renderer extends HumanoidMobRenderer<IslandKnightSpecial2, PlayerModel<IslandKnightSpecial2>> {
  public IslandKnightSpecial2Renderer(EntityRenderDispatcher dispatcher) {
    super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    this.addLayer(
        new HumanoidArmorLayer<>(this, new PlayerModel<>(0.5F, true), new PlayerModel<>(1.0F, true)));
  }

  @Override
  public ResourceLocation getTextureLocation(IslandKnightSpecial2 specialknight1) {
    return new ResourceLocation("mobz:textures/entity/knightspecial2.png");
  }
}