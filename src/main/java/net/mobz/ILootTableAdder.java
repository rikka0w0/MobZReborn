package net.mobz;

import net.minecraft.world.level.storage.loot.RandomIntGenerator;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.resources.ResourceLocation;

public interface ILootTableAdder {	
	void addRoll(ResourceLocation[] lootTableIDs, RandomIntGenerator range, LootPoolSingletonContainer.Builder<?> entryBuilder);
	
	default void addRoll(ResourceLocation lootTableID, RandomIntGenerator range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation[] {lootTableID}, range, entryBuilder);
	}

	default void addRoll(String lootTableID, RandomIntGenerator range, LootPoolSingletonContainer.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation(lootTableID), range, entryBuilder);
	}
}
