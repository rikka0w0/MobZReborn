package net.mobz.init;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;
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
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZ.tab);

    public static final IItemTier BOSS_MATERIAL = new SwordBossMaterial();
    public static final IItemTier ARMORED_MATERIAL = new ArmoredSwordMaterial();
    public static final IItemTier SWORDMATERIAL = new SwordMaterial();
    public static final IItemTier AXEMATERIAL = new AxeMaterial();
    public static final IItemTier POISONSWORDMATERIAL = new PoisonSwordMaterial();
    public static final IItemTier WITHERSWORDMATERIAL = new WitherSwordMaterial();
    public static final IItemTier DEBUGOMAT = new DebugoMat();

    public static final Item Axe = new AxeBase(AXEMATERIAL, defaultItemProp);
    public static final Item ArmoredSword = new ArmoredSwordBase(ARMORED_MATERIAL, defaultItemProp);
    public static final Item BossSword = new BossSwordBase(BOSS_MATERIAL, defaultItemProp);
    public static final Item Debuger = new Debugo(DEBUGOMAT, defaultItemProp);
    public static final Item FrozenSword = new FrozenSwordBase(POISONSWORDMATERIAL, defaultItemProp);
    public static final Item PoisonSword = new PoisonSwordBase(POISONSWORDMATERIAL, defaultItemProp);
    public static final Item Sword = new SwordBase(SWORDMATERIAL, defaultItemProp);
    public static final Item VSword = new VSwordBase(WITHERSWORDMATERIAL, defaultItemProp);
    public static final Item WitherSword = new WitherSwordBase(WITHERSWORDMATERIAL, defaultItemProp);

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("axe", Axe);
		registry.register("armored_sword", ArmoredSword);
		registry.register("boss_sword", BossSword);
		registry.register("debuger", Debuger);
		registry.register("frozen_sword", FrozenSword);
		registry.register("poison_sword", PoisonSword);
		registry.register("sword", Sword);
		registry.register("v_sword", VSword);
		registry.register("wither_sword", WitherSword);
	}
}
