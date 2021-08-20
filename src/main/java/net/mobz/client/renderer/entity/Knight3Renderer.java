package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Knight3Entity;

public class Knight3Renderer extends HumanoidMobRenderer<Knight3Entity, PlayerModel<Knight3Entity>> {
    public Knight3Renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new PlayerModel<>(0.5F, true),
                new PlayerModel<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(Knight3Entity knight3entitye) {
        return new ResourceLocation("mobz:textures/entity/knight3entity.png");
    }
}