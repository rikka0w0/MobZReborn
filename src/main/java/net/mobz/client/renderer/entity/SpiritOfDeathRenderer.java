package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.client.renderer.entity.state.VexRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SpiritOfDeathRenderer extends VexRenderer {
    private final ResourceLocation texture;
    private final ResourceLocation charging_texture;

    public SpiritOfDeathRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
        String eyePath = texture.getPath().replace(".png", "_charging.png");
        this.charging_texture = ResourceLocation.tryBuild(texture.getNamespace(), eyePath);
    }

    @Override
    public ResourceLocation getTextureLocation(VexRenderState renderState) {
        return renderState.isCharging ? this.charging_texture : this.texture;
    }

    @Override
    protected void scale(VexRenderState renderState, PoseStack matrixStack) {
        matrixStack.scale(0.4F, 0.4F, 0.4F);
    }
}
