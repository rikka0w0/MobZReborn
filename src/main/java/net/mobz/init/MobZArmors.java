package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.item.armor.AMaterial;
import net.mobz.item.armor.AmatArmorBase;
import net.mobz.item.armor.BossArmorBase;
import net.mobz.item.armor.BossArmorMaterial;
import net.mobz.item.armor.LifeArmorBase;
import net.mobz.item.armor.LifeArmorMaterial;
import net.mobz.item.armor.SpeedShoeBase;
import net.mobz.item.armor.SpeedShoeMaterial;
import net.mobz.item.armor.SpeedShoeMaterial2;

public class MobZArmors {
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZTabs.tab);

    public static final ArmorMaterial BOSSAPPARE = new BossArmorMaterial();
    public static final ArmorMaterial LIFEAPPARE = new LifeArmorMaterial();
    public static final ArmorMaterial SPEEDAPPARE = new SpeedShoeMaterial();
    public static final ArmorMaterial SPEEDAPPARE2 = new SpeedShoeMaterial2();
    public static final ArmorMaterial AMAT = new AMaterial();

    public static final Supplier<AmatArmorBase> amat_helmet = MobZ.platform.registerItem("amat_helmet", () -> new AmatArmorBase(AMAT, EquipmentSlot.HEAD, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_chestplate = MobZ.platform.registerItem("amat_chestplate", () -> new AmatArmorBase(AMAT, EquipmentSlot.CHEST, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_leggings = MobZ.platform.registerItem("amat_leggings", () -> new AmatArmorBase(AMAT, EquipmentSlot.LEGS, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_boots = MobZ.platform.registerItem("amat_boots", () -> new AmatArmorBase(AMAT, EquipmentSlot.FEET, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_helmet = MobZ.platform.registerItem("boss_helmet", () -> new BossArmorBase(BOSSAPPARE, EquipmentSlot.HEAD, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_chestplate = MobZ.platform.registerItem("boss_chestplate", () -> new BossArmorBase(BOSSAPPARE, EquipmentSlot.CHEST, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_leggings = MobZ.platform.registerItem("boss_leggings", () -> new BossArmorBase(BOSSAPPARE, EquipmentSlot.LEGS, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_boots = MobZ.platform.registerItem("boss_boots", () -> new BossArmorBase(BOSSAPPARE, EquipmentSlot.FEET, defaultItemProp));
    public static final Supplier<LifeArmorBase> life_helmet = MobZ.platform.registerItem("life_helmet", () -> new LifeArmorBase(LIFEAPPARE, EquipmentSlot.HEAD, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_chestplate = MobZ.platform.registerItem("life_chestplate", () -> new LifeArmorBase(LIFEAPPARE, EquipmentSlot.CHEST, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_leggings = MobZ.platform.registerItem("life_leggings", () -> new LifeArmorBase(LIFEAPPARE, EquipmentSlot.LEGS, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_boots = MobZ.platform.registerItem("life_boots", () -> new LifeArmorBase(LIFEAPPARE, EquipmentSlot.FEET, defaultItemProp, 3.0D));
    public static final Supplier<SpeedShoeBase> speed_boots = MobZ.platform.registerItem("speed_boots", () -> new SpeedShoeBase(SPEEDAPPARE, EquipmentSlot.FEET, defaultItemProp, 0.02D));
    public static final Supplier<SpeedShoeBase> speed2_boots = MobZ.platform.registerItem("speed2_boots", () -> new SpeedShoeBase(SPEEDAPPARE2, EquipmentSlot.FEET, defaultItemProp, 0.03D));
}
