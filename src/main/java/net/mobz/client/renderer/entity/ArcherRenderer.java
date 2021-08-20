package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.ArcherEntity;

public class ArcherRenderer extends HumanoidMobRenderer<ArcherEntity, PlayerModel<ArcherEntity>> {

    public ArcherRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ArcherEntity archer) {
        return new ResourceLocation("mobz:textures/entity/archer.png");
    }
}