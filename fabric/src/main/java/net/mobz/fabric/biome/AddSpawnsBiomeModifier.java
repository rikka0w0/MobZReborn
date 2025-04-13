package net.mobz.fabric.biome;

import java.util.function.Function;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AddSpawnsBiomeModifier(HolderSet<Biome> biomes, WeightedList<SpawnerData> spawners) implements BiomeModifier {
	public final static ResourceLocation TYPE_NAME = ResourceLocation.tryBuild("fabric", "add_spawns");

	public final static MapCodec<AddSpawnsBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddSpawnsBiomeModifier::biomes),
			Codec.either(WeightedList.codec(SpawnerData.CODEC), Weighted.codec(SpawnerData.CODEC))
				.xmap(either -> either.map(Function.identity(), WeightedList::<SpawnerData>of),
						list -> list.unwrap().size() == 1 ? Either.right(list.unwrap().get(0)) : Either.left(list)
				).fieldOf("spawners").forGetter(AddSpawnsBiomeModifier::spawners)
			).apply(builder, AddSpawnsBiomeModifier::new)
		);

	@Override
	public void apply(BiomeModification biomeModification) {
		biomeModification.add(ModificationPhase.ADDITIONS,
				(selectionContext) -> this.biomes.contains(selectionContext.getBiomeRegistryEntry()),
				(selectionContext, modificationContext) -> {
					for (Weighted<SpawnerData> spawner : this.spawners.unwrap()) {
						EntityType<?> type = spawner.value().type();
						modificationContext.getSpawnSettings().addSpawn(type.getCategory(), spawner.value(), spawner.weight());
					}
				}
			);
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
