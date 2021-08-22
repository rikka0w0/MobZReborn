package net.mobz;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;

@FunctionalInterface
public interface IMobSpawnAdder {
	void register(IBiomeFilter biomeFilter, MobCategory spawnGroup, MobSpawnSettings.SpawnerData spawns);
}
