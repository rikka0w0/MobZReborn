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
				new MobSpawnSettings.SpawnerData(MobZEntities.BOWMAN.get(), MobZ.configs.Bowman.spawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED_ZOMBIE.get(), MobZ.configs.ArmoredZombie.spawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARF.get(), MobZ.configs.Dwarf.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST_ZOMBIE.get(), MobZ.configs.SpeedyZombie.spawnRate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.IRON_STEVE.get(), MobZ.configs.Steve.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.WARRIOR.get(), MobZ.configs.Warrior.spawnRate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TEMPLAR.get(), MobZ.configs.Templar.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ZOMBIE_MAGE.get(), MobZ.configs.ZombieMage.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(MobZEntities.OVERGROWN_SKELETON.get(),
				MobZ.configs.OvergrownSkeleton.spawnRate, 2, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.LOST_SKELETON.get(), MobZ.configs.LostSkeleton.spawnRate, 2, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.HONEY_SLIME.get(), MobZ.configs.HoneySlime.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.BLUE_SPIDER.get(), MobZ.configs.BlueSpider.spawnRate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.PURPLE_SPIDER.get(), MobZ.configs.PurpleSpider.spawnRate, 1, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TINY_SPIDER.get(), MobZ.configs.TinySpider.spawnRate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK_ZOMBIE.get(), MobZ.configs.Tank.spawnRate, 1, 2));

		if (MobZ.configs.WildBoar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.WILD_BOAR.get(), MobZ.configs.WildBoar.spawnRate, 2, 4));
		}
		if (MobZ.configs.DirtyBoar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.DIRTY_BOAR.get(), MobZ.configs.DirtyBoar.spawnRate, 1, 3));
		}
		if (MobZ.configs.BrownBear.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR.get(), MobZ.configs.BrownBear.spawnRate, 2, 3));
		}
		if (MobZ.configs.Alex.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.KATHERINE.get(), MobZ.configs.Alex.spawnRate, 1, 1));
		}
		if (MobZ.configs.GoldenChicken.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.GOLDEN_CHICKEN.get(), MobZ.configs.GoldenChicken.spawnRate, 1, 2));
		}
		if (MobZ.configs.Fiora.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.FIORA.get(), MobZ.configs.Fiora.spawnRate, 1, 1));
		}

		addSpawnToBiome(map, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD.get(), MobZ.configs.Toad.spawnRate, 1, 1));

		// Ice spawns
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST_CREEPER.get(), MobZ.configs.FrostCreeper.spawnRate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST.get(), MobZ.configs.FrostBlaze.spawnRate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM.get(), MobZ.configs.IceGolem.spawnRate, 1, 1));
		if (MobZ.configs.Boar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR.get(), MobZ.configs.Boar.spawnRate, 2, 3));
		}

		if (MobZ.configs.BlackBear.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR.get(), MobZ.configs.BlackBear.spawnRate, 1, 2));
		}

		// Nether spawns
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_WOLF.get(), MobZ.configs.NetherWolf.spawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM.get(), MobZ.configs.LavaGolem.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.PIGMAN.get(), MobZ.configs.Pigman.spawnRate, 2, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_SKELETON.get(), MobZ.configs.NetherSkeleton.spawnRate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHER_BLAZE.get(), MobZ.configs.WitherBlaze.spawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.SOUL_CREEPER.get(), MobZ.configs.SoulCreeper.spawnRate, 1, 2));

		// End spawns
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER.get(), MobZ.configs.Enderman.spawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_ZOMBIE.get(), MobZ.configs.Enderzombie.spawnRate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_KNIGHT.get(), MobZ.configs.EnderKnight.spawnRate, 1, 1));

		// Badland spawns
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSS.get(), MobZ.configs.BigBoss.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER.get(), MobZ.configs.Archer.spawnRate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_ZOMBIE.get(), MobZ.configs.BossZombie.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER.get(), MobZ.configs.Illusioner.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.LORD_OF_DARKNESS.get(), MobZ.configs.LordofDarkness.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.SPIDER_MAGE.get(), MobZ.configs.SpiderMage.spawnRate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_SKELETON.get(), MobZ.configs.BossSkeleton.spawnRate, 1, 1));

		// Rocky spawns
		addSpawnToBiome(map, BiomeTags.IS_HILL,
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM.get(), MobZ.configs.StoneGolem.spawnRate, 1, 1));

		// Jungle spawns
		addSpawnToBiome(map, BiomeTags.IS_JUNGLE,
				new MobSpawnSettings.SpawnerData(MobZEntities.COOKIE_CREEPER.get(), MobZ.configs.CookieCreeper.spawnRate, 1, 2));
	}
}
