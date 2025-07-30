package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import net.mobz.MobZ;
import net.mobz.tags.MobZBiomeTags;

public class SpawnBiomeTagProvider extends KeyTagProvider<Biome> {
	public SpawnBiomeTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.BIOME, lookupProvider);
	}

	@Override
	protected void addTags(Provider lookupProvider) {
		this.tag(MobZBiomeTags.SPAWN_NORMAL_TAG).add(Biomes.PLAINS).add(Biomes.SUNFLOWER_PLAINS).add(Biomes.DESERT)
				.add(Biomes.SWAMP).add(Biomes.MANGROVE_SWAMP).add(Biomes.FOREST).add(Biomes.FLOWER_FOREST)
				.add(Biomes.BIRCH_FOREST).add(Biomes.DARK_FOREST).add(Biomes.OLD_GROWTH_BIRCH_FOREST)
				.add(Biomes.OLD_GROWTH_PINE_TAIGA).add(Biomes.OLD_GROWTH_SPRUCE_TAIGA).add(Biomes.TAIGA)
				.add(Biomes.SAVANNA).add(Biomes.SAVANNA_PLATEAU).add(Biomes.WINDSWEPT_HILLS)
				.add(Biomes.WINDSWEPT_GRAVELLY_HILLS).add(Biomes.WINDSWEPT_FOREST).add(Biomes.WINDSWEPT_SAVANNA)
				.add(Biomes.JUNGLE).add(Biomes.SPARSE_JUNGLE).add(Biomes.BAMBOO_JUNGLE).add(Biomes.MEADOW)
				.add(Biomes.RIVER).add(Biomes.BEACH).add(Biomes.STONY_SHORE).add(Biomes.DRIPSTONE_CAVES);
		this.tag(MobZBiomeTags.SPAWN_ICY_TAG).add(Biomes.SNOWY_PLAINS).add(Biomes.ICE_SPIKES).add(Biomes.SNOWY_TAIGA)
				.add(Biomes.GROVE).add(Biomes.SNOWY_SLOPES).add(Biomes.FROZEN_PEAKS).add(Biomes.JAGGED_PEAKS);
	}

    @Override
    public String getName() {
        return "Tags for " + this.registryKey.location() + " mod id " + MobZ.MODID;
    }
}
