package net.mobz.client;

import java.util.function.BiFunction;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface IEntityRendererRegisterWrapper {
	<T extends Entity> void register(EntityType<? extends T> entityType,
			EntityRendererProvider<T> rendererConstructor);

	default <T extends Entity, S extends EntityRenderState> void register(EntityType<? extends T> entityType,
			BiFunction<EntityRendererProvider.Context, Identifier, EntityRenderer<T, S>> constructor) {
		Identifier entityName = EntityType.getKey(entityType);
		String path = "textures/entity/" + entityName.getPath() + ".png";
		Identifier resLoc = Identifier.fromNamespaceAndPath(entityName.getNamespace(), path);
		this.register(entityType, (context) -> constructor.apply(context, resLoc));
	}
}
