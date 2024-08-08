package net.mobz.fabric.biome;

import java.util.List;
import java.util.function.Function;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.resources.ResourceLocation;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.EitherCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AddSpawnsBiomeModifier(HolderSet<Biome> biomes, List<SpawnerData> spawners) implements BiomeModifier {
	public final static ResourceLocation TYPE_NAME = new ResourceLocation("fabric", "add_spawns");

	public final static MapCodec<AddSpawnsBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddSpawnsBiomeModifier::biomes),
			new EitherCodec<>(SpawnerData.CODEC.listOf(), SpawnerData.CODEC)
				.xmap(either -> either.map(Function.identity(), List::of),
						list -> list.size() == 1 ? Either.right(list.get(0)): Either.left(list)
				).fieldOf("spawners").forGetter(AddSpawnsBiomeModifier::spawners)
			).apply(builder, AddSpawnsBiomeModifier::new)
		);

	@Override
	public void apply(BiomeModification biomeModification) {
		biomeModification.add(ModificationPhase.ADDITIONS,
				(selectionContext) -> this.biomes.contains(selectionContext.getBiomeRegistryEntry()),
				(selectionContext, modificationContext) -> {
					for (SpawnerData spawnData : this.spawners) {
						modificationContext.getSpawnSettings().addSpawn(spawnData.type.getCategory(), spawnData);
					}
				}
			);
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
