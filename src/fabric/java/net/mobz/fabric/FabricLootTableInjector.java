package net.mobz.fabric;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.util.ResourceLocation;

import net.mobz.init.LootTableModifier;

public class FabricLootTableInjector {
	public static void injectLootTableEntries() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
        	for (ResourceLocation lootTableID: lootTableIDs) {
                if (id.equals(lootTableID)) {
                    supplier.withPool(FabricLootPoolBuilder.builder().setRolls(range).add(entryBuilder));
                }
        	}
        });

        LootTableModifier.loadAll(lootTableAdder);
	}
}
