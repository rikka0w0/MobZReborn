package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.item.armor.AmatArmorBase;
import net.mobz.item.armor.BossArmorBase;
import net.mobz.item.armor.LifeArmorBase;
import net.mobz.item.armor.SpeedShoeBase;

public class MobZArmors {
	public static final Item.Properties defaultItemProp = new Item.Properties();

	public static final Supplier<AmatArmorBase> AMAT_HELMET = MobZ.platform.registerItem("amat_helmet", MobZTabs.tab, () -> new AmatArmorBase(ArmorItem.Type.HELMET, defaultItemProp));
	public static final Supplier<AmatArmorBase> AMAT_CHESTPLATE = MobZ.platform.registerItem("amat_chestplate", MobZTabs.tab, () -> new AmatArmorBase(ArmorItem.Type.CHESTPLATE, defaultItemProp));
	public static final Supplier<AmatArmorBase> AMAT_LEGGINGS = MobZ.platform.registerItem("amat_leggings", MobZTabs.tab, () -> new AmatArmorBase(ArmorItem.Type.LEGGINGS, defaultItemProp));
	public static final Supplier<AmatArmorBase> AMAT_BOOTS = MobZ.platform.registerItem("amat_boots", MobZTabs.tab, () -> new AmatArmorBase(ArmorItem.Type.BOOTS, defaultItemProp));
	public static final Supplier<BossArmorBase> BOSS_HELMET = MobZ.platform.registerItem("boss_helmet", MobZTabs.tab, () -> new BossArmorBase(ArmorItem.Type.HELMET, defaultItemProp));
	public static final Supplier<BossArmorBase> BOSS_CHESTPLATE = MobZ.platform.registerItem("boss_chestplate", MobZTabs.tab, () -> new BossArmorBase(ArmorItem.Type.CHESTPLATE, defaultItemProp));
	public static final Supplier<BossArmorBase> BOSS_LEGGINGS = MobZ.platform.registerItem("boss_leggings", MobZTabs.tab, () -> new BossArmorBase(ArmorItem.Type.LEGGINGS, defaultItemProp));
	public static final Supplier<BossArmorBase> BOSS_BOOTS = MobZ.platform.registerItem("boss_boots", MobZTabs.tab, () -> new BossArmorBase(ArmorItem.Type.BOOTS, defaultItemProp));
	public static final Supplier<LifeArmorBase> LIFE_HELMET = MobZ.platform.registerItem("life_helmet", MobZTabs.tab, () -> new LifeArmorBase(ArmorItem.Type.HELMET, defaultItemProp));
	public static final Supplier<LifeArmorBase> LIFE_CHESTPLATE = MobZ.platform.registerItem("life_chestplate", MobZTabs.tab, () -> new LifeArmorBase(ArmorItem.Type.CHESTPLATE, defaultItemProp));
	public static final Supplier<LifeArmorBase> LIFE_LEGGINGS = MobZ.platform.registerItem("life_leggings", MobZTabs.tab, () -> new LifeArmorBase(ArmorItem.Type.LEGGINGS, defaultItemProp));
	public static final Supplier<LifeArmorBase> LIFE_BOOTS = MobZ.platform.registerItem("life_boots", MobZTabs.tab, () -> new LifeArmorBase(ArmorItem.Type.BOOTS, defaultItemProp));

	public static final Supplier<SpeedShoeBase> SPEED_BOOTS = MobZ.platform.registerItem("speed_boots", MobZTabs.tab, () ->
		new SpeedShoeBase(SpeedShoeBase.MATERIAL1, ArmorItem.Type.BOOTS, defaultItemProp, 0.02D));
	public static final Supplier<SpeedShoeBase> SPEED2_BOOTS = MobZ.platform.registerItem("speed2_boots", MobZTabs.tab, () ->
		new SpeedShoeBase(SpeedShoeBase.MATERIAL2, ArmorItem.Type.BOOTS, defaultItemProp, 0.03D));
}
