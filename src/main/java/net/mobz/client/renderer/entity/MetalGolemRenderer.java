package net.mobz.client.renderer.entity;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.resources.ResourceLocation;

public class MetalGolemRenderer extends EasyGolemRenderer {
    public MetalGolemRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture, false);
        this.addLayer(new MetalGolemCrack(this));
    }

    @Override
    protected void scale(IronGolem golem, PoseStack matrixStack, float f) {
        matrixStack.scale(1.15F, 1.15F, 1.15F);
    }

    public static class MetalGolemCrack extends RenderLayer<IronGolem, IronGolemModel<IronGolem>> {
    	private static final Map<Crackiness.Level, ResourceLocation> DAMAGE_TO_TEXTURE;

    	public MetalGolemCrack(RenderLayerParent<IronGolem, IronGolemModel<IronGolem>> featureRendererContext) {
    		super(featureRendererContext);
    	}

    	@Override
    	public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i,
    			IronGolem ironGolemEntity, float f, float g, float h, float j, float k, float l) {
    		if (!ironGolemEntity.isInvisible()) {
    			Crackiness.Level crack = ironGolemEntity.getCrackiness();
    			if (crack != Crackiness.Level.NONE) {
    				ResourceLocation identifier = DAMAGE_TO_TEXTURE.get(crack);
    				renderColoredCutoutModel(this.getParentModel(), identifier, matrixStack, vertexConsumerProvider, i,
    						ironGolemEntity, 1.0F, 1.0F, 1.0F);
    			}
    		}
    	}

    	static {
    		DAMAGE_TO_TEXTURE = ImmutableMap.of(Crackiness.Level.LOW,
    				new ResourceLocation("mobz:textures/entity/metal_golem_crackiness_low.png"),
    				Crackiness.Level.MEDIUM,
    				new ResourceLocation("mobz:textures/entity/metal_golem_crackiness_medium.png"),
    				Crackiness.Level.HIGH,
    				new ResourceLocation("mobz:textures/entity/metal_golem_crackiness_high.png"));
    	}
    }
}
