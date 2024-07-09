package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.resources.ResourceLocation;

public class DogRenderer extends MobRenderer<Wolf, WolfModel<Wolf>> {
    private final ResourceLocation texture;

    public DogRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new WolfModel<>(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
        this.texture = texture;
    }

    @Override
    protected float getBob(Wolf wolfEntity, float f) {
        return wolfEntity.getTailAngle();
    }

    @Override
    public ResourceLocation getTextureLocation(Wolf wolfEntity) {
        return this.texture;
    }
}
