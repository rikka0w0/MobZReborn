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

		spawnAdder.register(biomeSelector, MobZEntities.ARCHERENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHERENTITY.get(), MobZ.configs.BowmanSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.ARMORED.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED.get(), MobZ.configs.ArmoredZombieSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.DWARFENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARFENTITY.get(), MobZ.configs.DwarfSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.FAST.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST.get(), MobZ.configs.SpeedyZombieSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FULLIRONENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FULLIRONENTITY.get(), MobZ.configs.SteveSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT2ENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT2ENTITY.get(), MobZ.configs.WarriorSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHTENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHTENTITY.get(), MobZ.configs.TemplarSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGE2ENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGE2ENTITY.get(), MobZ.configs.ZombieMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI2.get().getCategory(), new MobSpawnSettings.SpawnerData(MobZEntities.SKELI2.get(),
				MobZ.configs.OvergrownSkeletonSpawnRate, 2, 4));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI4.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI4.get(), MobZ.configs.LostSkeletonSpawnRate, 2, 2));
		spawnAdder.register(biomeSelector, MobZEntities.SLIMO.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SLIMO.get(), MobZ.configs.GrassSlimeSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SPI.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPI.get(), MobZ.configs.BlueSpiderSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SPO.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SPO.get(), MobZ.configs.PurpleSpiderSpawnRate, 1, 4));
		spawnAdder.register(biomeSelector, MobZEntities.TSPIDER.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TSPIDER.get(), MobZ.configs.TinySpiderSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.TANK.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK.get(), MobZ.configs.TankSpawnRate, 1, 2));

		if (MobZ.configs.WildBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR.get().getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR.get(), MobZ.configs.WildBoarSpawnRate, 2, 4));
		}
		if (MobZ.configs.DirtyBoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR3.get().getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR3.get(), MobZ.configs.DirtyBoarSpawnRate, 1, 3));
		}
		if (MobZ.configs.BrownBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BROWNBEAR.get().getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR.get(), MobZ.configs.BrownBearSpawnRate, 2, 3));
		}
		if (MobZ.configs.AlexSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.KATHERINE.get().getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.KATHERINE.get(), MobZ.configs.AlexSpawnRate, 1, 1));
		}
		if (MobZ.configs.GoldenChickenSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.GCHICKEN.get().getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.GCHICKEN.get(), MobZ.configs.GoldenChickenSpawnRate, 1, 2));
		}
		if (MobZ.configs.FioraSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.FIORA.get().getCategory(), new MobSpawnSettings.SpawnerData(
					MobZEntities.FIORA.get(), MobZ.configs.FioraSpawnRate, 1, 1));
		}


		spawnAdder.register(biomeSelector, MobZEntities.TOAD.get().getCategory(), new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD.get(), MobZ.configs.ToadSpawnRate, 1, 1));
	}

	private static void iceSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.ICY;

		spawnAdder.register(biomeSelector, MobZEntities.CREEP.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CREEP.get(), MobZ.configs.FrostCreeperSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.FROSTENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.FROSTENTITY.get(), MobZ.configs.FrostBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ICEGOLEM.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM.get(), MobZ.configs.IceGolemSpawnRate, 1, 1));
		if (MobZ.configs.BoarSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BOAR2.get().getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR2.get(), MobZ.configs.BoarSpawnRate, 2, 3));
		}

		if (MobZ.configs.BlackBearSpawn) {
			spawnAdder.register(biomeSelector, MobZEntities.BLACKBEAR.get().getCategory(),
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR.get(), MobZ.configs.BlackBearSpawnRate, 1, 2));
		}
	}

	private static void netherSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.NETHER;

		spawnAdder.register(biomeSelector, MobZEntities.DOG.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.DOG.get(), MobZ.configs.NetherWolfSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.LAVAGOLEM.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM.get(), MobZ.configs.LavaGolemSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.PIG.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.PIG.get(), MobZ.configs.PigmanSpawnRate, 2, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI3.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI3.get(), MobZ.configs.NetherSkeletonSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.WITHENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHENTITY.get(), MobZ.configs.WitherBlazeSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.SCREEPER.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SCREEPER.get(), MobZ.configs.SoulCreeperSpawnRate, 1, 2));
	}

	private static void endSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.THEEND;

		spawnAdder.register(biomeSelector, MobZEntities.ENDER.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER.get(), MobZ.configs.EndermanSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.ENDERZOMBIE.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDERZOMBIE.get(), MobZ.configs.EnderzombieSpawnRate, 1, 3));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT3ENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT3ENTITY.get(), MobZ.configs.EnderKnightSpawnRate, 1, 1));
	}

	private static void bossSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> {
			return category == Biome.BiomeCategory.MESA;
		};

		spawnAdder.register(biomeSelector, MobZEntities.BIGBOSSENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSSENTITY.get(), MobZ.configs.BigBossSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ARCHER2ENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER2ENTITY.get(), MobZ.configs.ArcherSpawnRate, 1, 2));
		spawnAdder.register(biomeSelector, MobZEntities.BOSS.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS.get(), MobZ.configs.BossZombieSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.ILLUSIONER.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER.get(), MobZ.configs.IllusionerSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.KNIGHT5ENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT5ENTITY.get(), MobZ.configs.LordofDarknessSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.MAGEENTITY.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGEENTITY.get(), MobZ.configs.SpiderMageSpawnRate, 1, 1));
		spawnAdder.register(biomeSelector, MobZEntities.SKELI1.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI1.get(), MobZ.configs.BossSkeletonSpawnRate, 1, 1));
	}

	private static void rockySpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.EXTREME_HILLS;

		spawnAdder.register(biomeSelector, MobZEntities.STONEGOLEM.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM.get(), MobZ.configs.StoneGolemSpawnRate, 1, 1));

	}

	private static void jungleSpawn(IMobSpawnAdder spawnAdder) {
		IBiomeFilter biomeSelector = (category) -> category == Biome.BiomeCategory.JUNGLE;

		spawnAdder.register(biomeSelector, MobZEntities.CRIP.get().getCategory(),
				new MobSpawnSettings.SpawnerData(MobZEntities.CRIP.get(), MobZ.configs.CookieCreeperSpawnRate, 1, 2));
	}
}
