package net.mobz.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack.Factory;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.registries.VanillaRegistries;

import net.mobz.data.ItemModelDataProvider;
import net.mobz.data.SpawnBiomeTagProvider;
import net.mobz.fabric.data.BiomeModifierProvider;

public class DataGeneratorEntry implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

		Factory<? extends DataProvider> itemModels = (a) -> new ItemModelDataProvider(a, registryAccess.registryOrThrow(Registries.ITEM), resLoc->true);
		pack.addProvider(itemModels);

		CompletableFuture<HolderLookup.Provider> completableFuture =
				CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());
		Factory<? extends DataProvider> spawnBiomes = (a) -> new SpawnBiomeTagProvider(a, completableFuture);
		pack.addProvider(spawnBiomes);

		Factory<? extends DataProvider> mobSpawns = (a) -> new BiomeModifierProvider(a, completableFuture);
		pack.addProvider(mobSpawns);
	}
}
