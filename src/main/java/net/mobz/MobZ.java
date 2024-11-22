package net.mobz;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

	// Make sure the static initialization is invoked before the registration phase is done
	public static void invokeStaticFields() {
		MobZDataComponents.DRYING_NUMBER.getClass();
    	MobZItems.BOSS_INGOT.getClass();
    	MobZBlocks.BOSS_BLOCK.getClass();
    	MobZEntities.BOSS_ZOMBIE.getClass();
    	MobZArmors.BOSS_BOOTS.getClass();
    	MobZWeapons.BOSS_SWORD.getClass();
    	MobZSounds.MEDIVEAL_MUSIC.getClass();
    	MobZIcons.headNames.getClass();
    	MobZTabs.tab.getClass();
    	MobZTabs.eggs.getClass();
	}

	public static ResourceLocation resLoc(String path) {
		return ResourceLocation.fromNamespaceAndPath(MobZ.MODID, path);
	}

	public static <T> ResourceKey<T> resKey(ResourceKey<? extends Registry<T>> registry, String path) {
		return ResourceKey.create(registry, resLoc(path));
	}
}
