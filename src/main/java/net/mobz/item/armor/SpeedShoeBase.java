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
import net.minecraft.world.item.Items;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import net.mobz.init.MobZItems;

public class SpeedShoeBase extends ArmorItem {
	public static final EnumMap<Type, Integer> DURABILITY_MAP1 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 21);
		map.put(ArmorItem.Type.LEGGINGS, 21);
		map.put(ArmorItem.Type.CHESTPLATE, 21);
		map.put(ArmorItem.Type.HELMET, 21);
	});

	public static final EnumMap<Type, Integer> DEFENSE_MAP1 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 3);
		map.put(ArmorItem.Type.HELMET, 1);
	});

	public static final ArmorMaterial MATERIAL1 = new SimpleArmorMaterial("speed1", DURABILITY_MAP1,
			DEFENSE_MAP1,
       		10,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		()->Ingredient.of(MobZItems.BEAR_LEATHER.get()),
       		0,	// getToughness
       		0	// getKnockbackResistance
		);

	public static final EnumMap<Type, Integer> DURABILITY_MAP2 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 23);
		map.put(ArmorItem.Type.LEGGINGS, 23);
		map.put(ArmorItem.Type.CHESTPLATE, 23);
		map.put(ArmorItem.Type.HELMET, 23);
	});

	public static final EnumMap<Type, Integer> DEFENSE_MAP2 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 3);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 2);
	});

	public static final ArmorMaterial MATERIAL2 = new SimpleArmorMaterial("speed2", DURABILITY_MAP2,
			DEFENSE_MAP2,
       		12,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		()->Ingredient.of(Items.EMERALD),
       		0,	// getToughness
       		0	// getKnockbackResistance
		);

    private final double speedBoost;

    public SpeedShoeBase(ArmorMaterial material, ArmorItem.Type armorItemType, Item.Properties properties, double speedBoost) {
        super(material, armorItemType, properties);
        this.speedBoost = speedBoost;
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.speed_boots.tooltip"));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Multimap<Attribute, AttributeModifier> modifiers = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot));

		if (equipmentSlot == this.getEquipmentSlot()) {
			modifiers.put(
					Attributes.MOVEMENT_SPEED,
					new AttributeModifier(ArmorUtils.getModifierUUID(modifiers), "Speed", this.speedBoost, AttributeModifier.Operation.ADDITION));
		}

		return modifiers;
	}
}
