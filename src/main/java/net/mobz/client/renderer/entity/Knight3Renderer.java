package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Knight3Entity;

public class Knight3Renderer extends HumanoidMobRenderer<Knight3Entity, PlayerModel<Knight3Entity>> {
    public Knight3Renderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Knight3Entity knight3entitye) {
        return new ResourceLocation("mobz:textures/entity/knight3entity.png");
    }
}