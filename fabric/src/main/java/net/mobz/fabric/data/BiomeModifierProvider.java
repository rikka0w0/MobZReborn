package net.mobz.fabric.data;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

import net.mobz.MobZ;
import net.mobz.fabric.AddSpawnsBiomeModifier;
import net.mobz.init.MobSpawns;

public class BiomeModifierProvider implements DataProvider {
	private final Path outputFolder;
	private final CompletableFuture<HolderLookup.Provider> lookupProvider;

	public static RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(AddSpawnsBiomeModifier.REGISTRY_KET, BiomeModifierProvider::biomeModifierPopulator)
			.add(Registries.BIOME, BiomeData::bootstrap);

	public BiomeModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		this.outputFolder = output.getOutputFolder();
		this.lookupProvider = lookupProvider;
	}

	public static void biomeModifierPopulator(BootstapContext<AddSpawnsBiomeModifier> context) {
		Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> rawMap = new HashMap<>();
		MobSpawns.collectAll(rawMap);

		for (Entry<ResourceLocation, Pair<TagKey<Biome>, List<SpawnerData>>> entry : rawMap.entrySet()) {
			ResourceLocation resloc = entry.getKey();
			ResourceKey<AddSpawnsBiomeModifier> resKey = ResourceKey.create(AddSpawnsBiomeModifier.REGISTRY_KET, resloc);
			Pair<TagKey<Biome>, List<SpawnerData>> payload = entry.getValue();
			TagKey<Biome> toBiomeWithTag = payload.getLeft();
			HolderGetter<Biome> biomeReg = context.lookup(toBiomeWithTag.registry());
			HolderSet.Named<Biome> biomeHolderSet = biomeReg.getOrThrow(toBiomeWithTag);
			AddSpawnsBiomeModifier modifier = new AddSpawnsBiomeModifier(biomeHolderSet, payload.getRight());
			context.register(resKey, modifier);
		}
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cachedOutput) {
		RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

		return lookupProvider.thenApply(provider -> {
			return BUILDER.buildPatch(registryAccess, provider);
		}).thenCompose(provider -> {
			RegistryOps<JsonElement> dynamicOps = RegistryOps.create(JsonOps.INSTANCE, provider);
			RegistryLookup<AddSpawnsBiomeModifier> lookup = provider.lookupOrThrow(AddSpawnsBiomeModifier.REGISTRY_KET);

			return CompletableFuture.allOf(lookup.listElements().map(entry -> {
				Path path = outputFolder.resolve("data/" + MobZ.MODID + "/" + AddSpawnsBiomeModifier.FOLDER_NAME + "/" + entry.key().location().getPath() + ".json");
				AddSpawnsBiomeModifier modifier = entry.value();
				return DataProvider.saveStable(cachedOutput, modifier.encode(dynamicOps), path);
			}).toArray(CompletableFuture[]::new));
		});
	}

	@Override
	public String getName() {
		return "QAQ!!!";
	}
}
