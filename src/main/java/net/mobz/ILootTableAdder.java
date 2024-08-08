package net.mobz;

import java.util.List;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

@FunctionalInterface
public interface ILootTableAdder {
	void addRoll(List<ResourceKey<LootTable>> lootTableIDs, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder);

	default void addRoll(ResourceKey<LootTable> lootTableID, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(List.of(lootTableID), range, entryBuilder);
	}
}
