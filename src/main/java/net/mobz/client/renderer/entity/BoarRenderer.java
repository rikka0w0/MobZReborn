package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Boar;

public class BoarRenderer extends MobRenderer<Boar, PigModel<Boar>> {
    public BoarRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PigModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boar boar) {
        return new ResourceLocation("mobz:textures/entity/boar.png");
    }
}