package net.mobz.fabric.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.tuple.Pair;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

import net.mobz.MobZ;
import net.mobz.fabric.biome.AddSpawnsBiomeModifier;
import net.mobz.fabric.biome.BiomeModifier;
import net.mobz.fabric.biome.BiomeModifierRegistry;
import net.mobz.init.MobSpawns;

public class BiomeModifierProvider extends FabricDynamicRegistryProvider {
	public static RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(BiomeModifierRegistry.REGISTRY_KEY, BiomeModifierProvider::biomeModifierPopulator);

	public BiomeModifierProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void biomeModifierPopulator(BootstrapContext<BiomeModifier> context) {
		Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> rawMap = new HashMap<>();
		MobSpawns.collectAll(rawMap);

		for (Entry<ResourceLocation, Pair<TagKey<Biome>, List<SpawnerData>>> entry : rawMap.entrySet()) {
			ResourceLocation resloc = entry.getKey();
			ResourceKey<BiomeModifier> resKey = ResourceKey.create(BiomeModifierRegistry.REGISTRY_KEY, resloc);
			Pair<TagKey<Biome>, List<SpawnerData>> payload = entry.getValue();
			TagKey<Biome> toBiomeWithTag = payload.getLeft();
			HolderGetter<Biome> biomeReg = context.lookup(toBiomeWithTag.registry());
			HolderSet.Named<Biome> biomeHolderSet = biomeReg.getOrThrow(toBiomeWithTag);
			AddSpawnsBiomeModifier modifier = new AddSpawnsBiomeModifier(biomeHolderSet, payload.getRight());
			context.register(resKey, modifier);
		}
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(BiomeModifierRegistry.REGISTRY_KEY));
	}

	@Override
	public String getName() {
		return "BiomeModifiers for " + BiomeModifierRegistry.REGISTRY_KEY.location() + " mod id "  + MobZ.MODID;
	}
}
