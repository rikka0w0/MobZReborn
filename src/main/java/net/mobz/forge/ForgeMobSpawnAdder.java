package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.mobz.IBiomeFilter;
import net.mobz.IMobSpawnAdder;

public class ForgeMobSpawnAdder implements IMobSpawnAdder {
	private Set<Triple<IBiomeFilter, EntityClassification, Spawners>> cache = new HashSet<>();

	public void addMobSpawns(Category category, MobSpawnInfoBuilder spawns) {
		cache.forEach((t)->{
			if (t.getLeft().accept(category)) {
				spawns.addSpawn(t.getMiddle(), t.getRight());
			}
		});
	}

	@Override
	public void register(IBiomeFilter biomeFilter, EntityClassification spawnGroup, Spawners spawns) {
		cache.add(Triple.of(biomeFilter, spawnGroup, spawns));
	}
}
