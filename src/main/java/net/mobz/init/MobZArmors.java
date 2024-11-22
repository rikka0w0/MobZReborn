package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import net.mobz.item.armor.AmatArmorBase;
import net.mobz.item.armor.BossArmorBase;
import net.mobz.item.armor.LifeArmorBase;
import net.mobz.item.armor.SpeedShoeBase;

public class MobZArmors {
	public static final Item.Properties props = new Item.Properties();

	public static final Supplier<AmatArmorBase> AMAT_HELMET = MobZItems.registerItem("amat_helmet",
			(props) ->new AmatArmorBase(ArmorItem.Type.HELMET, props));
	public static final Supplier<AmatArmorBase> AMAT_CHESTPLATE = MobZItems.registerItem("amat_chestplate",
			(props) ->new AmatArmorBase(ArmorItem.Type.CHESTPLATE, props));
	public static final Supplier<AmatArmorBase> AMAT_LEGGINGS = MobZItems.registerItem("amat_leggings",
			(props) ->new AmatArmorBase(ArmorItem.Type.LEGGINGS, props));
	public static final Supplier<AmatArmorBase> AMAT_BOOTS = MobZItems.registerItem("amat_boots",
			(props) ->new AmatArmorBase(ArmorItem.Type.BOOTS, props));
	public static final Supplier<BossArmorBase> BOSS_HELMET = MobZItems.registerItem("boss_helmet",
			(props) ->new BossArmorBase(ArmorItem.Type.HELMET, props));
	public static final Supplier<BossArmorBase> BOSS_CHESTPLATE = MobZItems.registerItem("boss_chestplate",
			(props) ->new BossArmorBase(ArmorItem.Type.CHESTPLATE, props));
	public static final Supplier<BossArmorBase> BOSS_LEGGINGS = MobZItems.registerItem("boss_leggings",
			(props) ->new BossArmorBase(ArmorItem.Type.LEGGINGS, props));
	public static final Supplier<BossArmorBase> BOSS_BOOTS = MobZItems.registerItem("boss_boots",
			(props) ->new BossArmorBase(ArmorItem.Type.BOOTS, props));
	public static final Supplier<LifeArmorBase> LIFE_HELMET = MobZItems.registerItem("life_helmet",
			(props) ->new LifeArmorBase(ArmorItem.Type.HELMET, props));
	public static final Supplier<LifeArmorBase> LIFE_CHESTPLATE = MobZItems.registerItem("life_chestplate",
			(props) ->new LifeArmorBase(ArmorItem.Type.CHESTPLATE, props));
	public static final Supplier<LifeArmorBase> LIFE_LEGGINGS = MobZItems.registerItem("life_leggings",
			(props) ->new LifeArmorBase(ArmorItem.Type.LEGGINGS, props));
	public static final Supplier<LifeArmorBase> LIFE_BOOTS = MobZItems.registerItem("life_boots",
			(props) ->new LifeArmorBase(ArmorItem.Type.BOOTS, props));

	public static final Supplier<SpeedShoeBase> SPEED_BOOTS = MobZItems.registerItem("speed_boots",
			(props) ->new SpeedShoeBase(SpeedShoeBase.MATERIAL1, ArmorItem.Type.BOOTS, props.durability(21), 0.02D));
	public static final Supplier<SpeedShoeBase> SPEED2_BOOTS = MobZItems.registerItem("speed2_boots",
			(props) ->new SpeedShoeBase(SpeedShoeBase.MATERIAL2, ArmorItem.Type.BOOTS, props.durability(23), 0.03D));
}
