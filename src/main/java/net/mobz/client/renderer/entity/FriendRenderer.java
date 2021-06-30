package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.FriendEntity;

public class FriendRenderer extends BipedRenderer<FriendEntity, PlayerModel<FriendEntity>> {
    public FriendRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, true), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
    }

    @Override
    public ResourceLocation getTextureLocation(FriendEntity FriendEntitye) {
        return new ResourceLocation("mobz:textures/entity/friend.png");
    }
}
