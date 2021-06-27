package net.mobz.client;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public interface IEntityRendererRegisterWrapper {
	<T extends Entity> void register(EntityType<T> entityType, 
			Function<EntityRendererManager, EntityRenderer<? super T>> rendererConstructor);
}
