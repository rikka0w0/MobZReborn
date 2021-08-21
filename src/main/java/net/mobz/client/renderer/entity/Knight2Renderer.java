package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Knight2Entity;

public class Knight2Renderer extends HumanoidMobRenderer<Knight2Entity, PlayerModel<Knight2Entity>> {
    public Knight2Renderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Knight2Entity knight2entitye) {
        return new ResourceLocation("mobz:textures/entity/knight2entity.png");
    }
}