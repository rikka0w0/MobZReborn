package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.FrostEntity;

public class FrostRenderer extends MobRenderer<FrostEntity, BlazeModel<FrostEntity>> {
    public FrostRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new BlazeModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostEntity frostentity) {
        return new ResourceLocation("mobz:textures/entity/frost.png");
    }
}
