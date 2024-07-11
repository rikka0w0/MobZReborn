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
				new MobSpawnSettings.SpawnerData(MobZEntities.BOWMAN.get(), MobZ.configs.bowman.spawn_rate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED_ZOMBIE.get(), MobZ.configs.armored_zombie.spawn_rate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARF.get(), MobZ.configs.dwarf.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST_ZOMBIE.get(), MobZ.configs.fast_zombie.spawn_rate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.IRON_STEVE.get(), MobZ.configs.iron_steve.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.WARRIOR.get(), MobZ.configs.warrior.spawn_rate, 1, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TEMPLAR.get(), MobZ.configs.templar.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ZOMBIE_MAGE.get(), MobZ.configs.zombie_mage.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(MobZEntities.OVERGROWN_SKELETON.get(),
				MobZ.configs.overgrown_skeleton.spawn_rate, 2, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.LOST_SKELETON.get(), MobZ.configs.lost_skeleton.spawn_rate, 2, 2));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.HONEY_SLIME.get(), MobZ.configs.honey_slime.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.BLUE_SPIDER.get(), MobZ.configs.blue_spider.spawn_rate, 2, 3));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.PURPLE_SPIDER.get(), MobZ.configs.purple_spider.spawn_rate, 1, 4));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TINY_SPIDER.get(), MobZ.configs.tiny_spider.spawn_rate, 1, 1));
		addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK_ZOMBIE.get(), MobZ.configs.tank_zombie.spawn_rate, 1, 2));

		if (MobZ.configs.wild_boar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.WILD_BOAR.get(), MobZ.configs.wild_boar.spawn_rate, 2, 4));
		}
		if (MobZ.configs.dirty_boar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.DIRTY_BOAR.get(), MobZ.configs.dirty_boar.spawn_rate, 1, 3));
		}
		if (MobZ.configs.brown_bear.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.BROWNBEAR.get(), MobZ.configs.brown_bear.spawn_rate, 2, 3));
		}
		if (MobZ.configs.katherine.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.KATHERINE.get(), MobZ.configs.katherine.spawn_rate, 1, 1));
		}
		if (MobZ.configs.golden_chicken.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.GOLDEN_CHICKEN.get(), MobZ.configs.golden_chicken.spawn_rate, 1, 2));
		}
		if (MobZ.configs.fiora.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_NORMAL_TAG, new MobSpawnSettings.SpawnerData(
					MobZEntities.FIORA.get(), MobZ.configs.fiora.spawn_rate, 1, 1));
		}

		addSpawnToBiome(map, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, new MobSpawnSettings.SpawnerData(
				MobZEntities.TOAD.get(), MobZ.configs.toad.spawn_rate, 1, 1));

		// Ice spawns
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST_CREEPER.get(), MobZ.configs.frost_creeper.spawn_rate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST_BLAZE.get(), MobZ.configs.frost_blaze.spawn_rate, 1, 3));
		addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM.get(), MobZ.configs.ice_golem.spawn_rate, 1, 1));
		if (MobZ.configs.boar.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR.get(), MobZ.configs.boar.spawn_rate, 2, 3));
		}

		if (MobZ.configs.black_bear.spawn) {
			addSpawnToBiome(map, MobZ.SPAWN_ICY_TAG,
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR.get(), MobZ.configs.black_bear.spawn_rate, 1, 2));
		}

		// Nether spawns
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_WOLF.get(), MobZ.configs.nether_wolf.spawn_rate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM.get(), MobZ.configs.lava_golem.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.PIGMAN.get(), MobZ.configs.pigman.spawn_rate, 2, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_SKELETON.get(), MobZ.configs.nether_skeleton.spawn_rate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHER_BLAZE.get(), MobZ.configs.wither_blaze.spawn_rate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				new MobSpawnSettings.SpawnerData(MobZEntities.SOUL_CREEPER.get(), MobZ.configs.soul_creeper.spawn_rate, 1, 2));

		// End spawns
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER.get(), MobZ.configs.ender.spawn_rate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_ZOMBIE.get(), MobZ.configs.ender_zombie.spawn_rate, 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_KNIGHT.get(), MobZ.configs.ender_knight.spawn_rate, 1, 1));

		// Badland spawns
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSS.get(), MobZ.configs.bigboss.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER.get(), MobZ.configs.archer.spawn_rate, 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_ZOMBIE.get(), MobZ.configs.boss_zombie.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER.get(), MobZ.configs.illusioner.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.LORD_OF_DARKNESS.get(), MobZ.configs.lord_of_darkness.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.SPIDER_MAGE.get(), MobZ.configs.spider_mage.spawn_rate, 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_SKELETON.get(), MobZ.configs.boss_skeleton.spawn_rate, 1, 1));

		// Rocky spawns
		addSpawnToBiome(map, BiomeTags.IS_HILL,
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM.get(), MobZ.configs.stone_golem.spawn_rate, 1, 1));

		// Jungle spawns
		addSpawnToBiome(map, BiomeTags.IS_JUNGLE,
				new MobSpawnSettings.SpawnerData(MobZEntities.COOKIE_CREEPER.get(), MobZ.configs.cookie_creeper.spawn_rate, 1, 2));
	}
}
