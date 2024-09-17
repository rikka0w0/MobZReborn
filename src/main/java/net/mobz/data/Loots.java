package net.mobz.data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class Loots {
	public static LootTableProvider all(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		return new LootTableProvider(packOutput, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(RewardLoot::new, LootContextParamSets.GIFT),
				new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(EntityLoot::new, LootContextParamSets.ENTITY)
			));
	}
}
