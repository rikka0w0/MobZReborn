package net.mobz.init;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.mobz.MobZ;

public class MobSpawns {
	private static void addSpawnToBiome(
			Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> map, TagKey<Biome> biomeTag,
			MobSpawnSettings.SpawnerData ... spawnerDataList) {
		String fileName = null;
		if (spawnerDataList.length == 1) {
			ResourceLocation entityKey = EntityType.getKey(spawnerDataList[0].type);
			fileName = "spawn_" + entityKey.getPath() + "_in_" + biomeTag.location().getNamespace() + "_"
					+ biomeTag.location().getPath();
		} else {
			String hashSrc = "";
			for (SpawnerData s: spawnerDataList) {
				hashSrc += "," + EntityType.getKey(s.type);
			}

			fileName = "spawn_" + hashSrc.length() + "h" + hashSrc.hashCode() + "_in_" + biomeTag.location().getNamespace() + "_"
					+ biomeTag.location().getPath();
		}
		ResourceLocation fileNameResLoc = new ResourceLocation(MobZ.MODID, fileName);
		map.put(fileNameResLoc, Pair.of(biomeTag, List.of(spawnerDataList)));
	}

	public static void collectAll(Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> map) {
		// Normal spawns
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOWMAN.get(), MobZ.configs.BowmanSpawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED.get(), MobZ.configs.ArmoredZombieSpawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARFENTITY.get(), MobZ.configs.DwarfSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST.get(), MobZ.configs.SpeedyZombieSpawnRate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FULLIRONENTITY.get(), MobZ.configs.SteveSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT2ENTITY.get(), MobZ.configs.WarriorSpawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHTENTITY.get(), MobZ.configs.TemplarSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGE2ENTITY.get(), MobZ.configs.ZombieMageSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(MobZEntities.SKELI2.get(),
				MobZ.configs.OvergrownSkeletonSpawnRate, 2, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI4.get(), MobZ.configs.LostSkeletonSpawnRate, 2, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.SLIMO.get(), MobZ.configs.GrassSlimeSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.SPI.get(), MobZ.configs.BlueSpiderSpawnRate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.SPO.get(), MobZ.configs.PurpleSpiderSpawnRate, 1, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TSPIDER.get(), MobZ.configs.TinySpiderSpawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK.get(), MobZ.configs.TankSpawnRate, 1, 2));

		if (MobZ.configs.WildBoarSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR.get(), MobZ.configs.WildBoarSpawnRate, 2, 4));
		}
		if (MobZ.configs.DirtyBoarSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR3.get(), MobZ.configs.DirtyBoarSpawnRate, 1, 3));
		}
		if (MobZ.configs.BrownBearSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR.get(), MobZ.configs.BrownBearSpawnRate, 2, 3));
		}
		if (MobZ.configs.AlexSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.KATHERINE.get(), MobZ.configs.AlexSpawnRate, 1, 1));
		}
		if (MobZ.configs.GoldenChickenSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.GCHICKEN.get(), MobZ.configs.GoldenChickenSpawnRate, 1, 2));
		}
		if (MobZ.configs.FioraSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.FIORA.get(), MobZ.configs.FioraSpawnRate, 1, 1));
		}

		addSpawnToBiome(map, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD.get(), MobZ.configs.ToadSpawnRate, 1, 1));

		// Ice spawns
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.CREEP.get(), MobZ.configs.FrostCreeperSpawnRate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROSTENTITY.get(), MobZ.configs.FrostBlazeSpawnRate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM.get(), MobZ.configs.IceGolemSpawnRate, 1, 1));
		if (MobZ.configs.BoarSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR2.get(), MobZ.configs.BoarSpawnRate, 2, 3));
		}

		if (MobZ.configs.BlackBearSpawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR.get(), MobZ.configs.BlackBearSpawnRate, 1, 2));
		}

		// Nether spawns
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.DOG.get(), MobZ.configs.NetherWolfSpawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM.get(), MobZ.configs.LavaGolemSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.PIG.get(), MobZ.configs.PigmanSpawnRate, 2, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI3.get(), MobZ.configs.NetherSkeletonSpawnRate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHENTITY.get(), MobZ.configs.WitherBlazeSpawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.SCREEPER.get(), MobZ.configs.SoulCreeperSpawnRate, 1, 2));

		// End spawns
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER.get(), MobZ.configs.EndermanSpawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDERZOMBIE.get(), MobZ.configs.EnderzombieSpawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT3ENTITY.get(), MobZ.configs.EnderKnightSpawnRate, 1, 1));

		// Badland spawns
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSSENTITY.get(), MobZ.configs.BigBossSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER.get(), MobZ.configs.ArcherSpawnRate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS.get(), MobZ.configs.BossZombieSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER.get(), MobZ.configs.IllusionerSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.KNIGHT5ENTITY.get(), MobZ.configs.LordofDarknessSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.MAGEENTITY.get(), MobZ.configs.SpiderMageSpawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.SKELI1.get(), MobZ.configs.BossSkeletonSpawnRate, 1, 1));

		// Rocky spawns
		addSpawnToBiome(map, BiomeTags.IS_HILL,
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM.get(), MobZ.configs.StoneGolemSpawnRate, 1, 1));

		// Jungle spawns
		addSpawnToBiome(map, BiomeTags.IS_JUNGLE,
				new MobSpawnSettings.SpawnerData(MobZEntities.CRIP.get(), MobZ.configs.CookieCreeperSpawnRate, 1, 2));
	}
}
