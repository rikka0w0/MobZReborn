package net.mobz.init;

import net.mobz.MobZ;
import net.mobz.item.SimpleItem;

public class MobZIcons {
	public final static String[] headNames = {
			"head_start",
		    "head_bowman",
		    "head_archer",
		    "head_fast_zombie",
		    "head_baby_ravager",
		    "head_frost_blaze",
		    "head_boss_zombie",
		    "head_frost_creeper",
		    "head_cookie_creeper",
		    "head_dwarf",
		    "head_ender",
		    "head_ender_knight",
		    "head_ender_zombie",
		    "head_fiora",
		    "head_stone_golem",
		    "head_ice_golem",
		    "head_charles",
		    "head_william",
		    "head_andriu",
		    "head_katherine",
		    "head_templar",
		    "head_warrior",
		    "head_lord_of_darkness",
		    "head_lava_golem",
		    "head_zombie_mage",
		    "head_metal_golem",
		    "head_pillager_boss",
		    "head_soul_creeper",
		    "head_lost_skeleton",
		    "head_overgrown_skeleton",
		    "head_boss_skeleton",
		    "head_nether_skeleton",
		    "head_blue_spider",
		    "head_purple_spider",
		    "head_iron_steve",
		    "head_withender",
		    "head_wither_blaze"
	};

	static {
		for (String headName: headNames) {
			MobZ.platform.registerItem(headName, null, SimpleItem::new, null);
		}
	}
}
