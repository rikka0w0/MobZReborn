package net.mobz.item.armor;

import java.util.EnumMap;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;

import net.mobz.MobZ;
import net.mobz.MobZRarity;
import net.mobz.item.SimpleItem;
import net.mobz.tags.MobZItemTags;

public class LifeArmorBase extends SimpleItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 16, 18, 20, 14 } * 25
	// Vanilla base: {13, 15, 16, 11};

	public static final EnumMap<ArmorType, Integer> DEFENSE_MAP =
		Util.make(new EnumMap<>(ArmorType.class), (map) -> {
			map.put(ArmorType.BOOTS, 2);
			map.put(ArmorType.LEGGINGS, 4);
			map.put(ArmorType.CHESTPLATE, 5);
			map.put(ArmorType.HELMET, 2);
		});

	public static final ResourceKey<EquipmentAsset> EQUIPMENT_MODEL_LIFE =
			ResourceKey.create(EquipmentAssets.ROOT_ID, MobZ.resLoc("life"));

	public static final ArmorMaterial MATERIAL = new ArmorMaterial(
			30,		// Durability
			DEFENSE_MAP,
			10,		// getEnchantmentValue
			SoundEvents.ARMOR_EQUIP_IRON,
			0,	// getToughness
			0,	// getKnockbackResistance
			MobZItemTags.REPAIRS_LIFE_ARMOR,
			EQUIPMENT_MODEL_LIFE
		);

	public LifeArmorBase(ArmorType armorType, Item.Properties properties) {
		super(ArmorHelper.humanoidProperties(MATERIAL, armorType, properties,
				attributes -> LifeArmorBase.attributeModifiers(attributes, armorType)), MobZRarity.RARE);
	}

	public static ItemAttributeModifiers attributeModifiers(ItemAttributeModifiers modifiers, ArmorType armorType) {
		return modifiers.withModifierAdded(
			Attributes.MAX_HEALTH,
			new AttributeModifier(MobZ.resLoc("life"), 3.0D, AttributeModifier.Operation.ADD_VALUE),
			EquipmentSlotGroup.bySlot(armorType.getSlot())
		);
	}
}
