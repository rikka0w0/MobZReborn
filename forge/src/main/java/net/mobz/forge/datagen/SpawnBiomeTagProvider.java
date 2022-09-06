package net.mobz.forge.datagen;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mobz.MobZ;

public class SpawnBiomeTagProvider extends TagsProvider<Biome> {
	public SpawnBiomeTagProvider(DataGenerator dataGenerator, Registry<Biome> registry,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, registry, MobZ.MODID, existingFileHelper);;
	}

	@Override
	protected void addTags() {
		this.tag(MobZ.SPAWN_NORMAL_TAG).add(Biomes.PLAINS).add(Biomes.SUNFLOWER_PLAINS).add(Biomes.DESERT)
				.add(Biomes.SWAMP).add(Biomes.MANGROVE_SWAMP).add(Biomes.FOREST).add(Biomes.FLOWER_FOREST)
				.add(Biomes.BIRCH_FOREST).add(Biomes.DARK_FOREST).add(Biomes.OLD_GROWTH_BIRCH_FOREST)
				.add(Biomes.OLD_GROWTH_PINE_TAIGA).add(Biomes.OLD_GROWTH_SPRUCE_TAIGA).add(Biomes.TAIGA)
				.add(Biomes.SAVANNA).add(Biomes.SAVANNA_PLATEAU).add(Biomes.WINDSWEPT_HILLS)
				.add(Biomes.WINDSWEPT_GRAVELLY_HILLS).add(Biomes.WINDSWEPT_FOREST).add(Biomes.WINDSWEPT_SAVANNA)
				.add(Biomes.JUNGLE).add(Biomes.SPARSE_JUNGLE).add(Biomes.BAMBOO_JUNGLE).add(Biomes.MEADOW)
				.add(Biomes.RIVER).add(Biomes.BEACH).add(Biomes.STONY_SHORE).add(Biomes.DRIPSTONE_CAVES);
		this.tag(MobZ.SPAWN_ICY_TAG).add(Biomes.SNOWY_PLAINS).add(Biomes.ICE_SPIKES).add(Biomes.SNOWY_TAIGA)
				.add(Biomes.GROVE).add(Biomes.SNOWY_SLOPES).add(Biomes.FROZEN_PEAKS).add(Biomes.JAGGED_PEAKS);
	}

}
