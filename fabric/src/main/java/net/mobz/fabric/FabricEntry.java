package net.mobz.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.mobz.MobZ;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;

public class FabricEntry implements ModInitializer {
	public static void addRoll(ResourceLocation[] lootTableIDs, NumberProvider range, Builder<?> entryBuilder) {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
        	for (ResourceLocation lootTableID: lootTableIDs) {
                if (id.equals(lootTableID)) {
                	tableBuilder.withPool(LootPool.lootPool().setRolls(range).add(entryBuilder));
                }
        	}
		});
	}

	@Override
	public void onInitialize() {
		MobZ.platform = new FabricRegistryWrapper();

		// Config
		MobZ.initConfig();

		// Register items, blocks, entities
		MobZ.invokeStaticFields();
		MobSpawnRestrictions.applyAll(SpawnPlacements::register);

		// Inject loot tables
		LootTableModifier.loadAll(FabricEntry::addRoll);

		// Add spawns
		AddSpawnsBiomeModifier.FOLDER_NAME.getClass();
	}
}
