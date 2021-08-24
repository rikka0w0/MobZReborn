package net.mobz.init;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.mobz.Configs;
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
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHERENTITY, Configs.instance.BowmanSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.ARMORED.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED, Configs.instance.ArmoredZombieSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.DWARFENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARFENTITY, Configs.instance.DwarfSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.FAST.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST, Configs.instance.SpeedyZombieSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FULLIRONENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FULLIRONENTITY, Configs.instance.SteveSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT2ENTITY, Configs.instance.WarriorSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHTENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHTENTITY, Configs.instance.TemplarSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGE2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGE2ENTITY, Configs.instance.ZombieMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI2.getCategory(), new MobSpawnSettings.SpawnerData(MobZEntities.SKELI2,
				Configs.instance.OvergrownSkeletonSpawnRate, 2, 4));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI4.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI4, Configs.instance.LostSkeletonSpawnRate, 2, 2));
		spawnAdder.register(biomeSelector, MobZEntities.SLIMO.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SLIMO, Configs.instance.GrassSlimeSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SPI.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPI, Configs.instance.BlueSpiderSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SPO.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPO, Configs.instance.PurpleSpiderSpawnRate, 1, 4));
		spawnAdder.register(biomeSelector, MobZEntities.TSPIDER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TSPIDER, Configs.instance.TinySpiderSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.TANK.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK, Configs.instance.TankSpawnRate, 1, 2));

		if (Configs.instance.WildBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR, Configs.instance.WildBoarSpawnRate, 2, 4));
		}
		if (Configs.instance.DirtyBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR3.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR3, Configs.instance.DirtyBoarSpawnRate, 1, 3));
		}
		if (Configs.instance.BrownBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BROWNBEAR.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR, Configs.instance.BrownBearSpawnRate, 2, 3));
		}
		if (Configs.instance.AlexSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.FRIEND.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.FRIEND, Configs.instance.AlexSpawnRate, 1, 1));
		}
		if (Configs.instance.GoldenChickenSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.GCHICKEN.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.GCHICKEN, Configs.instance.GoldenChickenSpawnRate, 1, 2));
		}
		if (Configs.instance.FioraSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.KNIGHT4ENTITY.getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.KNIGHT4ENTITY, Configs.instance.FioraSpawnRate, 1, 1));
		}


		spawnAdder.register(biomeSelector, MobZEntities.TOAD.getCategory(), new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD, Configs.instance.ToadSpawnRate, 1, 1));
	}

	private static void iceSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.ICY;

		spawnAdder.register(biomeSelector, MobZEntities.CREEP.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CREEP, Configs.instance.FrostCreeperSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FROSTENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FROSTENTITY, Configs.instance.FrostBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ICEGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM, Configs.instance.IceGolemSpawnRate, 1, 1));
		if (Configs.instance.BoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR2.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR2, Configs.instance.BoarSpawnRate, 2, 3));
		}

		if (Configs.instance.BlackBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BLACKBEAR.getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR, Configs.instance.BlackBearSpawnRate, 1, 2));
		}
	}

	private static void netherSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.NETHER;

		spawnAdder.register(biomeSelector, MobZEntities.DOG.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DOG, Configs.instance.NetherWolfSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.LAVAGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM, Configs.instance.LavaGolemSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.PIG.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.PIG, Configs.instance.PigmanSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI3.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI3, Configs.instance.NetherSkeletonSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.WITHENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHENTITY, Configs.instance.WitherBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SCREEPER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SCREEPER, Configs.instance.SoulCreeperSpawnRate, 1, 2));
	}

	private static void endSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.THEEND;

		spawnAdder.register(biomeSelector, MobZEntities.ENDER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER, Configs.instance.EndermanSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ENDERZOMBIE.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDERZOMBIE, Configs.instance.EnderzombieSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT3ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT3ENTITY, Configs.instance.EnderKnightSpawnRate, 1, 1));
	}

	private static void bossSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> {
			return category == Biome.BiomeCategory.MESA;
		};

		spawnAdder.register(biomeSelector, MobZEntities.BIGBOSSENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSSENTITY, Configs.instance.BigBossSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ARCHER2ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER2ENTITY, Configs.instance.ArcherSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.BOSS.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS, Configs.instance.BossZombieSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ILLUSIONER.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER, Configs.instance.IllusionerSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT5ENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT5ENTITY, Configs.instance.LordofDarknessSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGEENTITY.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGEENTITY, Configs.instance.SpiderMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI1.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI1, Configs.instance.BossSkeletonSpawnRate, 1, 1));
	}

	private static void rockySpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.EXTREME_HILLS;

		spawnAdder.register(biomeSelector, MobZEntities.STONEGOLEM.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM, Configs.instance.StoneGolemSpawnRate, 1, 1));

	}

	private static void jungleSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.JUNGLE;

		spawnAdder.register(biomeSelector, MobZEntities.CRIP.getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CRIP, Configs.instance.CookieCreeperSpawnRate, 1, 2));
	}
}
