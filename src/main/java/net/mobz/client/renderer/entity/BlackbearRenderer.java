package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PandaModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Blackbear;

public class BlackbearRenderer extends MobRenderer<Blackbear, PandaModel<Blackbear>> {
    public BlackbearRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PandaModel<>(0, 0.0F), 0.8F);

    }

    @Override
    public ResourceLocation getTextureLocation(Blackbear Blackbear) {
        return new ResourceLocation("mobz:textures/entity/blackbear.png");
    }

}