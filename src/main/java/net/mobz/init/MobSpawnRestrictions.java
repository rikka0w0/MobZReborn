package net.mobz.init;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.mobz.IEntitySpawnPlacementWrapper;

public class MobSpawnRestrictions {
	public static void applyAll(IEntitySpawnPlacementWrapper w) {
		w.register(MobZEntities.TANK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FAST, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ARMORED, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BOSS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.CREEP, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.CRIP, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDERZOMBIE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPI, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPO, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PIG, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.LAVAGOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ICEGOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.SKELI1, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI2, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI3, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI4, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ARCHERENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.ARCHER2ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.BIGBOSSENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHTENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT2ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT3ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT4ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.KNIGHT5ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGE2ENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGEENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FULLIRONENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SMALLZOMBIE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FROSTENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.DOG, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.STONEGOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ILLUSIONER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.DWARFENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPISMALL, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BLACKBEAR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BROWNBEAR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.GCHICKEN, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR2, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR3, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.FRIEND, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.WITHENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.WITHENDER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SLIMO, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.TSPIDER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PILLAGERBOSS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BABYRAVAGERENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKING, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTNORMAL, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL2, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDVEXENTITY, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.METALGOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.SCREEPER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);

		w.register(MobZEntities.TOAD, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
	}
}
