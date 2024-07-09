package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class EasyZombieRenderer<T extends Zombie> extends AbstractZombieRenderer<T, ZombieModel<T>> {
	private final ResourceLocation texture;

    public EasyZombieRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context,
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie Fasty) {
    	return this.texture;
    }
}
