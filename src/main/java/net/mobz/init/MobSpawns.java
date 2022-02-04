package net.mobz.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.mobz.IBiomeFilter;
import net.mobz.IMobSpawnAdder;
import net.mobz.MobZ;

public class MobSpawns {
	public static void addMobSpawns(IMobSpawnAdder spawnAdder) {
		normalSpawn(spawnAdder);
		iceSpawn(spawnAdder);
		netherSpawn(spawnAdder);
		endSpawn(spawnAdder);
		bossSpawn(spawnAdder);
		rockySpawn(spawnAdder);
		jungleSpawn(spawnAdder);
	}

	private static void normalSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> {
			return category != Biome.Category.NETHER && category != Biome.Category.THEEND
					&& category != Biome.Category.ICY && category != Biome.Category.OCEAN
					&& category != Biome.Category.MUSHROOM && category != Biome.Category.EXTREME_HILLS
					&& category != Biome.Category.MESA && category != Biome.Category.DESERT;
		};

		spawnAdder.register(biomeSelector, MobZEntities.ARCHERENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ARCHERENTITY, MobZ.configs.BowmanSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.ARMORED.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ARMORED, MobZ.configs.ArmoredZombieSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.DWARFENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.DWARFENTITY, MobZ.configs.DwarfSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.FAST.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.FAST, MobZ.configs.SpeedyZombieSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FULLIRONENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.FULLIRONENTITY, MobZ.configs.SteveSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT2ENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.KNIGHT2ENTITY, MobZ.configs.WarriorSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHTENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.KNIGHTENTITY, MobZ.configs.TemplarSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGE2ENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.MAGE2ENTITY, MobZ.configs.ZombieMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI2.getCategory(), new MobSpawnInfo.Spawners(MobZEntities.SKELI2,
				MobZ.configs.OvergrownSkeletonSpawnRate, 2, 4));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI4.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SKELI4, MobZ.configs.LostSkeletonSpawnRate, 2, 2));
		spawnAdder.register(biomeSelector, MobZEntities.SLIMO.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SLIMO, MobZ.configs.GrassSlimeSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SPI.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SPI, MobZ.configs.BlueSpiderSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SPO.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SPO, MobZ.configs.PurpleSpiderSpawnRate, 1, 4));
		spawnAdder.register(biomeSelector, MobZEntities.TSPIDER.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.TSPIDER, MobZ.configs.TinySpiderSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.TANK.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.TANK, MobZ.configs.TankSpawnRate, 1, 2));

		if (MobZ.configs.WildBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR.getCategory(),
					new MobSpawnInfo.Spawners(MobZEntities.BOAR, MobZ.configs.WildBoarSpawnRate, 2, 4));
		}
		if (MobZ.configs.DirtyBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR3.getCategory(),
					new MobSpawnInfo.Spawners(MobZEntities.BOAR3, MobZ.configs.DirtyBoarSpawnRate, 1, 3));
		}
		if (MobZ.configs.BrownBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BROWNBEAR.getCategory(), new MobSpawnInfo.Spawners(
					MobZEntities.BROWNBEAR, MobZ.configs.BrownBearSpawnRate, 2, 3));
		}
		if (MobZ.configs.AlexSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.FRIEND.getCategory(),
					new MobSpawnInfo.Spawners(MobZEntities.FRIEND, MobZ.configs.AlexSpawnRate, 1, 1));
		}
		if (MobZ.configs.GoldenChickenSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.GCHICKEN.getCategory(), new MobSpawnInfo.Spawners(
					MobZEntities.GCHICKEN, MobZ.configs.GoldenChickenSpawnRate, 1, 2));
		}
		if (MobZ.configs.FioraSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.KNIGHT4ENTITY.getCategory(), new MobSpawnInfo.Spawners(
					MobZEntities.KNIGHT4ENTITY, MobZ.configs.FioraSpawnRate, 1, 1));
		}
	}

	private static void iceSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.Category.ICY;

		spawnAdder.register(biomeSelector, MobZEntities.CREEP.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.CREEP, MobZ.configs.FrostCreeperSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FROSTENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.FROSTENTITY, MobZ.configs.FrostBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ICEGOLEM.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ICEGOLEM, MobZ.configs.IceGolemSpawnRate, 1, 1));
		if (MobZ.configs.BoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR2.getCategory(),
					new MobSpawnInfo.Spawners(MobZEntities.BOAR2, MobZ.configs.BoarSpawnRate, 2, 3));
		}

		if (MobZ.configs.BlackBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BLACKBEAR.getCategory(),
					new MobSpawnInfo.Spawners(MobZEntities.BLACKBEAR, MobZ.configs.BlackBearSpawnRate, 1, 2));
		}
	}

	private static void netherSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.Category.NETHER;

		spawnAdder.register(biomeSelector, MobZEntities.DOG.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.DOG, MobZ.configs.NetherWolfSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.LAVAGOLEM.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.LAVAGOLEM, MobZ.configs.LavaGolemSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.PIG.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.PIG, MobZ.configs.PigmanSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI3.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SKELI3, MobZ.configs.NetherSkeletonSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.WITHENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.WITHENTITY, MobZ.configs.WitherBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SCREEPER.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SCREEPER, MobZ.configs.SoulCreeperSpawnRate, 1, 2));
	}

	private static void endSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.Category.THEEND;

		spawnAdder.register(biomeSelector, MobZEntities.ENDER.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ENDER, MobZ.configs.EndermanSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ENDERZOMBIE.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ENDERZOMBIE, MobZ.configs.EnderzombieSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT3ENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.KNIGHT3ENTITY, MobZ.configs.EnderKnightSpawnRate, 1, 1));
	}

	private static void bossSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> {
			return category == Biome.Category.MESA;
		};

		spawnAdder.register(biomeSelector, MobZEntities.BIGBOSSENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.BIGBOSSENTITY, MobZ.configs.BigBossSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ARCHER2ENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ARCHER2ENTITY, MobZ.configs.ArcherSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.BOSS.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.BOSS, MobZ.configs.BossZombieSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ILLUSIONER.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.ILLUSIONER, MobZ.configs.IllusionerSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT5ENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.KNIGHT5ENTITY, MobZ.configs.LordofDarknessSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGEENTITY.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.MAGEENTITY, MobZ.configs.SpiderMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI1.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.SKELI1, MobZ.configs.BossSkeletonSpawnRate, 1, 1));
	}

	private static void rockySpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.Category.EXTREME_HILLS;

		spawnAdder.register(biomeSelector, MobZEntities.STONEGOLEM.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.STONEGOLEM, MobZ.configs.StoneGolemSpawnRate, 1, 1));

	}

	private static void jungleSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.Category.JUNGLE;

		spawnAdder.register(biomeSelector, MobZEntities.CRIP.getCategory(),
				new MobSpawnInfo.Spawners(MobZEntities.CRIP, MobZ.configs.CookieCreeperSpawnRate, 1, 2));
	}
}
