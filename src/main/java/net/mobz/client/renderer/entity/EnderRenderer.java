package net.mobz.client.renderer.entity;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.mobz.client.renderer.features.EnderEyes;

public class EnderRenderer extends MobRenderer<EnderMan, EndermanModel<EnderMan>> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/ender.png");
    private final Random random = new Random();

    public EnderRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new EnderEyes<>(this));
        this.addLayer(new CarriedBlockLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public void render(EnderMan endermanEntity, float f, float g, PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider, int i) {
        BlockState blockState = endermanEntity.getCarriedBlock();
        EndermanModel<EnderMan> endermanEntityModel = this.getModel();
        endermanEntityModel.carrying = blockState != null;
        endermanEntityModel.creepy = endermanEntity.isCreepy();
        super.render((EnderMan) endermanEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Vec3 getRenderOffset(EnderMan endermanEntity, float f) {
        if (endermanEntity.isCreepy()) {
            return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(endermanEntity, f);
        }
    }

    public ResourceLocation getTextureLocation(EnderMan endermanEntity) {
        return SKIN;
    }
}
