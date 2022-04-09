package net.mobz.fabric;

import com.google.common.base.Preconditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.mobz.IBiomeFilter;
import net.mobz.MobZ;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobSpawns;

@SuppressWarnings("deprecation")
public class FabricEntry implements ModInitializer {
	public static void addRoll(ResourceLocation[] lootTableIDs, NumberProvider range, Builder<?> entryBuilder) {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
        	for (ResourceLocation lootTableID: lootTableIDs) {
                if (id.equals(lootTableID)) {
                    supplier.withPool(FabricLootPoolBuilder.builder().setRolls(range).add(entryBuilder));
                }
        	}
        });
	}

	public static void addMobSpawn(IBiomeFilter biomeFilter, MobCategory spawnGroup, SpawnerData spawns) {
		// See constructor of SpawnSettings.SpawnEntry for context
		Preconditions.checkArgument(spawns.type.getCategory() != MobCategory.MISC,
				"Cannot add spawns for entities with spawnGroup=MISC since they'd be replaced by pigs.");

		ResourceLocation id = Registry.ENTITY_TYPE.getKey(spawns.type);
		Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultKey(), "Unregistered entity type: %s", spawns.type);

		BiomeModifications.create(id).add(ModificationPhase.ADDITIONS,
				context -> biomeFilter.accept(Biome.getBiomeCategory(context.getBiomeRegistryEntry())),
				context -> context.getSpawnSettings().addSpawn(spawnGroup, spawns));
	}

	@Override
	public void onInitialize() {
		// Config
		MobZ.initConfig();

		// Register items, blocks, entities
		MobZ.invokeStaticFields();
		MobSpawnRestrictions.applyAll(SpawnRestrictionAccessor::callRegister);

		// Inject loot tables
		LootTableModifier.loadAll(FabricEntry::addRoll);
		// Add spawns
		MobSpawns.addMobSpawns(FabricEntry::addMobSpawn);
	}
}
