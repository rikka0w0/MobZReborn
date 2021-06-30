package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.FullIronEntity;

public class FullIronRenderer extends BipedRenderer<FullIronEntity, PlayerModel<FullIronEntity>> {
    public FullIronRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
    }

    @Override
    public ResourceLocation getTextureLocation(FullIronEntity fullironEntitye) {
        return new ResourceLocation("mobz:textures/entity/fulliron.png");
    }
}
