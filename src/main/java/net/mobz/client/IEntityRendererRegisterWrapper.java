package net.mobz.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface IEntityRendererRegisterWrapper {
	<T extends Entity> void register(EntityType<? extends T> entityType,
			EntityRendererProvider<T> rendererConstructor);
}
