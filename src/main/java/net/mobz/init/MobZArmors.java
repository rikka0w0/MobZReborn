package net.mobz.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;
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
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZ.tab);
	
    public static final ArmorMaterial BOSSAPPARE = new BossArmorMaterial();
    public static final ArmorMaterial LIFEAPPARE = new LifeArmorMaterial();
    public static final ArmorMaterial SPEEDAPPARE = new SpeedShoeMaterial();
    public static final ArmorMaterial SPEEDAPPARE2 = new SpeedShoeMaterial2();
    public static final ArmorMaterial AMAT = new AMaterial();

    public static final AmatArmorBase amat_helmet = new AmatArmorBase(AMAT, EquipmentSlot.HEAD, defaultItemProp);
    public static final AmatArmorBase amat_chestplate = new AmatArmorBase(AMAT, EquipmentSlot.CHEST, defaultItemProp);
    public static final AmatArmorBase amat_leggings = new AmatArmorBase(AMAT, EquipmentSlot.LEGS, defaultItemProp);
    public static final AmatArmorBase amat_boots = new AmatArmorBase(AMAT, EquipmentSlot.FEET, defaultItemProp);
    public static final BossArmorBase boss_helmet = new BossArmorBase(BOSSAPPARE, EquipmentSlot.HEAD, defaultItemProp);
    public static final BossArmorBase boss_chestplate = new BossArmorBase(BOSSAPPARE, EquipmentSlot.CHEST, defaultItemProp);
    public static final BossArmorBase boss_leggings = new BossArmorBase(BOSSAPPARE, EquipmentSlot.LEGS, defaultItemProp);
    public static final BossArmorBase boss_boots = new BossArmorBase(BOSSAPPARE, EquipmentSlot.FEET, defaultItemProp);
    public static final LifeArmorBase life_helmet = new LifeArmorBase(LIFEAPPARE, EquipmentSlot.HEAD, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_chestplate = new LifeArmorBase(LIFEAPPARE, EquipmentSlot.CHEST, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_leggings = new LifeArmorBase(LIFEAPPARE, EquipmentSlot.LEGS, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_boots = new LifeArmorBase(LIFEAPPARE, EquipmentSlot.FEET, defaultItemProp, 3.0D);
    public static final SpeedShoeBase speed_boots = new SpeedShoeBase(SPEEDAPPARE, EquipmentSlot.FEET, defaultItemProp, 0.02D);
    public static final SpeedShoeBase speed2_boots = new SpeedShoeBase(SPEEDAPPARE2, EquipmentSlot.FEET, defaultItemProp, 0.03D);

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("amat_helmet", amat_helmet);
		registry.register("amat_chestplate", amat_chestplate);
		registry.register("amat_leggings", amat_leggings);
		registry.register("amat_boots", amat_boots);
		registry.register("boss_helmet", boss_helmet);
		registry.register("boss_chestplate", boss_chestplate);
		registry.register("boss_leggings", boss_leggings);
		registry.register("boss_boots", boss_boots);
		registry.register("life_helmet", life_helmet);
		registry.register("life_chestplate", life_chestplate);
		registry.register("life_leggings", life_leggings);
		registry.register("life_boots", life_boots);
		registry.register("speed_boots", speed_boots);
		registry.register("speed2_boots", speed2_boots);
	}
}
