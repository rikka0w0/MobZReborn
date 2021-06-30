package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.WithEntity;

public class WithRenderer extends MobRenderer<WithEntity, BlazeModel<WithEntity>> {
    public WithRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new BlazeModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WithEntity withtentity) {
        return new ResourceLocation("mobz:textures/entity/with.png");
    }
}
