package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.ResourceLocation;

public class NetherWolfRenderer extends WolfRenderer {
    private final ResourceLocation texture;

    public NetherWolfRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(WolfRenderState renderState) {
		return this.texture;
    }
}
