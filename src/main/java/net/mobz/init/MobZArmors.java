package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.equipment.ArmorType;
import net.mobz.item.armor.AmatArmorBase;
import net.mobz.item.armor.BossArmorBase;
import net.mobz.item.armor.LifeArmorBase;
import net.mobz.item.armor.SpeedShoeBase;

public class MobZArmors {
	public static final Supplier<AmatArmorBase> AMAT_HELMET = MobZItems.registerItem("amat_helmet",
			(props) -> new AmatArmorBase(ArmorType.HELMET, props));
	public static final Supplier<AmatArmorBase> AMAT_CHESTPLATE = MobZItems.registerItem("amat_chestplate",
			(props) -> new AmatArmorBase(ArmorType.CHESTPLATE, props));
	public static final Supplier<AmatArmorBase> AMAT_LEGGINGS = MobZItems.registerItem("amat_leggings",
			(props) -> new AmatArmorBase(ArmorType.LEGGINGS, props));
	public static final Supplier<AmatArmorBase> AMAT_BOOTS = MobZItems.registerItem("amat_boots",
			(props) -> new AmatArmorBase(ArmorType.BOOTS, props));
	public static final Supplier<BossArmorBase> BOSS_HELMET = MobZItems.registerItem("boss_helmet",
			(props) -> new BossArmorBase(ArmorType.HELMET,
					props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<BossArmorBase> BOSS_CHESTPLATE = MobZItems.registerItem("boss_chestplate",
			(props) -> new BossArmorBase(ArmorType.CHESTPLATE, props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<BossArmorBase> BOSS_LEGGINGS = MobZItems.registerItem("boss_leggings",
			(props) -> new BossArmorBase(ArmorType.LEGGINGS, props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<BossArmorBase> BOSS_BOOTS = MobZItems.registerItem("boss_boots",
			(props) -> new BossArmorBase(ArmorType.BOOTS, props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<LifeArmorBase> LIFE_HELMET = MobZItems.registerItem("life_helmet",
			(props) -> new LifeArmorBase(ArmorType.HELMET, props));
	public static final Supplier<LifeArmorBase> LIFE_CHESTPLATE = MobZItems.registerItem("life_chestplate",
			(props) -> new LifeArmorBase(ArmorType.CHESTPLATE, props));
	public static final Supplier<LifeArmorBase> LIFE_LEGGINGS = MobZItems.registerItem("life_leggings",
			(props) -> new LifeArmorBase(ArmorType.LEGGINGS, props));
	public static final Supplier<LifeArmorBase> LIFE_BOOTS = MobZItems.registerItem("life_boots",
			(props) -> new LifeArmorBase(ArmorType.BOOTS, props));

	public static final Supplier<SpeedShoeBase> SPEED_BOOTS = MobZItems.registerItem("speed_boots",
			(props) -> new SpeedShoeBase(SpeedShoeBase.MATERIAL1, ArmorType.BOOTS, props, 0.02D));
	public static final Supplier<SpeedShoeBase> SPEED2_BOOTS = MobZItems.registerItem("speed2_boots",
			(props) -> new SpeedShoeBase(SpeedShoeBase.MATERIAL2, ArmorType.BOOTS, props, 0.03D));
}
