package net.mobz;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
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

	// ItemGroup
	public final static CreativeModeTab tab = StaticAPIWrapper.instance.tab(
			new ResourceLocation(MODID, "glomod"), () -> new ItemStack(MobZBlocks.BOSS_TROPHY));

	public final static CreativeModeTab eggs = StaticAPIWrapper.instance.tab(
			new ResourceLocation(MODID, "glomodegg"), () -> new ItemStack(MobZItems.SHOWEGG));

	// Tags
	public final static ResourceLocation TOAD_FOOD = new ResourceLocation(MODID, "toad_food");
	public final static ResourceLocation TOAD_TARGET = new ResourceLocation(MODID, "toad_target");

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
}
