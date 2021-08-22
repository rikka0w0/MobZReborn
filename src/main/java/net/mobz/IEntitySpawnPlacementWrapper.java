package net.mobz;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.levelgen.Heightmap;

@FunctionalInterface
public interface IEntitySpawnPlacementWrapper {
	<T extends Mob> void register(EntityType<T> entityType, SpawnPlacements.Type placeType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> placementPredicate);
}
