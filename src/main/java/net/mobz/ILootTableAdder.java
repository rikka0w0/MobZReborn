package net.mobz;

import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.StandaloneLootEntry;
import net.minecraft.util.ResourceLocation;

public interface ILootTableAdder {	
	void addRoll(ResourceLocation[] lootTableIDs, IRandomRange range, StandaloneLootEntry.Builder<?> entryBuilder);
	
	default void addRoll(ResourceLocation lootTableID, IRandomRange range, StandaloneLootEntry.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation[] {lootTableID}, range, entryBuilder);
	}

	default void addRoll(String lootTableID, IRandomRange range, StandaloneLootEntry.Builder<?> entryBuilder) {
		addRoll(new ResourceLocation(lootTableID), range, entryBuilder);
	}
}
