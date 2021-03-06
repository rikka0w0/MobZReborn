package net.mobz;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
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

	// Configs
	public static Configs configs = null;

	// Tags
	public final static TagKey<Item> TOAD_FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "toad_food"));
	public final static TagKey<EntityType<?>> TOAD_TARGET_TAG = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "toad_target"));
	public final static TagKey<Item> FIORA_EQUIP_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "fiora_equip"));
	public final static TagKey<Item> KATHERINE_EQUIP_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "katherine_equip"));
	public final static TagKey<Item> FIORA_FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "fiora_food"));
	public final static TagKey<Item> KATHERINE_FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "katherine_food"));
	public final static TagKey<Item> FIORA_TAME_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "fiora_tame"));
	public final static TagKey<Item> KATHERINE_TAME_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "katherine_tame"));

	// Make sure the static initialization is invoked before the registration phase is done
	public static void invokeStaticFields() {
    	MobZItems.BOSS_INGOT.getClass();
    	MobZBlocks.BOSS_BLOCK.getClass();
    	MobZEntities.BOSS.getClass();
    	MobZArmors.boss_boots.getClass();
    	MobZWeapons.BossSword.getClass();
    	MobZSounds.MEDIVEALSOUNDEVENT.getClass();
    	MobZIcons.BOSSHEAD.getClass();
	}

	public static void initConfig() {
		MobZ.configs = AutoConfig.register(Configs.class, JanksonConfigSerializer::new).getConfig();
	}
}
