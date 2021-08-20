package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.SmallZombie;

public class SmallZombieRenderer extends AbstractZombieRenderer<SmallZombie, ZombieModel<SmallZombie>> {
    public SmallZombieRenderer(EntityRendererProvider.Context context) {
        super(context,
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
    }

    @Override
    protected void scale(SmallZombie smallZombie, PoseStack matrixStack, float f) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SmallZombie stone) {
        return new ResourceLocation("mobz:textures/entity/smallzombie.png");
    }
}
