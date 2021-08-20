package net.mobz.forge;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.mobz.client.IEntityRendererRegisterWrapper;

public class ForgeClientRegistryWrapper implements IEntityRendererRegisterWrapper {
	public final static IEntityRendererRegisterWrapper instance = new ForgeClientRegistryWrapper();
	
	@Override
	public <T extends Entity> void register(EntityType<T> entityType,
			Function<EntityRenderDispatcher, EntityRenderer<? super T>> rendererConstructor) {
		RenderingRegistry.registerEntityRenderingHandler(entityType, manager -> rendererConstructor.apply(manager));
	}
}
