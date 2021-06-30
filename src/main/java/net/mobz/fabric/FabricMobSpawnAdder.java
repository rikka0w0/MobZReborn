package net.mobz.fabric;

import com.google.common.base.Preconditions;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.mobz.IBiomeFilter;
import net.mobz.IMobSpawnAdder;

public class FabricMobSpawnAdder implements IMobSpawnAdder {
	public final static IMobSpawnAdder instance = new FabricMobSpawnAdder();
	
	@Override
	public void register(IBiomeFilter biomeFilter, EntityClassification spawnGroup, Spawners spawns) {
		// See constructor of SpawnSettings.SpawnEntry for context
		Preconditions.checkArgument(spawns.type.getCategory() != EntityClassification.MISC,
				"Cannot add spawns for entities with spawnGroup=MISC since they'd be replaced by pigs.");

		// We need the entity type to be registered or we cannot deduce an ID otherwisea
		ResourceLocation id = Registry.ENTITY_TYPE.getKey(spawns.type);
		Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultKey(), "Unregistered entity type: %s", spawns.type);

		/*
		BiomeModifications.create(id).add(
				ModificationPhase.ADDITIONS, 
				context -> biomeFilter.accept(context.getBiome().getBiomeCategory()), 
				context -> context.getSpawnSettings().addSpawn(spawnGroup, spawns)
		);
		*/
	}
}
