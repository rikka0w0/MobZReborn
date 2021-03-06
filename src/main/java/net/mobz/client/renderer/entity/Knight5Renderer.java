package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Knight5Entity;

public class Knight5Renderer extends HumanoidMobRenderer<Knight5Entity, PlayerModel<Knight5Entity>> {
    public Knight5Renderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Knight5Entity knight5entitye) {
        return new ResourceLocation("mobz:textures/entity/knight5entity.png");
    }
}