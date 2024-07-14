package net.mobz.fabric;

import java.util.List;
import java.util.function.Function;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AddSpawnsBiomeModifier(HolderSet<Biome> biomes, List<SpawnerData> spawners) {
	public final static ResourceLocation REGISTRY_NAME = new ResourceLocation("fabric", "biome_modifier");
	public final static String FOLDER_NAME = REGISTRY_NAME.getNamespace() + "/" + REGISTRY_NAME.getPath();
	public final static ResourceKey<Registry<AddSpawnsBiomeModifier>> REGISTRY_KET = ResourceKey.createRegistryKey(REGISTRY_NAME);
	public final static String TYPE_NAME = "fabric:add_spawns";

	public final static Codec<AddSpawnsBiomeModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddSpawnsBiomeModifier::biomes),
			new ExtraCodecs.EitherCodec<>(SpawnerData.CODEC.listOf(), SpawnerData.CODEC)
				.xmap(either -> either.map(Function.identity(), List::of),
						list -> list.size() == 1 ? Either.right(list.get(0)): Either.left(list)
				).fieldOf("spawners").forGetter(AddSpawnsBiomeModifier::spawners)
			).apply(builder, AddSpawnsBiomeModifier::new)
		);

	static {
		DynamicRegistries.register(REGISTRY_KET, CODEC);
	}

	public static void parseBiomeModifiers(RegistryAccess registryAccess) {
		RegistryLookup<AddSpawnsBiomeModifier> bmRegistry = registryAccess.lookupOrThrow(REGISTRY_KET);
		BiomeModification bm = BiomeModifications.create(REGISTRY_NAME);
		bmRegistry.listElements().map(Holder.Reference::value).forEach(result -> {
			bm.add(ModificationPhase.ADDITIONS,
				(selectionContext) -> result.biomes.contains(selectionContext.getBiomeRegistryEntry()),
				(selectionContext, modificationContext) -> {
					for (SpawnerData spawnData : result.spawners) {
						modificationContext.getSpawnSettings().addSpawn(spawnData.type.getCategory(), spawnData);
					}
				});
		});
	}

	public JsonElement encode(DynamicOps<JsonElement> dynamicOps) {
		JsonObject rootJSON = new JsonObject();
		rootJSON.addProperty("type", TYPE_NAME);
		return AddSpawnsBiomeModifier.CODEC.encode(this, dynamicOps, rootJSON).result().get();
	}
}
