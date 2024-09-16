package net.mobz.client.renderer.entity;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;

public class EasySpiderRenderer<T extends Spider> extends SpiderRenderer<T> {
	private final ResourceLocation texture;

    public EasySpiderRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);

        this.texture = texture;
        String eyePath = texture.getPath().replace(".png", "_eyes.png");
        ResourceLocation eyeTexture = ResourceLocation.tryBuild(texture.getNamespace(), eyePath);
        this.addLayer(new SpiderEyes<>(this, eyeTexture));
    }

    @Override
    public ResourceLocation getTextureLocation(T spiEntity) {
        return this.texture;
    }

    public static class SpiderEyes<T extends Entity, M extends SpiderModel<T>> extends EyesLayer<T, M> {
    	private final RenderType texture;

    	public SpiderEyes(RenderLayerParent<T, M> context, ResourceLocation eyeTexture) {
    		super(context);
    		this.texture = RenderType.eyes(eyeTexture);
    	}

    	@Override
		public RenderType renderType() {
    		return texture;
    	}
    }

}