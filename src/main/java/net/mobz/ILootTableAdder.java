package net.mobz;

import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.List;

import net.minecraft.resources.ResourceLocation;

@FunctionalInterface
public interface ILootTableAdder {
	void addRoll(List<ResourceLocation> lootTableIDs, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder);

	default void addRoll(ResourceLocation lootTableID, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(List.of(lootTableID), range, entryBuilder);
	}

	default void addRoll(String lootTableID, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation(lootTableID), range, entryBuilder);
	}
}
