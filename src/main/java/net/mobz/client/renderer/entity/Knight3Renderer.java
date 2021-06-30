package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Knight3Entity;

public class Knight3Renderer extends BipedRenderer<Knight3Entity, PlayerModel<Knight3Entity>> {
    public Knight3Renderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new PlayerModel<>(0.5F, true),
                new PlayerModel<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(Knight3Entity knight3entitye) {
        return new ResourceLocation("mobz:textures/entity/knight3entity.png");
    }
}