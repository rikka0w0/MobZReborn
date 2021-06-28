package net.mobz.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

import net.mobz.common.IRegistryWrapper;
import net.mobz.item.armor.*;
import net.mobz.common.MobZ;

public class MobZArmors {
	public static final Item.Properties defaultItemProp = new Item.Properties().tab(MobZ.tab);
	
    public static final IArmorMaterial BOSSAPPARE = new BossArmorMaterial();
    public static final IArmorMaterial LIFEAPPARE = new LifeArmorMaterial();
    public static final IArmorMaterial SPEEDAPPARE = new SpeedShoeMaterial();
    public static final IArmorMaterial SPEEDAPPARE2 = new SpeedShoeMaterial2();
    public static final IArmorMaterial AMAT = new AMaterial();

    public static final AmatArmorBase amat_helmet = new AmatArmorBase(AMAT, EquipmentSlotType.HEAD, defaultItemProp);
    public static final AmatArmorBase amat_chestplate = new AmatArmorBase(AMAT, EquipmentSlotType.CHEST, defaultItemProp);
    public static final AmatArmorBase amat_leggings = new AmatArmorBase(AMAT, EquipmentSlotType.LEGS, defaultItemProp);
    public static final AmatArmorBase amat_boots = new AmatArmorBase(AMAT, EquipmentSlotType.FEET, defaultItemProp);
    public static final BossArmorBase boss_helmet = new BossArmorBase(BOSSAPPARE, EquipmentSlotType.HEAD, defaultItemProp);
    public static final BossArmorBase boss_chestplate = new BossArmorBase(BOSSAPPARE, EquipmentSlotType.CHEST, defaultItemProp);
    public static final BossArmorBase boss_leggings = new BossArmorBase(BOSSAPPARE, EquipmentSlotType.LEGS, defaultItemProp);
    public static final BossArmorBase boss_boots = new BossArmorBase(BOSSAPPARE, EquipmentSlotType.FEET, defaultItemProp);
    public static final LifeArmorBase life_helmet = new LifeArmorBase(LIFEAPPARE, EquipmentSlotType.HEAD, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_chestplate = new LifeArmorBase(LIFEAPPARE, EquipmentSlotType.CHEST, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_leggings = new LifeArmorBase(LIFEAPPARE, EquipmentSlotType.LEGS, defaultItemProp, 3.0D);
    public static final LifeArmorBase life_boots = new LifeArmorBase(LIFEAPPARE, EquipmentSlotType.FEET, defaultItemProp, 3.0D);
    public static final SpeedShoeBase speed_boots = new SpeedShoeBase(SPEEDAPPARE, EquipmentSlotType.FEET, defaultItemProp, 0.02D);
    public static final SpeedShoeBase speed2_boots = new SpeedShoeBase(SPEEDAPPARE2, EquipmentSlotType.FEET, defaultItemProp, 0.03D);

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
