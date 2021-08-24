package net.mobz.init;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
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
import net.mobz.portable.StaticAPIWrapper;

public class MobZWeapons {
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZ.tab);

    public static final Tier BOSS_MATERIAL = new SwordBossMaterial();
    public static final Tier ARMORED_MATERIAL = new ArmoredSwordMaterial();
    public static final Tier SWORDMATERIAL = new SwordMaterial();
    public static final Tier AXEMATERIAL = new AxeMaterial();
    public static final Tier POISONSWORDMATERIAL = new PoisonSwordMaterial();
    public static final Tier WITHERSWORDMATERIAL = new WitherSwordMaterial();
    public static final Tier DEBUGOMAT = new DebugoMat();

    public static final Item Axe = new AxeBase(AXEMATERIAL, defaultItemProp);
    public static final Item ArmoredSword = new ArmoredSwordBase(ARMORED_MATERIAL, defaultItemProp);
    public static final Item BossSword = new BossSwordBase(BOSS_MATERIAL, defaultItemProp);
    public static final Item Debuger = new Debugo(DEBUGOMAT, defaultItemProp);
    public static final Item FrozenSword = new FrozenSwordBase(POISONSWORDMATERIAL, defaultItemProp);
    public static final Item PoisonSword = new PoisonSwordBase(POISONSWORDMATERIAL, defaultItemProp);
    public static final Item Sword = new SwordBase(SWORDMATERIAL, defaultItemProp);
    public static final Item VSword = new VSwordBase(WITHERSWORDMATERIAL, defaultItemProp);
    public static final Item WitherSword = new WitherSwordBase(WITHERSWORDMATERIAL, defaultItemProp);

	static {
		StaticAPIWrapper.instance.register("axe", Axe);
		StaticAPIWrapper.instance.register("armored_sword", ArmoredSword);
		StaticAPIWrapper.instance.register("boss_sword", BossSword);
		StaticAPIWrapper.instance.register("debuger", Debuger);
		StaticAPIWrapper.instance.register("frozen_sword", FrozenSword);
		StaticAPIWrapper.instance.register("poison_sword", PoisonSword);
		StaticAPIWrapper.instance.register("sword", Sword);
		StaticAPIWrapper.instance.register("v_sword", VSword);
		StaticAPIWrapper.instance.register("wither_sword", WitherSword);
	}
}
