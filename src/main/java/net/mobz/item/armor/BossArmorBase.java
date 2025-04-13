package net.mobz.item.armor;

import java.util.EnumMap;

import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;

import net.mobz.MobZ;
import net.mobz.MobZRarity;
import net.mobz.init.MobZArmors;
import net.mobz.item.SimpleItem;
import net.mobz.tags.MobZItemTags;

public class BossArmorBase extends SimpleItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 18, 20, 22, 16 } * 25
	// Vanilla base: {13, 15, 16, 11};

	public static final EnumMap<ArmorType, Integer> DEFENSE_MAP =
		Util.make(new EnumMap<>(ArmorType.class), (map) -> {
			map.put(ArmorType.BOOTS, 4);
			map.put(ArmorType.LEGGINGS, 7);
			map.put(ArmorType.CHESTPLATE, 9);
			map.put(ArmorType.HELMET, 4);
		});

	public static final ResourceKey<EquipmentAsset> EQUIPMENT_MODEL_BOSS =
			ResourceKey.create(EquipmentAssets.ROOT_ID, MobZ.resLoc("boss"));

	public static final ArmorMaterial MATERIAL = new ArmorMaterial(
			35,		// Durability
			DEFENSE_MAP,
			25,		// getEnchantmentValue
			SoundEvents.ARMOR_EQUIP_GOLD,
			2,	// getToughness
			0,	// getKnockbackResistance
			MobZItemTags.REPAIRS_BOSS_ARMOR,
			EQUIPMENT_MODEL_BOSS
	);

	public BossArmorBase(ArmorType armorType, Item.Properties properties) {
		super(properties.humanoidArmor(MATERIAL, armorType), MobZRarity.EPIC);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		if (!(entity instanceof LivingEntity livingEntity) || world.isClientSide())
			return;

		if (livingEntity.getItemBySlot(EquipmentSlot.FEET).is(MobZArmors.BOSS_BOOTS.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(MobZArmors.BOSS_LEGGINGS.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(MobZArmors.BOSS_CHESTPLATE.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(MobZArmors.BOSS_HELMET.get())) {
			livingEntity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 9, 0, false, false));
			if (livingEntity.isSprinting()) {
				livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, 9, 0, false, false));
			}
		}
	}
}
