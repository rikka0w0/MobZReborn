package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Knight4Entity;

public class Knight4Renderer extends HumanoidMobRenderer<Knight4Entity, PlayerModel<Knight4Entity>> {
    public Knight4Renderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Knight4Entity knight4entitye) {
        return new ResourceLocation("mobz:textures/entity/knight4entity.png");
    }
}