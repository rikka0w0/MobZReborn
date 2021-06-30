package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.ArcherEntity;

public class ArcherRenderer extends BipedRenderer<ArcherEntity, PlayerModel<ArcherEntity>> {

    public ArcherRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ArcherEntity archer) {
        return new ResourceLocation("mobz:textures/entity/archer.png");
    }
}