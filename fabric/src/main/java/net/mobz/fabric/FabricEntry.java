package net.mobz.fabric;

import java.util.List;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import net.mobz.MobZ;
import net.mobz.config.ClothConfig;
import net.mobz.fabric.biome.BiomeModifierRegistry;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;

public class FabricEntry implements ModInitializer {
	public static void addRoll(List<ResourceKey<LootTable>> lootTableIDs, NumberProvider range, Builder<?> entryBuilder) {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, lookupProvider) -> {
			lootTableIDs.stream().filter(lootTableID -> lootTableID.equals(key)).forEach((lootTableID) -> {
				tableBuilder.withPool(LootPool.lootPool().setRolls(range).add(entryBuilder));
			});
		});
	}

	@Override
	public void onInitialize() {
		MobZ.platform = new FabricRegistryWrapper();

		// Config
		MobZ.configs = ClothConfig.get();

		// Register items, blocks, entities
		MobZ.invokeStaticFields();
		MobSpawnRestrictions.applyAll(SpawnPlacements::register);

		// Inject loot tables
		LootTableModifier.loadAll(FabricEntry::addRoll);

		// Add spawns
		BiomeModifierRegistry.REGISTRY_KEY.getClass();
	}
}
