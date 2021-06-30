package net.mobz;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.MobSpawnInfo;

public interface IMobSpawnAdder {
	void register(IBiomeFilter biomeFilter, EntityClassification spawnGroup, MobSpawnInfo.Spawners spawns);
}
