package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.mobz.IBiomeFilter;
import net.mobz.IMobSpawnAdder;

public class ForgeMobSpawnAdder implements IMobSpawnAdder {
	private Set<Triple<IBiomeFilter, MobCategory, SpawnerData>> cache = new HashSet<>();

	public void addMobSpawns(BiomeCategory category, MobSpawnSettingsBuilder spawns) {
		cache.forEach((t)->{
			if (t.getLeft().accept(category)) {
				spawns.addSpawn(t.getMiddle(), t.getRight());
			}
		});
	}

	@Override
	public void register(IBiomeFilter biomeFilter, MobCategory spawnGroup, SpawnerData spawns) {
		cache.add(Triple.of(biomeFilter, spawnGroup, spawns));
	}
}
