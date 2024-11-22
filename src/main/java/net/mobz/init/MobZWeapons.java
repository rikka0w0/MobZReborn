package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.mobz.MobZ;
import net.mobz.item.weapon.*;
import net.mobz.tags.MobZBlockTags;
import net.mobz.tags.MobZItemTags;

public class MobZWeapons {
	public static final ToolMaterial BOSS_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_BOSS_TOOL, 561, 1.0F, 8, 15, MobZItemTags.BOSS_TOOL_MATERIALS);
	public static final ToolMaterial ARMORED_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_ARMORED_TOOL, 251, 1.0F, 6, 12, MobZItemTags.ARMORED_TOOL_MATERIALS);
	public static final ToolMaterial ERAGONS_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_ERAGONS_TOOL, 761, 1.0F, 11, 30, MobZItemTags.ERAGONS_TOOL_MATERIALS);
	public static final ToolMaterial POISON_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_POISON_TOOL, 251, 1.0F, 4, 12, MobZItemTags.POISON_TOOL_MATERIALS);
	public static final ToolMaterial RAINBOW_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_RAINBOW_TOOL, 826, 1.0F, 7, 30, MobZItemTags.RAINBOW_TOOL_MATERIALS);
	public static final ToolMaterial WITHER_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_WITHER_TOOL, 561, 1.0F, 5, 12, MobZItemTags.WITHER_TOOL_MATERIALS);
	public final static ToolMaterial DEBUG_MATERIAL =
			new ToolMaterial(MobZBlockTags.INCORRECT_FOR_DEBUG_TOOL, 2020, 5.0F, 1995, 30, MobZItemTags.DEBUG_TOOL_MATERIALS);

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
			(props) -> new StoneTomahawk(WITHER_MATERIAL, props));

	public static final Supplier<Item> WITHER_SWORD = MobZItems.registerItem("wither_sword",
			(props) -> new WitherSwordBase(WITHER_MATERIAL, props));

	public static final Supplier<Item> Debuger;
	static {
		if (MobZ.isDebugMode) {
			Debuger = MobZItems.registerItem("debuger", (props) -> new Debugo(DEBUG_MATERIAL, props));
		} else {
			Debuger = null;
		}
	}
}
