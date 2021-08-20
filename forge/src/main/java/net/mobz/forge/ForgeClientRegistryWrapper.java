package net.mobz.forge;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.mobz.client.IEntityRendererRegisterWrapper;

public class ForgeClientRegistryWrapper implements IEntityRendererRegisterWrapper {
	public final static IEntityRendererRegisterWrapper instance = new ForgeClientRegistryWrapper();

	@Override
	public <T extends Entity> void register(EntityType<? extends T> entityType,
			EntityRendererProvider<T> rendererConstructor) {
		EntityRenderers.register(entityType, rendererConstructor);
	}
}
