package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.IslandKnightNormal;

public class IslandKnightNRenderer extends HumanoidMobRenderer<IslandKnightNormal, PlayerModel<IslandKnightNormal>> {
    public IslandKnightNRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(IslandKnightNormal Normalknight) {
        return new ResourceLocation("mobz:textures/entity/normalknight.png");
    }
}