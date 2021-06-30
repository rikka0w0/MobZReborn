package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PandaModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.Blackbear;

public class BlackbearRenderer extends MobRenderer<Blackbear, PandaModel<Blackbear>> {
    public BlackbearRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PandaModel<>(0, 0.0F), 0.8F);

    }

    @Override
    public ResourceLocation getTextureLocation(Blackbear Blackbear) {
        return new ResourceLocation("mobz:textures/entity/blackbear.png");
    }

}