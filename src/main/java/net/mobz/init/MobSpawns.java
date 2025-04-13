package net.mobz.init;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.mobz.MobZ;
import net.mobz.tags.MobZBiomeTags;

public class MobSpawns {
	private static void addSpawnToBiome(
			Map<ResourceLocation, Pair<TagKey<Biome>, WeightedList<SpawnerData>>> map, TagKey<Biome> biomeTag,
			int weight,
			MobSpawnSettings.SpawnerData spawnerData) {
		String fileName = null;
		ResourceLocation entityKey = EntityType.getKey(spawnerData.type());
		fileName = "spawn_" + entityKey.getPath() + "_in_" + biomeTag.location().getNamespace() + "_"
			+ biomeTag.location().getPath();

		WeightedList<SpawnerData> weightedList = WeightedList.<SpawnerData>builder().add(spawnerData, weight).build();

		ResourceLocation fileNameResLoc = MobZ.resLoc(fileName);
		map.put(fileNameResLoc, Pair.of(biomeTag, weightedList));
	}

	public static void collectAll(Map<ResourceLocation, Pair<TagKey<Biome>, WeightedList<SpawnerData>>> map) {
		// Normal spawns
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.bowman.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOWMAN.get(), 1, 2));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.armored_zombie.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARMORED_ZOMBIE.get(), 1, 2));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.dwarf.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.DWARF.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.fast_zombie.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.FAST_ZOMBIE.get(), 2, 3));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.iron_steve.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.IRON_STEVE.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.warrior.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.WARRIOR.get(), 1, 2));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.templar.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.TEMPLAR.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.zombie_mage.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ZOMBIE_MAGE.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.overgrown_skeleton.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.OVERGROWN_SKELETON.get(), 2, 4));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.lost_skeleton.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.LOST_SKELETON.get(), 2, 2));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.honey_slime.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.HONEY_SLIME.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.blue_spider.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.BLUE_SPIDER.get(), 2, 3));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.purple_spider.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.PURPLE_SPIDER.get(), 1, 4));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.tiny_spider.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.TINY_SPIDER.get(), 1, 1));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
				MobZ.configs.tank_zombie.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.TANK_ZOMBIE.get(), 1, 2));

		if (MobZ.configs.wild_boar.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.wild_boar.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.WILD_BOAR.get(), 2, 4));
		}
		if (MobZ.configs.dirty_boar.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.dirty_boar.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.DIRTY_BOAR.get(), 1, 3));
		}
		if (MobZ.configs.brown_bear.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.brown_bear.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.BROWNBEAR.get(), 2, 3));
		}
		if (MobZ.configs.katherine.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.katherine.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.KATHERINE.get(), 1, 1));
		}
		if (MobZ.configs.golden_chicken.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.golden_chicken.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.GOLDEN_CHICKEN.get(), 1, 2));
		}
		if (MobZ.configs.fiora.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_NORMAL_TAG,
					MobZ.configs.fiora.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.FIORA.get(), 1, 1));
		}

		addSpawnToBiome(map, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS,
				MobZ.configs.toad.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.TOAD.get(), 1, 1));

		// Ice spawns
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_ICY_TAG,
				MobZ.configs.frost_creeper.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST_CREEPER.get(), 1, 3));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_ICY_TAG,
				MobZ.configs.frost_blaze.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.FROST_BLAZE.get(), 1, 3));
		addSpawnToBiome(map, MobZBiomeTags.SPAWN_ICY_TAG,
				MobZ.configs.ice_golem.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ICEGOLEM.get(), 1, 1));
		if (MobZ.configs.boar.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_ICY_TAG,
					MobZ.configs.boar.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.BOAR.get(), 2, 3));
		}

		if (MobZ.configs.black_bear.spawn) {
			addSpawnToBiome(map, MobZBiomeTags.SPAWN_ICY_TAG,
					MobZ.configs.black_bear.spawn_rate,
					new MobSpawnSettings.SpawnerData(MobZEntities.BLACKBEAR.get(), 1, 2));
		}

		// Nether spawns
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.nether_wolf.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_WOLF.get(), 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.lava_golem.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.LAVAGOLEM.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.pigman.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.PIGMAN.get(), 2, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.nether_skeleton.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.NETHER_SKELETON.get(), 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.wither_blaze.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.WITHER_BLAZE.get(), 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_NETHER,
				MobZ.configs.soul_creeper.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.SOUL_CREEPER.get(), 1, 2));

		// End spawns
		addSpawnToBiome(map, BiomeTags.IS_END,
				MobZ.configs.ender.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER.get(), 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				MobZ.configs.ender_zombie.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_ZOMBIE.get(), 1, 3));
		addSpawnToBiome(map, BiomeTags.IS_END,
				MobZ.configs.ender_knight.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ENDER_KNIGHT.get(), 1, 1));

		// Badland spawns
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.bigboss.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.BIGBOSS.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.archer.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ARCHER.get(), 1, 2));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.boss_zombie.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_ZOMBIE.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.illusioner.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.ILLUSIONER.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.lord_of_darkness.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.LORD_OF_DARKNESS.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.spider_mage.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.SPIDER_MAGE.get(), 1, 1));
		addSpawnToBiome(map, BiomeTags.IS_BADLANDS,
				MobZ.configs.boss_skeleton.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.BOSS_SKELETON.get(), 1, 1));

		// Rocky spawns
		addSpawnToBiome(map, BiomeTags.IS_HILL,
				MobZ.configs.stone_golem.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.STONEGOLEM.get(), 1, 1));

		// Jungle spawns
		addSpawnToBiome(map, BiomeTags.IS_JUNGLE,
				MobZ.configs.cookie_creeper.spawn_rate,
				new MobSpawnSettings.SpawnerData(MobZEntities.COOKIE_CREEPER.get(), 1, 2));
	}
}
