package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Archer2Entity;

public class Archer2Renderer extends HumanoidMobRenderer<Archer2Entity, PlayerModel<Archer2Entity>> {

    public Archer2Renderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Archer2Entity archer2) {
        return new ResourceLocation("mobz", "textures/entity/archer2.png");
    }
}