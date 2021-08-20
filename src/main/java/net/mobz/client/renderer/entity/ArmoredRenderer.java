package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.ArmoredEntity;

public class ArmoredRenderer extends HumanoidMobRenderer<ArmoredEntity, ZombieModel<ArmoredEntity>> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/armored.png");

    public ArmoredRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR))));
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredEntity Army) {
        return SKIN;
    }
}
