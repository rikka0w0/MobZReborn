package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.IslandKing;

public class IslandKingRenderer extends HumanoidMobRenderer<IslandKing, PlayerModel<IslandKing>> {
    public IslandKingRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(IslandKing kingoftheisland) {
        return new ResourceLocation("mobz:textures/entity/islandking.png");
    }
}