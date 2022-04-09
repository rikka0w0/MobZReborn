package net.mobz;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;
import net.mobz.portable.StaticAPIWrapper;

public class MobZ {
	public static final String MODID = "mobz";

	// Configs
	public static Configs configs = null;

	// ItemGroup
	public final static CreativeModeTab tab = StaticAPIWrapper.instance.tab(
			new ResourceLocation(MODID, "glomod"), () -> new ItemStack(MobZBlocks.BOSS_TROPHY));

	public final static CreativeModeTab eggs = StaticAPIWrapper.instance.tab(
			new ResourceLocation(MODID, "glomodegg"), () -> new ItemStack(MobZItems.SHOWEGG));

	// Tags
	public final static ResourceLocation TOAD_FOOD = new ResourceLocation(MODID, "toad_food");
	public final static ResourceLocation TOAD_TARGET = new ResourceLocation(MODID, "toad_target");
	public static final TagKey<Item> TOAD_FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, TOAD_FOOD);
	public static final TagKey<EntityType<?>> TOAD_TARGET_TAG = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, TOAD_TARGET);

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
