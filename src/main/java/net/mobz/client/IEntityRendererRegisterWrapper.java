package net.mobz.client;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface IEntityRendererRegisterWrapper {
	<T extends Entity> void register(EntityType<T> entityType, 
			Function<EntityRenderDispatcher, EntityRenderer<? super T>> rendererConstructor);
}
