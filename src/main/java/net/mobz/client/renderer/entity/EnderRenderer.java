package net.mobz.client.renderer.entity;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class EnderRenderer extends MobRenderer<EnderMan, EndermanModel<EnderMan>> {
    private final ResourceLocation texture;
    private final Random random = new Random();

    public EnderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new EndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new CarriedBlockLayer(this, context.getBlockRenderDispatcher()));

        this.texture = texture;
        String eyePath = texture.getPath().replace(".png", "_eyes.png");
        ResourceLocation eyeTexture = new ResourceLocation(texture.getNamespace(), eyePath);
        this.addLayer(new EnderEyes<>(this, eyeTexture));
    }

    @Override
    public void render(EnderMan endermanEntity, float f, float g, PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider, int i) {
        BlockState blockState = endermanEntity.getCarriedBlock();
        EndermanModel<EnderMan> endermanEntityModel = this.getModel();
        endermanEntityModel.carrying = blockState != null;
        endermanEntityModel.creepy = endermanEntity.isCreepy();
        super.render(endermanEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Vec3 getRenderOffset(EnderMan endermanEntity, float f) {
        if (endermanEntity.isCreepy()) {
            return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(endermanEntity, f);
        }
    }

    @Override
	public ResourceLocation getTextureLocation(EnderMan endermanEntity) {
        return this.texture;
    }

    public static class EnderEyes<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
    	private final RenderType texture;

    	public EnderEyes(RenderLayerParent<T, EndermanModel<T>> context, ResourceLocation eyeTexture) {
    		super(context);
    		this.texture = RenderType.eyes(eyeTexture);
    	}

    	@Override
		public RenderType renderType() {
    		return this.texture;
    	}
    }
}
