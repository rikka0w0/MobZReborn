package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.resources.ResourceLocation;

public class EasyEvokerRenderer<T extends SpellcasterIllager> extends EvokerRenderer<T> {
	private final ResourceLocation texture;

    public EasyEvokerRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T illusioner) {
        return this.texture;
    }
}