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
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZTabs.tab);

    public static final Tier BOSS_MATERIAL = new SwordBossMaterial();
    public static final Tier ARMORED_MATERIAL = new ArmoredSwordMaterial();
    public static final Tier SWORDMATERIAL = new SwordMaterial();
    public static final Tier AXEMATERIAL = new AxeMaterial();
    public static final Tier POISONSWORDMATERIAL = new PoisonSwordMaterial();
    public static final Tier WITHERSWORDMATERIAL = new WitherSwordMaterial();
    public static final Tier DEBUGOMAT = new DebugoMat();

    public static final Supplier<Item> Axe = MobZ.platform.registerItem("axe", () -> new AxeBase(AXEMATERIAL, defaultItemProp));
    public static final Supplier<Item> ArmoredSword = MobZ.platform.registerItem("armored_sword", () -> new ArmoredSwordBase(ARMORED_MATERIAL, defaultItemProp));
    public static final Supplier<Item> BossSword = MobZ.platform.registerItem("boss_sword", () -> new BossSwordBase(BOSS_MATERIAL, defaultItemProp));
    public static final Supplier<Item> Debuger = MobZ.platform.registerItem("debuger", () -> new Debugo(DEBUGOMAT, defaultItemProp));
    public static final Supplier<Item> FrozenSword = MobZ.platform.registerItem("frozen_sword", () -> new FrozenSwordBase(POISONSWORDMATERIAL, defaultItemProp));
    public static final Supplier<Item> PoisonSword = MobZ.platform.registerItem("poison_sword", () -> new PoisonSwordBase(POISONSWORDMATERIAL, defaultItemProp));
    public static final Supplier<Item> Sword = MobZ.platform.registerItem("sword", () -> new SwordBase(SWORDMATERIAL, defaultItemProp));
    public static final Supplier<Item> VSword = MobZ.platform.registerItem("v_sword", () -> new VSwordBase(WITHERSWORDMATERIAL, defaultItemProp));
    public static final Supplier<Item> WitherSword = MobZ.platform.registerItem("wither_sword", () -> new WitherSwordBase(WITHERSWORDMATERIAL, defaultItemProp));
}
