package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.mobz.entity.skeli1;

public class skeli1renderer extends EasySkeletonRender<skeli1> {
    public skeli1renderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, texture);

        String overlayPath = texture.getPath().replace(".png", "_overlay.png");
        ResourceLocation overlayTexture = new ResourceLocation(texture.getNamespace(), overlayPath);
        this.addLayer(new OverlayFeature<>(this, context.getModelSet(), overlayTexture));
    }

    public static class OverlayFeature<T extends Mob & RangedAttackMob, M extends EntityModel<T>> extends RenderLayer<T, M> {
        private final ResourceLocation texture;
        private final SkeletonModel<T> model;

        public OverlayFeature(RenderLayerParent<T, M> featureRendererContext, EntityModelSet entityModelSet, ResourceLocation overlayTexture) {
            super(featureRendererContext);
            this.model = new SkeletonModel<>(entityModelSet.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
            this.texture = overlayTexture;
        }

        @Override
		public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T mobEntity, float f,
                float g, float h, float j, float k, float l) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, this.texture, matrixStack, vertexConsumerProvider,
                    i, mobEntity, f, g, j, k, l, h, 1.0F, 1.0F, 1.0F);
        }
    }
}