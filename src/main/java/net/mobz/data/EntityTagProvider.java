package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.entity.EntityType;

import net.mobz.init.MobZEntities;
import net.mobz.tags.MobZEntityTags;

public class EntityTagProvider extends IntrinsicHolderTagsProvider<EntityType<?>> {
	public EntityTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ENTITY_TYPE, lookupProvider, entityType -> entityType.builtInRegistryHolder().key());
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(MobZEntityTags.TOAD_TARGET_TAG).add(
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
