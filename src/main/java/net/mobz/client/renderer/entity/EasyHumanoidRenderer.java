package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class EasyHumanoidRenderer<T extends Mob> extends HumanoidMobRenderer<T, PlayerModel<T>> {
	private final ResourceLocation texture;

    public EasyHumanoidRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T archer) {
        return this.texture;
    }
}