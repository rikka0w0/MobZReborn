package net.mobz;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;

import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class MobZ {
	public static final String MODID = "mobz";
	public static IAbstractedAPI platform = null;
	public static boolean isDebugMode = false;

	// Configs
	public static Configs configs = null;

	// Tags
	public final static TagKey<Item> TOAD_FOOD_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "toad_food"));
	public final static TagKey<EntityType<?>> TOAD_TARGET_TAG = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(MODID, "toad_target"));
	public final static TagKey<Item> FIORA_EQUIP_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "fiora_equip"));
	public final static TagKey<Item> KATHERINE_EQUIP_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "katherine_equip"));
	public final static TagKey<Item> FIORA_FOOD_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "fiora_food"));
	public final static TagKey<Item> KATHERINE_FOOD_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "katherine_food"));
	public final static TagKey<Item> FIORA_TAME_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "fiora_tame"));
	public final static TagKey<Item> KATHERINE_TAME_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "katherine_tame"));

	public final static TagKey<Biome> SPAWN_NORMAL_TAG = TagKey.create(Registries.BIOME, new ResourceLocation(MODID, "spawn_normal"));
	public final static TagKey<Biome> SPAWN_ICY_TAG = TagKey.create(Registries.BIOME, new ResourceLocation(MODID, "spawn_icy"));

	// Make sure the static initialization is invoked before the registration phase is done
	public static void invokeStaticFields() {
    	MobZItems.BOSS_INGOT.getClass();
    	MobZBlocks.BOSS_BLOCK.getClass();
    	MobZEntities.BOSS_ZOMBIE.getClass();
    	MobZArmors.BOSS_BOOTS.getClass();
    	MobZWeapons.BOSS_SWORD.getClass();
    	MobZSounds.MEDIVEALSOUNDEVENT.getClass();
    	MobZIcons.headNames.getClass();
    	MobZTabs.tab.getClass();
    	MobZTabs.eggs.getClass();
	}
}
