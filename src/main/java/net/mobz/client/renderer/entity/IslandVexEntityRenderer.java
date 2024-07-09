package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.resources.ResourceLocation;

public class IslandVexEntityRenderer extends MobRenderer<Vex, VexModel> {
    private final ResourceLocation texture;
    private final ResourceLocation charging_texture;

    public IslandVexEntityRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new VexModel(context.bakeLayer(ModelLayers.VEX)), 0.3F);
        this.texture = texture;
        String eyePath = texture.getPath().replace(".png", "_charging.png");
        this.charging_texture = new ResourceLocation(texture.getNamespace(), eyePath);
    }

    @Override
    public ResourceLocation getTextureLocation(Vex vexEntity) {
        return vexEntity.isCharging() ? this.charging_texture : this.texture;
    }

    @Override
    protected void scale(Vex vexEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.4F, 0.4F, 0.4F);
    }
}
