package net.mobz.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.mobz.MobZ;

public class MobZBlockTags {
	public static final TagKey<Block> INCORRECT_FOR_BOSS_TOOL = newTag("incorrect_for_boss_tool");
	public static final TagKey<Block> INCORRECT_FOR_ARMORED_TOOL = newTag("incorrect_for_armored_tool");
	public static final TagKey<Block> INCORRECT_FOR_ERAGONS_TOOL = newTag("incorrect_for_eragons_tool");
	public static final TagKey<Block> INCORRECT_FOR_POISON_TOOL = newTag("incorrect_for_poison_tool");
	public static final TagKey<Block> INCORRECT_FOR_RAINBOW_TOOL = newTag("incorrect_for_rainbow_tool");
	public static final TagKey<Block> INCORRECT_FOR_WITHER_TOOL = newTag("incorrect_for_wither_tool");
	public static final TagKey<Block> INCORRECT_FOR_DEBUG_TOOL = newTag("incorrect_for_debug_tool");

	private static TagKey<Block> newTag(String name) {
		return TagKey.create(Registries.BLOCK, MobZ.resLoc(name));
	}
}
