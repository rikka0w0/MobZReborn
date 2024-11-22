package net.mobz.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.mobz.MobZ;

public class MobZBiomeTags {
	public final static TagKey<Biome> SPAWN_NORMAL_TAG = newTag("spawn_normal");
	public final static TagKey<Biome> SPAWN_ICY_TAG = newTag("spawn_icy");

	private static TagKey<Biome> newTag(String name) {
		return TagKey.create(Registries.BIOME, MobZ.resLoc(name));
	}
}
