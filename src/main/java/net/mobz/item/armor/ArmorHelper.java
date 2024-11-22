package net.mobz.item.armor;

import java.util.function.UnaryOperator;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class ArmorHelper {
	public static Item.Properties humanoidProperties(ArmorMaterial armorMaterial, ArmorType armorType,
			Item.Properties itemProps, UnaryOperator<ItemAttributeModifiers> attributeModifier) {
		// ArmorMaterial::createAttributes was made public by the access transformer
		ItemAttributeModifiers defaultAttributes = armorMaterial.createAttributes(armorType);

		// This vanilla function populates default components for an armor item, including attributes
		itemProps = armorMaterial.humanoidProperties(itemProps, armorType);

		return itemProps.attributes(attributeModifier.apply(defaultAttributes));
	}
}
