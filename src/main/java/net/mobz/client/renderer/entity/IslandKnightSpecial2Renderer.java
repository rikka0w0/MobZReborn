package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.IslandKnightSpecial2;

public class IslandKnightSpecial2Renderer extends HumanoidMobRenderer<IslandKnightSpecial2, PlayerModel<IslandKnightSpecial2>> {
    public IslandKnightSpecial2Renderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))
                ));
    }

    @Override
    public ResourceLocation getTextureLocation(IslandKnightSpecial2 specialknight1) {
        return new ResourceLocation("mobz:textures/entity/knightspecial2.png");
    }
}