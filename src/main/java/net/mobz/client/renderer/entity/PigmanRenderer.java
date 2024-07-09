package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.Pigman;

public class PigmanRenderer extends HumanoidMobRenderer<Pigman, PiglinModel<Pigman>> {
    private final ResourceLocation texture;

    public PigmanRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PiglinModel<>(context.bakeLayer(ModelLayers.PIGLIN)), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
		this.addLayer(new HumanoidArmorLayer<>(this,
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PIGLIN_INNER_ARMOR)),
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PIGLIN_OUTER_ARMOR)),
				context.getModelManager()));
		this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(Pigman mobEntity) {
        return this.texture;
    }
}
