package net.mobz.forge;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.mobz.client.IEntityRendererRegisterWrapper;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ForgeClientRegistryWrapper implements IEntityRendererRegisterWrapper {
	public final static IEntityRendererRegisterWrapper instance = new ForgeClientRegistryWrapper();
	
	@Override
	public <T extends Entity> void register(EntityType<T> entityType,
			Function<EntityRendererManager, EntityRenderer<? super T>> rendererConstructor) {
		RenderingRegistry.registerEntityRenderingHandler(entityType, manager -> rendererConstructor.apply(manager));
	}
}
