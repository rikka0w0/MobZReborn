package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Archer2Entity;

public class Archer2Renderer extends BipedRenderer<Archer2Entity, PlayerModel<Archer2Entity>> {

    public Archer2Renderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Archer2Entity archer2) {
        return new ResourceLocation("mobz", "textures/entity/archer2.png");
    }
}