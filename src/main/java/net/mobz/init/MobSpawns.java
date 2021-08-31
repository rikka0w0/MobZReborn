package net.mobz.init;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.mobz.MobZ;
import net.mobz.IBiomeFilter;
import net.mobz.IMobSpawnAdder;

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
			return category != Biome.BiomeCategory.NETHER && category != Biome.BiomeCategory.THEEND
					&& category != Biome.BiomeCategory.ICY && category != Biome.BiomeCategory.OCEAN
					&& category != Biome.BiomeCategory.MUSHROOM && category != Biome.BiomeCategory.EXTREME_HILLS
					&& category != Biome.BiomeCategory.MESA && category != Biome.BiomeCategory.DESERT;
		};

		spawnAdder.register(biomeSelector, MobZEntities.ARCHERENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHERENTITY, MobZ.configs.BowmanSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.ARMORED.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED, MobZ.configs.ArmoredZombieSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.DWARFENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARFENTITY, MobZ.configs.DwarfSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.FAST.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST, MobZ.configs.SpeedyZombieSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FULLIRONENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FULLIRONENTITY, MobZ.configs.SteveSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT2ENTITY, MobZ.configs.WarriorSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHTENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHTENTITY, MobZ.configs.TemplarSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGE2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGE2ENTITY, MobZ.configs.ZombieMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI2.getCategory(), new MobSpawnSettings.SpawnerData(MobZEntities.SKELI2,
				MobZ.configs.OvergrownSkeletonSpawnRate, 2, 4));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI4.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI4, MobZ.configs.LostSkeletonSpawnRate, 2, 2));
		spawnAdder.register(biomeSelector, MobZEntities.SLIMO.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SLIMO, MobZ.configs.GrassSlimeSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SPI.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPI, MobZ.configs.BlueSpiderSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SPO.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPO, MobZ.configs.PurpleSpiderSpawnRate, 1, 4));
		spawnAdder.register(biomeSelector, MobZEntities.TSPIDER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TSPIDER, MobZ.configs.TinySpiderSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.TANK.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK, MobZ.configs.TankSpawnRate, 1, 2));

		if (MobZ.configs.WildBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR, MobZ.configs.WildBoarSpawnRate, 2, 4));
		}
		if (MobZ.configs.DirtyBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR3.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR3, MobZ.configs.DirtyBoarSpawnRate, 1, 3));
		}
		if (MobZ.configs.BrownBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BROWNBEAR.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR, MobZ.configs.BrownBearSpawnRate, 2, 3));
		}
		if (MobZ.configs.AlexSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.FRIEND.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.FRIEND, MobZ.configs.AlexSpawnRate, 1, 1));
		}
		if (MobZ.configs.GoldenChickenSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.GCHICKEN.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.GCHICKEN, MobZ.configs.GoldenChickenSpawnRate, 1, 2));
		}
		if (MobZ.configs.FioraSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.KNIGHT4ENTITY.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.KNIGHT4ENTITY, MobZ.configs.FioraSpawnRate, 1, 1));
		}


		spawnAdder.register(biomeSelector, MobZEntities.TOAD.getCategory(), new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD, MobZ.configs.ToadSpawnRate, 1, 1));
	}

	private static void iceSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.ICY;

		spawnAdder.register(biomeSelector, MobZEntities.CREEP.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CREEP, MobZ.configs.FrostCreeperSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FROSTENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FROSTENTITY, MobZ.configs.FrostBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ICEGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM, MobZ.configs.IceGolemSpawnRate, 1, 1));
		if (MobZ.configs.BoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR2.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR2, MobZ.configs.BoarSpawnRate, 2, 3));
		}

		if (MobZ.configs.BlackBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BLACKBEAR.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR, MobZ.configs.BlackBearSpawnRate, 1, 2));
		}
	}

	private static void netherSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.NETHER;

		spawnAdder.register(biomeSelector, MobZEntities.DOG.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DOG, MobZ.configs.NetherWolfSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.LAVAGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM, MobZ.configs.LavaGolemSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.PIG.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.PIG, MobZ.configs.PigmanSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI3.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI3, MobZ.configs.NetherSkeletonSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.WITHENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHENTITY, MobZ.configs.WitherBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SCREEPER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SCREEPER, MobZ.configs.SoulCreeperSpawnRate, 1, 2));
	}

	private static void endSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.THEEND;

		spawnAdder.register(biomeSelector, MobZEntities.ENDER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER, MobZ.configs.EndermanSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ENDERZOMBIE.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDERZOMBIE, MobZ.configs.EnderzombieSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT3ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT3ENTITY, MobZ.configs.EnderKnightSpawnRate, 1, 1));
	}

	private static void bossSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> {
			return category == Biome.BiomeCategory.MESA;
		};

		spawnAdder.register(biomeSelector, MobZEntities.BIGBOSSENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSSENTITY, MobZ.configs.BigBossSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ARCHER2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER2ENTITY, MobZ.configs.ArcherSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.BOSS.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS, MobZ.configs.BossZombieSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ILLUSIONER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER, MobZ.configs.IllusionerSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT5ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT5ENTITY, MobZ.configs.LordofDarknessSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGEENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGEENTITY, MobZ.configs.SpiderMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI1.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI1, MobZ.configs.BossSkeletonSpawnRate, 1, 1));
	}

	private static void rockySpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.EXTREME_HILLS;

		spawnAdder.register(biomeSelector, MobZEntities.STONEGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM, MobZ.configs.StoneGolemSpawnRate, 1, 1));

	}

	private static void jungleSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.JUNGLE;

		spawnAdder.register(biomeSelector, MobZEntities.CRIP.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CRIP, MobZ.configs.CookieCreeperSpawnRate, 1, 2));
	}
}
