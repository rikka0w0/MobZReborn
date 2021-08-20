package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.FriendEntity;

public class FriendRenderer extends HumanoidMobRenderer<FriendEntity, PlayerModel<FriendEntity>> {
    public FriendRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, true), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(0.5F), new HumanoidModel<>(1.0F)));
    }

    @Override
    public ResourceLocation getTextureLocation(FriendEntity FriendEntitye) {
        return new ResourceLocation("mobz:textures/entity/friend.png");
    }
}
