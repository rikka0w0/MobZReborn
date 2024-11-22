package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import net.mobz.MobZ;
import net.mobz.MobZRarity;
import net.mobz.init.MobZItems;
import net.mobz.item.SimpleItem;
import net.mobz.tags.MobZItemTags;

public class SpeedShoeBase extends SimpleItem {
	public static final EnumMap<ArmorType, Integer> DEFENSE_MAP1 =
		Util.make(new EnumMap<>(ArmorType.class), (map) -> {
			map.put(ArmorType.BOOTS, 1);
			map.put(ArmorType.LEGGINGS, 2);
			map.put(ArmorType.CHESTPLATE, 3);
			map.put(ArmorType.HELMET, 1);
		});

	public static final ResourceLocation EQUIPMENT_MODEL_SPEED = MobZ.resLoc("speed");

	public static final ArmorMaterial MATERIAL1 = new ArmorMaterial(
			21,
			DEFENSE_MAP1,
       		10,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		0,	// getToughness
       		0,	// getKnockbackResistance
       		MobZItemTags.REPAIRS_SPEED_ARMOR,
       		EQUIPMENT_MODEL_SPEED
//       		List.of(new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "speed")))
		);

	public static final EnumMap<ArmorType, Integer> DEFENSE_MAP2 =
		Util.make(new EnumMap<>(ArmorType.class), (map) -> {
			map.put(ArmorType.BOOTS, 2);
			map.put(ArmorType.LEGGINGS, 3);
			map.put(ArmorType.CHESTPLATE, 4);
			map.put(ArmorType.HELMET, 2);
		});

	public static final ResourceLocation EQUIPMENT_MODEL_SPEED2 = MobZ.resLoc("speed2");

	public static final ArmorMaterial MATERIAL2 = new ArmorMaterial(
			23,		// Durability
			DEFENSE_MAP2,
       		12,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		0,	// getToughness
       		0,	// getKnockbackResistance
       		MobZItemTags.REPAIRS_SPEED2_ARMOR,
       		EQUIPMENT_MODEL_SPEED2
//       		List.of(new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "speed2")))
		);


    public SpeedShoeBase(ArmorMaterial material, ArmorType armorType, Item.Properties properties, double speedBoost) {
        super(ArmorHelper.humanoidProperties(material, armorType, properties,
        		attributes -> SpeedShoeBase.attributeModifiers(attributes, armorType, speedBoost)
        		), MobZRarity.UNCOMMON);
    }

    public static ItemAttributeModifiers attributeModifiers(ItemAttributeModifiers modifiers, ArmorType armorType, double speedBoost) {
		return modifiers.withModifierAdded(
			Attributes.MOVEMENT_SPEED,
			new AttributeModifier(ResourceLocation.tryBuild(MobZ.MODID, "speed"), speedBoost, AttributeModifier.Operation.ADD_VALUE),
			EquipmentSlotGroup.bySlot(armorType.getSlot())
		);
	}
}
