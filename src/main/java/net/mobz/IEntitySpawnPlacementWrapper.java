package net.mobz;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.gen.Heightmap;

public interface IEntitySpawnPlacementWrapper {
	<T extends MobEntity> void register(EntityType<T> entityType, EntitySpawnPlacementRegistry.PlacementType placeType, Heightmap.Type heightMapType, EntitySpawnPlacementRegistry.IPlacementPredicate<T> placementPredicate);
}
