package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;

import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.item.weapon.*;

public class MobZWeapons {
	public static final Item.Properties defaultItemProp = new Item.Properties();

	public static final Tier BOSS_MATERIAL = SimpleTier.of(561, 1.0F, 8, 15, MobZItems.BOSS_INGOT);
	public static final Tier ARMORED_MATERIAL = SimpleTier.of(251, 1.0F, 6, 12);
	public static final Tier ERAGONS_MATERIAL = SimpleTier.of(761, 1.0F, 11, 30);
	public static final Tier POISON_MATERIAL = SimpleTier.of(251, 1.0F, 4, 12);
	public static final Tier RAINBOW_MATERIAL = SimpleTier.of(826, 1.0F, 7, 30, () -> Items.DRAGON_EGG);
	public static final Tier WITHER_MATERIAL = SimpleTier.of(561, 1.0F, 5, 12);
	public final static Tier DEBUG_MATERIAL = SimpleTier.of(2020, 5.0F, 1995, 30, () -> Items.DRAGON_EGG);

	public static final Supplier<Item> ERAGONS_AXE = MobZ.platform.registerItem("eragons_axe", MobZTabs.tab,
			() -> new AxeBase(ERAGONS_MATERIAL, defaultItemProp));

	public static final Supplier<Item> ARMORED_SWORD = MobZ.platform.registerItem("armored_sword", MobZTabs.tab,
			() -> new ArmoredSwordBase(ARMORED_MATERIAL, defaultItemProp));

	public static final Supplier<Item> BOSS_SWORD = MobZ.platform.registerItem("boss_sword", MobZTabs.tab,
			() -> new BossSwordBase(BOSS_MATERIAL, defaultItemProp));


	public static final Supplier<Item> FROZEN_SWORD = MobZ.platform.registerItem("frozen_sword", MobZTabs.tab,
			() -> new FrozenSwordBase(POISON_MATERIAL, defaultItemProp));

	public static final Supplier<Item> POISON_SWORD = MobZ.platform.registerItem("poison_sword", MobZTabs.tab,
			() -> new PoisonSwordBase(POISON_MATERIAL, defaultItemProp));

	public static final Supplier<Item> RAINBOW_SWORD = MobZ.platform.registerItem("rainbow_sword", MobZTabs.tab,
			() -> new SwordBase(RAINBOW_MATERIAL, defaultItemProp));

	public static final Supplier<Item> STONE_TOMAHAWK = MobZ.platform.registerItem("stone_tomahawk", MobZTabs.tab,
			() -> new VSwordBase(WITHER_MATERIAL, defaultItemProp));

	public static final Supplier<Item> WITHER_SWORD = MobZ.platform.registerItem("wither_sword", MobZTabs.tab,
			() -> new WitherSwordBase(WITHER_MATERIAL, defaultItemProp));

	public static final Supplier<Item> Debuger;
	static {
		if (MobZ.isDebugMode) {
			Debuger = MobZ.platform.registerItem("debuger", MobZTabs.tab,
					() -> new Debugo(DEBUG_MATERIAL, defaultItemProp));
		} else {
			Debuger = null;
		}
	}
}
