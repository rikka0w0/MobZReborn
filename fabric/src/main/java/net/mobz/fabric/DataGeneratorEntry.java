package net.mobz.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack.Factory;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;

import net.mobz.data.ItemModelDataProvider;
import net.mobz.data.SpawnBiomeTagProvider;
import net.mobz.fabric.biome.BiomeModifierRegistry;
import net.mobz.fabric.data.BiomeModifierProvider;

public class DataGeneratorEntry implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

		Factory<? extends DataProvider> itemModels = (a) -> new ItemModelDataProvider(a, registryAccess.registryOrThrow(Registries.ITEM), resLoc->true);
		pack.addProvider(itemModels);

		pack.addProvider(SpawnBiomeTagProvider::new);

		pack.addProvider(BiomeModifierProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(BiomeModifierRegistry.REGISTRY_KEY, BiomeModifierProvider::biomeModifierPopulator);
	}
}
