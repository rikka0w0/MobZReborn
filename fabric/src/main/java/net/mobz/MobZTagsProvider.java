package net.mobz;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;

// To walkaround https://github.com/FabricMC/fabric-api/issues/5431
public abstract class MobZTagsProvider<T> extends FabricTagsProvider<T> {
	protected MobZTagsProvider(PackOutput packOutput,
			ResourceKey<? extends Registry<T>> registryKey,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super((FabricPackOutput) packOutput, registryKey, lookupProvider);
	}
}
