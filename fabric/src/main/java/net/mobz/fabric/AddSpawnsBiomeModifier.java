package net.mobz.fabric;

import java.util.List;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.mobz.MobZ;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AddSpawnsBiomeModifier(HolderSet<Biome> biomes, List<SpawnerData> spawners) {
	public final static ResourceLocation resloc = new ResourceLocation(MobZ.MODID, "biome_modifier");
	private final static Gson GSON = new Gson();

	private final static Codec<AddSpawnsBiomeModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddSpawnsBiomeModifier::biomes),
			new ExtraCodecs.EitherCodec<>(SpawnerData.CODEC.listOf(), SpawnerData.CODEC)
				.xmap(either -> either.map(Function.identity(), List::of),
						list -> list.size() == 1 ? Either.right(list.get(0)): Either.left(list)
				).fieldOf("spawners").forGetter(AddSpawnsBiomeModifier::spawners)
			).apply(builder, AddSpawnsBiomeModifier::new)
		);

	private static List<JsonObject> biomeModifiers = new LinkedList<>();

	public static void onDataPackReload(ResourceManager resourceManager) throws IOException {
		Map<ResourceLocation, Resource> resMap = resourceManager.listResources("forge/biome_modifier",
				path -> path.getPath().endsWith(".json"));

		for (Map.Entry<ResourceLocation, Resource> entry : resMap.entrySet()) {
			JsonObject jsonRoot = GSON.fromJson(entry.getValue().openAsReader(), JsonObject.class);
			if (!jsonRoot.has("type") || jsonRoot.get("type").getAsString().equalsIgnoreCase("forge:add_spawns")) {
				biomeModifiers.add(jsonRoot);
			}
		}
		return;
	}

	public static void parseBiomeModifiers(RegistryAccess registryAccess) {
		RegistryOps<JsonElement> dynamicOps = RegistryOps.create(JsonOps.INSTANCE, registryAccess);
		BiomeModification bm = BiomeModifications.create(resloc);

		for (JsonObject jsonRoot: biomeModifiers) {
			DataResult<AddSpawnsBiomeModifier> parseResult = CODEC.parse(dynamicOps, jsonRoot);
			try {
				final AddSpawnsBiomeModifier result = parseResult.getOrThrow(true, System.out::println);

				bm.add(
					ModificationPhase.ADDITIONS,
					(selectionContext) -> result.biomes.contains(selectionContext.getBiomeRegistryEntry()),
					(selectionContext, modificationContext) -> {
						for (SpawnerData spawnData: result.spawners) {
							modificationContext.getSpawnSettings().addSpawn(spawnData.type.getCategory(), spawnData);
						}
					}
				);
			} catch (RuntimeException e) {

			}
		}
	}
}
