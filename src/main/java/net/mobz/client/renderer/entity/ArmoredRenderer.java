package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.ArmoredEntity;

public class ArmoredRenderer extends HumanoidMobRenderer<ArmoredEntity, ZombieModel<ArmoredEntity>> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/armored.png");

    public ArmoredRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new ZombieModel<>(0.0F, false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new ZombieModel<>(0.5F, true),
                new ZombieModel<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredEntity Army) {
        return SKIN;
    }
}
