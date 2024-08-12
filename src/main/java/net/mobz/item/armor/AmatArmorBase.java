package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import net.mobz.init.MobZArmors;
import net.mobz.init.MobZItems;

public class AmatArmorBase extends ArmorItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 17, 19, 21, 15 } * 25
	// Vanilla base: {13, 15, 16, 11};
	public static final EnumMap<Type, Integer> DURABILITY_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 34);
		map.put(ArmorItem.Type.LEGGINGS, 34);
		map.put(ArmorItem.Type.CHESTPLATE, 34);
		map.put(ArmorItem.Type.HELMET, 34);
	});

	public static final EnumMap<Type, Integer> DEFENSE_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 5);
		map.put(ArmorItem.Type.CHESTPLATE, 6);
		map.put(ArmorItem.Type.HELMET, 2);
	});

	public static final ArmorMaterial MATERIAL = new SimpleArmorMaterial("amat", DURABILITY_MAP,
			DEFENSE_MAP,
       		15,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_DIAMOND,
       		()->Ingredient.of(MobZItems.AMAT_INGOT.get()),
       		1,	// getToughness
       		0	// getKnockbackResistance
			);

    double attackSpeedBonus = 0.1D;

    public AmatArmorBase(ArmorItem.Type armorItemType, Item.Properties properties) {
        super(MATERIAL, armorItemType, properties);
    }

    @Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.amat_armor.tooltip"));
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Multimap<Attribute, AttributeModifier> modifiers = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot));

		if (equipmentSlot == this.getEquipmentSlot()) {
			modifiers.put(
					Attributes.ATTACK_DAMAGE,
					new AttributeModifier(ArmorUtils.getModifierUUID(modifiers),
					"amat_attack_bonus", this.attackSpeedBonus, AttributeModifier.Operation.ADDITION));
		}

		return modifiers;
	}

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        MobEffectInstance fireResistance = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9, 0, false, false);
        if (bob.getItemBySlot(EquipmentSlot.FEET).is(MobZArmors.AMAT_BOOTS.get())
                && bob.getItemBySlot(EquipmentSlot.LEGS).is(MobZArmors.AMAT_LEGGINGS.get())
                && bob.getItemBySlot(EquipmentSlot.CHEST).is(MobZArmors.AMAT_CHESTPLATE.get())
                && bob.getItemBySlot(EquipmentSlot.HEAD).is(MobZArmors.AMAT_HELMET.get())
                && !world.isClientSide) {
            bob.addEffect(fireResistance);
        }
    }
}
