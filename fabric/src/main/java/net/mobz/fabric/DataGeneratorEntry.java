package net.mobz.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack.Factory;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;

import net.mobz.data.ModelDataProvider;
import net.mobz.data.Recipes;
import net.mobz.data.Advancements;
import net.mobz.data.Loots;
import net.mobz.data.MineableTagProvider;
import net.mobz.data.SpawnBiomeTagProvider;
import net.mobz.fabric.biome.BiomeModifierRegistry;
import net.mobz.fabric.data.BiomeModifierProvider;

public class DataGeneratorEntry implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

		// Data: Mob spawns
		pack.addProvider(BiomeModifierProvider::new);

		// Data: Biome tags for spawns
		pack.addProvider(SpawnBiomeTagProvider::new);

		// Data: Mineable tags
		pack.addProvider(MineableTagProvider::new);

		// Resource: Models and blockstates
		Factory<? extends DataProvider> itemModels = (output) ->
			new ModelDataProvider(output, registryAccess.registryOrThrow(Registries.ITEM), resLoc->true);
		pack.addProvider(itemModels);

		// Data: LootTable
		pack.addProvider((fabricDataOutput, registriesFuture) -> Loots.all(fabricDataOutput, registriesFuture));

		// Data: Recipes
		pack.addProvider((fabricDataOutput, registriesFuture) -> new Recipes(fabricDataOutput, registriesFuture));

		// Data: Advancements
		pack.addProvider((fabricDataOutput, registriesFuture) -> Advancements.all(fabricDataOutput, registriesFuture));
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(BiomeModifierRegistry.REGISTRY_KEY, BiomeModifierProvider::biomeModifierPopulator);
	}
}
