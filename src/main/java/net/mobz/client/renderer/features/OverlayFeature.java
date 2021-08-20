package net.mobz.client.renderer.features;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;

public class OverlayFeature<T extends Mob & RangedAttackMob, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation SKIN = new ResourceLocation("mobz:textures/entity/skeli1_overlay.png");
    private final SkeletonModel<T> model;

    public OverlayFeature(RenderLayerParent<T, M> featureRendererContext, EntityModelSet entityModelSet) {
        super(featureRendererContext);
        this.model = new SkeletonModel<>(entityModelSet.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
    }

    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T mobEntity, float f,
            float g, float h, float j, float k, float l) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrixStack, vertexConsumerProvider,
                i, mobEntity, f, g, j, k, l, h, 1.0F, 1.0F, 1.0F);
    }
}
