package net.mobz;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;

public abstract class MobZTagsProvider<T> extends TagsProvider<T> {
	protected MobZTagsProvider(PackOutput packOutput,
			ResourceKey<? extends Registry<T>> registryKey,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, registryKey, lookupProvider);
	}
}
