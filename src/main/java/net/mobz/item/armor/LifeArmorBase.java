package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import net.mobz.init.MobZItems;

public class LifeArmorBase extends ArmorItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 16, 18, 20, 14 } * 25
	// Vanilla base: {13, 15, 16, 11};
	public static final EnumMap<Type, Integer> DURABILITY_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 30);
		map.put(ArmorItem.Type.LEGGINGS, 30);
		map.put(ArmorItem.Type.CHESTPLATE, 30);
		map.put(ArmorItem.Type.HELMET, 30);
	});

	public static final EnumMap<Type, Integer> DEFENSE_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 5);
		map.put(ArmorItem.Type.HELMET, 2);
	});

	public static final ArmorMaterial MATERIAL = new SimpleArmorMaterial("life", DURABILITY_MAP,
			DEFENSE_MAP,
       		10,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_IRON,
       		()->Ingredient.of(MobZItems.HARDENEDMETAL_INGOT.get()),
       		0,	// getToughness
       		0	// getKnockbackResistance
		);

    private final double lifeBoost = 3.0D;

    public LifeArmorBase(ArmorItem.Type armorItemType, Item.Properties properties) {
        super(MATERIAL, armorItemType, properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.life_armor.tooltip"));
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Multimap<Attribute, AttributeModifier> modifiers = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot));

		if (equipmentSlot == this.getEquipmentSlot()) {
			modifiers.put(
					Attributes.MAX_HEALTH,
					new AttributeModifier(ArmorUtils.getModifierUUID(modifiers), "Life", this.lifeBoost, AttributeModifier.Operation.ADDITION));
		}

		return modifiers;
	}
}
