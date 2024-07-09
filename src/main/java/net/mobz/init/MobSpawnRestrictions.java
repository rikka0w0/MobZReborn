package net.mobz.init;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.mobz.IEntitySpawnPlacementWrapper;

public class MobSpawnRestrictions {
	public static void applyAll(IEntitySpawnPlacementWrapper w) {
		w.register(MobZEntities.TANK.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FAST.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ARMORED.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BOSS.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.CREEP.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.CRIP.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDERZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPI.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPO.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PIG.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.LAVAGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ICEGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.SKELI1.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI2.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI3.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SKELI4.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BOWMAN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.ARCHER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.BIGBOSSENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHTENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT2ENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT3ENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FIORA.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.KNIGHT5ENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGE2ENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.MAGEENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FULLIRONENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SMALLZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FROSTENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.DOG.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.STONEGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ILLUSIONER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.DWARFENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPISMALL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BLACKBEAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BROWNBEAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.GCHICKEN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR2.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR3.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.KATHERINE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.WITHENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.WITHENDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SLIMO.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.TSPIDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PILLAGERBOSS.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BABYRAVAGERENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKING.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTNORMAL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDKNIGHTSPECIAL2.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ISLANDVEXENTITY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.METALGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.SCREEPER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);

		w.register(MobZEntities.TOAD.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
	}
}
