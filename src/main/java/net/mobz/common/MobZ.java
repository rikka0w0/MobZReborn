package net.mobz.common;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class MobZ {
	public static final String MODID = "mobz";

	// ItemGroup
	public final static ItemGroup tab = new ItemGroup("itemGroup.mobz.glomod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MobZArmors.boss_helmet);
        }
    };

	public final static ItemGroup eggs = new ItemGroup("itemGroup.mobz.glomodegg") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MobZItems.SHOWEGG);
        }
    };

	public static void registerAll(IRegistryWrapper regWrapper) {
    	MobZItems.registerAll(regWrapper);
    	MobZBlocks.registerAll(regWrapper);
    	MobZEntities.registerAll(regWrapper);
    	MobZArmors.registerAll(regWrapper);
    	MobZWeapons.registerAll(regWrapper);
    	MobZSounds.registerAll(regWrapper);
    	MobZIcons.registerAll(regWrapper);
	}
}
