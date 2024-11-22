package net.mobz.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.mobz.MobZ;

public class MobZEntityTags {
	public final static TagKey<EntityType<?>> TOAD_TARGET_TAG = newTag("toad_target");

	private static TagKey<EntityType<?>> newTag(String name) {
		return TagKey.create(Registries.ENTITY_TYPE, MobZ.resLoc(name));
	}
}
