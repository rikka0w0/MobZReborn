package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.ArmorItem;
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
	public static final Item.Properties defaultItemProp = new Item.Properties();

    public static final ArmorMaterial BOSSAPPARE = new BossArmorMaterial();
    public static final ArmorMaterial LIFEAPPARE = new LifeArmorMaterial();
    public static final ArmorMaterial SPEEDAPPARE = new SpeedShoeMaterial();
    public static final ArmorMaterial SPEEDAPPARE2 = new SpeedShoeMaterial2();
    public static final ArmorMaterial AMAT = new AMaterial();

    public static final Supplier<AmatArmorBase> amat_helmet = MobZ.platform.registerItem("amat_helmet", MobZTabs.tab, () -> new AmatArmorBase(AMAT, ArmorItem.Type.HELMET, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_chestplate = MobZ.platform.registerItem("amat_chestplate", MobZTabs.tab, () -> new AmatArmorBase(AMAT, ArmorItem.Type.CHESTPLATE, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_leggings = MobZ.platform.registerItem("amat_leggings", MobZTabs.tab, () -> new AmatArmorBase(AMAT, ArmorItem.Type.LEGGINGS, defaultItemProp));
    public static final Supplier<AmatArmorBase> amat_boots = MobZ.platform.registerItem("amat_boots", MobZTabs.tab, () -> new AmatArmorBase(AMAT, ArmorItem.Type.BOOTS, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_helmet = MobZ.platform.registerItem("boss_helmet", MobZTabs.tab, () -> new BossArmorBase(BOSSAPPARE, ArmorItem.Type.HELMET, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_chestplate = MobZ.platform.registerItem("boss_chestplate", MobZTabs.tab, () -> new BossArmorBase(BOSSAPPARE, ArmorItem.Type.CHESTPLATE, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_leggings = MobZ.platform.registerItem("boss_leggings", MobZTabs.tab, () -> new BossArmorBase(BOSSAPPARE, ArmorItem.Type.LEGGINGS, defaultItemProp));
    public static final Supplier<BossArmorBase> boss_boots = MobZ.platform.registerItem("boss_boots", MobZTabs.tab, () -> new BossArmorBase(BOSSAPPARE, ArmorItem.Type.BOOTS, defaultItemProp));
    public static final Supplier<LifeArmorBase> life_helmet = MobZ.platform.registerItem("life_helmet", MobZTabs.tab, () -> new LifeArmorBase(LIFEAPPARE, ArmorItem.Type.HELMET, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_chestplate = MobZ.platform.registerItem("life_chestplate", MobZTabs.tab, () -> new LifeArmorBase(LIFEAPPARE, ArmorItem.Type.CHESTPLATE, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_leggings = MobZ.platform.registerItem("life_leggings", MobZTabs.tab, () -> new LifeArmorBase(LIFEAPPARE, ArmorItem.Type.LEGGINGS, defaultItemProp, 3.0D));
    public static final Supplier<LifeArmorBase> life_boots = MobZ.platform.registerItem("life_boots", MobZTabs.tab, () -> new LifeArmorBase(LIFEAPPARE, ArmorItem.Type.BOOTS, defaultItemProp, 3.0D));
    public static final Supplier<SpeedShoeBase> speed_boots = MobZ.platform.registerItem("speed_boots", MobZTabs.tab, () -> new SpeedShoeBase(SPEEDAPPARE, ArmorItem.Type.BOOTS, defaultItemProp, 0.02D));
    public static final Supplier<SpeedShoeBase> speed2_boots = MobZ.platform.registerItem("speed2_boots", MobZTabs.tab, () -> new SpeedShoeBase(SPEEDAPPARE2, ArmorItem.Type.BOOTS, defaultItemProp, 0.03D));
}
