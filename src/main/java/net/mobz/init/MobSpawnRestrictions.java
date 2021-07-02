package net.mobz.init;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.mobz.IEntitySpawnPlacementWrapper;

public class MobSpawnRestrictions {
	public static void applyAll(IEntitySpawnPlacementWrapper w) {
		w.register(MobZEntities.TANK, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.FAST, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ARMORED, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.BOSS, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.CREEP, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.CRIP, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDERZOMBIE, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SPI, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SPO, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.PIG, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.LAVAGOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.ICEGOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.SKELI1, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI2, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI3, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI4, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ARCHERENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.ARCHER2ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.BIGBOSSENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHTENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT2ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT3ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT4ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.KNIGHT5ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGE2ENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGEENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.FULLIRONENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SMALLZOMBIE, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.FROSTENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.DOG, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.STONEGOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.ILLUSIONER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.DWARFENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SPISMALL, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.BLACKBEAR, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.BROWNBEAR, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.GCHICKEN, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR2, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR3, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::checkAnimalSpawnRules);
		w.register(MobZEntities.FRIEND, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.WITHENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.WITHENDER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.SLIMO, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.TSPIDER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.PILLAGERBOSS, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.BABYRAVAGERENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKING, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTNORMAL, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL2, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDVEXENTITY, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
		w.register(MobZEntities.METALGOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AgeableEntity::checkMobSpawnRules);
		w.register(MobZEntities.SCREEPER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::checkMonsterSpawnRules);
	}
}
