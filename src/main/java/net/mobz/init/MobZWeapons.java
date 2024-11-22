package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;

import net.mobz.MobZ;
import net.mobz.item.weapon.*;

public class MobZWeapons {
	public static final Tier BOSS_MATERIAL = SimpleTier.of(561, 1.0F, 8, 15, MobZItems.BOSS_INGOT);
	public static final Tier ARMORED_MATERIAL = SimpleTier.of(251, 1.0F, 6, 12);
	public static final Tier ERAGONS_MATERIAL = SimpleTier.of(761, 1.0F, 11, 30);
	public static final Tier POISON_MATERIAL = SimpleTier.of(251, 1.0F, 4, 12);
	public static final Tier RAINBOW_MATERIAL = SimpleTier.of(826, 1.0F, 7, 30, () -> Items.DRAGON_EGG);
	public static final Tier WITHER_MATERIAL = SimpleTier.of(561, 1.0F, 5, 12);
	public final static Tier DEBUG_MATERIAL = SimpleTier.of(2020, 5.0F, 1995, 30, () -> Items.DRAGON_EGG);

	public static final Supplier<Item> ERAGONS_AXE = MobZItems.registerItem("eragons_axe",
			(props) -> new AxeBase(ERAGONS_MATERIAL, props));

	public static final Supplier<Item> ARMORED_SWORD = MobZItems.registerItem("armored_sword",
			(props) -> new ArmoredSwordBase(ARMORED_MATERIAL, props));

	public static final Supplier<Item> BOSS_SWORD = MobZItems.registerItem("boss_sword",
			(props) -> new BossSwordBase(BOSS_MATERIAL, props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));


	public static final Supplier<Item> FROZEN_SWORD = MobZItems.registerItem("frozen_sword",
			(props) -> new FrozenSwordBase(POISON_MATERIAL, props));

	public static final Supplier<Item> POISON_SWORD = MobZItems.registerItem("poison_sword",
			(props) -> new PoisonSwordBase(POISON_MATERIAL, props));

	public static final Supplier<Item> RAINBOW_SWORD = MobZItems.registerItem("rainbow_sword",
			(props) -> new SwordBase(RAINBOW_MATERIAL, props));

	public static final Supplier<Item> STONE_TOMAHAWK = MobZItems.registerItem("stone_tomahawk",
			(props) -> new VSwordBase(WITHER_MATERIAL, props));

	public static final Supplier<Item> WITHER_SWORD = MobZItems.registerItem("wither_sword",
			(props) -> new WitherSwordBase(WITHER_MATERIAL, props));

	public static final Supplier<Item> Debuger;
	static {
		if (MobZ.isDebugMode) {
			Debuger = MobZItems.registerItem("debuger",
					(props) -> new Debugo(DEBUG_MATERIAL, props));
		} else {
			Debuger = null;
		}
	}
}
