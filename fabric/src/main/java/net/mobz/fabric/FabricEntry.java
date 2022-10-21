package net.mobz.fabric;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
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
		ResourceManagerHelper.get(PackType.SERVER_DATA)
				.registerReloadListener(new SimpleSynchronousResourceReloadListener() {
					@Override
					public ResourceLocation getFabricId() {
						return AddSpawnsBiomeModifier.resloc;
					}

					@Override
					public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier,
							ResourceManager resourceManager, ProfilerFiller serverProfiler,
							ProfilerFiller clientProfiler, Executor serverExecutor, Executor clientExecutor) {
						return preparationBarrier.wait(null).thenRunAsync(() -> {
							serverProfiler.startTick();
							serverProfiler.push("Reloading MobZ spawns");
							try {
								AddSpawnsBiomeModifier.onDataPackReload(resourceManager);
							} catch (IOException e) {
								e.printStackTrace();
							}
							serverProfiler.pop();
							serverProfiler.endTick();
						});
					}

					@Override
					public void onResourceManagerReload(ResourceManager resourceManager) {
					}
				});
	}
}
