package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.WithEntity;

public class WithRenderer extends MobRenderer<WithEntity, BlazeModel<WithEntity>> {
    public WithRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new BlazeModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WithEntity withtentity) {
        return new ResourceLocation("mobz:textures/entity/with.png");
    }
}
