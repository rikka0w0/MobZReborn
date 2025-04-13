package net.mobz.item.armor;

import java.util.EnumMap;

import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
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

public class AmatArmorBase extends SimpleItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 17, 19, 21, 15 } * 25
	// Vanilla base: {13, 15, 16, 11};

	public static final EnumMap<ArmorType, Integer> DEFENSE_MAP =
		Util.make(new EnumMap<>(ArmorType.class), (map) -> {
			map.put(ArmorType.BOOTS, 2);
			map.put(ArmorType.LEGGINGS, 5);
			map.put(ArmorType.CHESTPLATE, 6);
			map.put(ArmorType.HELMET, 2);
		});

	public static final ResourceKey<EquipmentAsset> EQUIPMENT_MODEL_AMAT =
			ResourceKey.create(EquipmentAssets.ROOT_ID, MobZ.resLoc("amat"));

	public static final ArmorMaterial MATERIAL = new ArmorMaterial(
			34,		// Durability
			DEFENSE_MAP,
       		15,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_DIAMOND,
       		1,	// getToughness
       		0,  // getKnockbackResistance
       		MobZItemTags.REPAIRS_AMAT_ARMOR,
       		EQUIPMENT_MODEL_AMAT
		);

	public AmatArmorBase(ArmorType armorType, Item.Properties properties) {
		super(ArmorHelper.humanoidProperties(MATERIAL, armorType, properties,
				attributes -> AmatArmorBase.attributeModifiers(attributes, armorType)), MobZRarity.RARE);
	}

	public static ItemAttributeModifiers attributeModifiers(ItemAttributeModifiers modifiers, ArmorType armorType) {
		return modifiers.withModifierAdded(
			Attributes.ATTACK_DAMAGE,
			new AttributeModifier(MobZ.resLoc("amat_attack_bonus"), 0.1D, AttributeModifier.Operation.ADD_VALUE),
			EquipmentSlotGroup.bySlot(armorType.getSlot())
		);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		if (!(entity instanceof LivingEntity livingEntity) || world.isClientSide())
			return;

		if (livingEntity.getItemBySlot(EquipmentSlot.FEET).is(MobZArmors.AMAT_BOOTS.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(MobZArmors.AMAT_LEGGINGS.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(MobZArmors.AMAT_CHESTPLATE.get())
				&& livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(MobZArmors.AMAT_HELMET.get())) {
			livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9, 0, false, false));
		}
    }
}
