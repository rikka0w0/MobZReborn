package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import net.mobz.init.MobZEntities;
import net.mobz.tags.MobZEntityTags;

public class EntityTagProvider extends TagsProvider<EntityType<?>> {
	public EntityTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ENTITY_TYPE, lookupProvider);
	}

	public TagsProvider.TagAppender<EntityType<?>> tag(TagKey<EntityType<?>> tagKey,
			EntityType<?>... entityTypes) {
		TagsProvider.TagAppender<EntityType<?>> appender = this.tag(tagKey);
		for (EntityType<?> entityType: entityTypes) {
			appender.add(entityType.builtInRegistryHolder().key());
		}
		return appender;
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(MobZEntityTags.TOAD_TARGET_TAG,
			EntityType.SPIDER,
			EntityType.CAVE_SPIDER,
			EntityType.SILVERFISH,
			MobZEntities.TINY_SPIDER.get(),
			MobZEntities.BLUE_SPIDER.get(),
			MobZEntities.SMALL_SPIDER.get(),
			MobZEntities.PURPLE_SPIDER.get(),
			MobZEntities.WASP.get()
		);
	}
}
