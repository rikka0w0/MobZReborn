package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;

import net.mobz.MobZTagsProvider;
import net.mobz.init.MobZEntities;
import net.mobz.tags.MobZEntityTags;

public class EntityTagProvider extends MobZTagsProvider<EntityType<?>> {
	public EntityTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ENTITY_TYPE, lookupProvider);
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(MobZEntityTags.TOAD_TARGET_TAG).add(
			key(EntityTypes.SPIDER),
			key(EntityTypes.CAVE_SPIDER),
			key(EntityTypes.SILVERFISH),
			key(MobZEntities.TINY_SPIDER.get()),
			key(MobZEntities.BLUE_SPIDER.get()),
			key(MobZEntities.SMALL_SPIDER.get()),
			key(MobZEntities.PURPLE_SPIDER.get()),
			key(MobZEntities.WASP.get())
		);
	}

	private static ResourceKey<EntityType<?>> key(EntityType<?> entityType) {
		return entityType.builtInRegistryHolder().key();
	}
}
