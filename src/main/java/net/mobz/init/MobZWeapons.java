package net.mobz.init;

import net.minecraft.world.item.Tier;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.item.weapon.ArmoredSwordBase;
import net.mobz.item.weapon.ArmoredSwordMaterial;
import net.mobz.item.weapon.AxeBase;
import net.mobz.item.weapon.AxeMaterial;
import net.mobz.item.weapon.BossSwordBase;
import net.mobz.item.weapon.Debugo;
import net.mobz.item.weapon.DebugoMat;
import net.mobz.item.weapon.FrozenSwordBase;
import net.mobz.item.weapon.PoisonSwordBase;
import net.mobz.item.weapon.PoisonSwordMaterial;
import net.mobz.item.weapon.SwordBase;
import net.mobz.item.weapon.SwordBossMaterial;
import net.mobz.item.weapon.SwordMaterial;
import net.mobz.item.weapon.VSwordBase;
import net.mobz.item.weapon.WitherSwordBase;
import net.mobz.item.weapon.WitherSwordMaterial;

public class MobZWeapons {
	public static final Item.Properties defaultItemProp = new Item.Properties();

    public static final Tier BOSS_MATERIAL = new SwordBossMaterial();
    public static final Tier ARMORED_MATERIAL = new ArmoredSwordMaterial();
    public static final Tier RAINBOW_MATERIAL = new SwordMaterial();
    public static final Tier ERAGONS_MATERIAL = new AxeMaterial();
    public static final Tier POISON_MATERIAL = new PoisonSwordMaterial();
    public static final Tier WITHER_MATERIAL = new WitherSwordMaterial();

    public static final Supplier<Item> ERAGONS_AXE = MobZ.platform.registerItem("eragons_axe", MobZTabs.tab, () -> new AxeBase(ERAGONS_MATERIAL, defaultItemProp));
    public static final Supplier<Item> ARMORED_SWORD = MobZ.platform.registerItem("armored_sword", MobZTabs.tab, () -> new ArmoredSwordBase(ARMORED_MATERIAL, defaultItemProp));
    public static final Supplier<Item> BOSS_SWORD = MobZ.platform.registerItem("boss_sword", MobZTabs.tab, () -> new BossSwordBase(BOSS_MATERIAL, defaultItemProp));
    public static final Supplier<Item> Debuger;
    public static final Supplier<Item> FROZEN_SWORD = MobZ.platform.registerItem("frozen_sword", MobZTabs.tab, () -> new FrozenSwordBase(POISON_MATERIAL, defaultItemProp));
    public static final Supplier<Item> POISON_SWORD = MobZ.platform.registerItem("poison_sword", MobZTabs.tab, () -> new PoisonSwordBase(POISON_MATERIAL, defaultItemProp));
    public static final Supplier<Item> RAINBOW_SWORD = MobZ.platform.registerItem("rainbow_sword", MobZTabs.tab, () -> new SwordBase(RAINBOW_MATERIAL, defaultItemProp));
    public static final Supplier<Item> STONE_TOMAHAWK = MobZ.platform.registerItem("stone_tomahawk", MobZTabs.tab, () -> new VSwordBase(WITHER_MATERIAL, defaultItemProp));
    public static final Supplier<Item> WITHER_SWORD = MobZ.platform.registerItem("wither_sword", MobZTabs.tab, () -> new WitherSwordBase(WITHER_MATERIAL, defaultItemProp));

    static {
    	if (MobZ.isDebugMode) {
        	Tier DEBUGOMAT = new DebugoMat();
    		Debuger = MobZ.platform.registerItem("debuger", MobZTabs.tab, () -> new Debugo(DEBUGOMAT, defaultItemProp));
    	} else {
    		Debuger = null;
    	}
    }
}
