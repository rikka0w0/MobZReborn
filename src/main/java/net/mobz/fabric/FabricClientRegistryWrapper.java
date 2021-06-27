package net.mobz.fabric;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.mobz.client.IEntityRendererRegisterWrapper;

public class FabricClientRegistryWrapper implements IEntityRendererRegisterWrapper {
	@Override
	public <T extends Entity> void register(EntityType<T> entityType,
			Function<EntityRendererManager, EntityRenderer<? super T>> rendererConstructor) {
		// EntityRendererRegistry.INSTANCE.register(entityType, (dispatcher, context) -> rendererConstructor.accept(dispatcher));
	}
}
