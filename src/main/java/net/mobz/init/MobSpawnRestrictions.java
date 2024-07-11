package net.mobz.init;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.mobz.IEntitySpawnPlacementWrapper;

public class MobSpawnRestrictions {
	public static void applyAll(IEntitySpawnPlacementWrapper w) {
		w.register(MobZEntities.TANK_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FAST_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ARMORED_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BOSS_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FROST_CREEPER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.COOKIE_CREEPER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BLUE_SPIDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PURPLE_SPIDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PIGMAN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.LAVAGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ICEGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.BOSS_SKELETON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.OVERGROWN_SKELETON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.NETHER_SKELETON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.LOST_SKELETON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BOWMAN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.ARCHER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkAnyLightMonsterSpawnRules);
		w.register(MobZEntities.BIGBOSS.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.TEMPLAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.WARRIOR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ENDER_KNIGHT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FIORA.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.LORD_OF_DARKNESS.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ZOMBIE_MAGE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPIDER_MAGE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.IRON_STEVE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SMALL_ZOMBIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.FROST_BLAZE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.NETHER_WOLF.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.STONEGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.ILLUSIONER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.DWARF.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SMALL_SPIDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BLACKBEAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BROWNBEAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.GOLDEN_CHICKEN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.WILD_BOAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.BOAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.DIRTY_BOAR.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
		w.register(MobZEntities.KATHERINE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.WITHER_BLAZE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.WITHENDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.HONEY_SLIME.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.TINY_SPIDER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.PILLAGER_BOSS.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.BABY_RAVAGER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.CHARLES.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.KNIGHT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.WILLIAM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.ANDRIU.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.SPIRIT_OF_DEATH.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
		w.register(MobZEntities.METALGOLEM.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				AgeableMob::checkMobSpawnRules);
		w.register(MobZEntities.SOUL_CREEPER.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);

		w.register(MobZEntities.TOAD.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Animal::checkAnimalSpawnRules);
	}
}
