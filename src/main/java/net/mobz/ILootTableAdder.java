package net.mobz;

import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.resources.ResourceLocation;

@FunctionalInterface
public interface ILootTableAdder {
	void addRoll(ResourceLocation[] lootTableIDs, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder);

	default void addRoll(ResourceLocation lootTableID, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation[] {lootTableID}, range, entryBuilder);
	}

	default void addRoll(String lootTableID, NumberProvider range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation(lootTableID), range, entryBuilder);
	}
}
