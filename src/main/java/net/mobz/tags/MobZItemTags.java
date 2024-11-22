package net.mobz.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.mobz.MobZ;

public class MobZItemTags {
	public final static TagKey<Item> GOLDEN_CHICKEN_FOOD = newTag("golden_chicken_food");
	public final static TagKey<Item> TOAD_FOOD_TAG = newTag("toad_food");
	public final static TagKey<Item> FIORA_EQUIP_TAG = newTag("fiora_equip");
	public final static TagKey<Item> KATHERINE_EQUIP_TAG = newTag("katherine_equip");
	public final static TagKey<Item> FIORA_FOOD_TAG = newTag("fiora_food");
	public final static TagKey<Item> KATHERINE_FOOD_TAG = newTag("katherine_food");
	public final static TagKey<Item> FIORA_TAME_TAG = newTag("fiora_tame");
	public final static TagKey<Item> KATHERINE_TAME_TAG = newTag("katherine_tame");


	// MobZItems.BOSS_INGOT
	public static final TagKey<Item> BOSS_TOOL_MATERIALS = newTag("boss_tool_materials");
	public static final TagKey<Item> ARMORED_TOOL_MATERIALS = newTag("armored_tool_materials");
	public static final TagKey<Item> ERAGONS_TOOL_MATERIALS = newTag("eragons_tool_materials");
	public static final TagKey<Item> POISON_TOOL_MATERIALS = newTag("poison_tool_materials");
	// Items.DRAGON_EGG
	public static final TagKey<Item> RAINBOW_TOOL_MATERIALS = newTag("rainbow_tool_materials");
	public static final TagKey<Item> WITHER_TOOL_MATERIALS = newTag("wither_tool_materials");
	public static final TagKey<Item> DEBUG_TOOL_MATERIALS = newTag("debug_tool_materials");

	// MobZItems.AMAT_INGOT.get()
	public static final TagKey<Item> REPAIRS_AMAT_ARMOR = newTag("repairs_amat_armor");
	// MobZItems.BOSS_INGOT.get()
	public static final TagKey<Item> REPAIRS_BOSS_ARMOR = newTag("repairs_boss_armor");
	// MobZItems.HARDENEDMETAL_INGOT.get()
	public static final TagKey<Item> REPAIRS_LIFE_ARMOR = newTag("repairs_life_armor");
	// MobZItems.BEAR_LEATHER.get()
	public static final TagKey<Item> REPAIRS_SPEED_ARMOR = newTag("repairs_speed_armor");
	// Items.EMERALD
	public static final TagKey<Item> REPAIRS_SPEED2_ARMOR = newTag("repairs_speed2_armor");


	private static TagKey<Item> newTag(String name) {
		return TagKey.create(Registries.ITEM, MobZ.resLoc(name));
	}
}
