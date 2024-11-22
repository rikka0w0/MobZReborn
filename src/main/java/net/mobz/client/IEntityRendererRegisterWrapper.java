package net.mobz.client;

import java.util.function.BiFunction;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface IEntityRendererRegisterWrapper {
	<T extends Entity> void register(EntityType<? extends T> entityType,
			EntityRendererProvider<T> rendererConstructor);

	default <T extends Entity, S extends EntityRenderState> void register(EntityType<? extends T> entityType,
			BiFunction<EntityRendererProvider.Context, ResourceLocation, EntityRenderer<T, S>> constructor) {
		ResourceLocation entityName = EntityType.getKey(entityType);
		String path = "textures/entity/" + entityName.getPath() + ".png";
		ResourceLocation resLoc = ResourceLocation.fromNamespaceAndPath(entityName.getNamespace(), path);
		this.register(entityType, (context) -> constructor.apply(context, resLoc));
	}
}
