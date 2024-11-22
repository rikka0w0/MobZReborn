package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import net.mobz.MobZ;
import net.mobz.MobZRarity;
import net.mobz.init.MobZItems;

public class SpeedShoeBase extends ArmorItem {
	public static final EnumMap<Type, Integer> DEFENSE_MAP1 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 3);
		map.put(ArmorItem.Type.HELMET, 1);
	});

	public static final Holder<ArmorMaterial> MATERIAL1 = Registry.registerForHolder(
		BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.tryBuild(MobZ.MODID, "speed"), new ArmorMaterial(
			DEFENSE_MAP1,
       		10,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		()->Ingredient.of(MobZItems.BEAR_LEATHER.get()),
       		List.of(new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "speed"))),
       		0,	// getToughness
       		0	// getKnockbackResistance
		)
	);

	public static final EnumMap<Type, Integer> DEFENSE_MAP2 = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 3);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 2);
	});

	public static final Holder<ArmorMaterial> MATERIAL2 = Registry.registerForHolder(
		BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.tryBuild(MobZ.MODID, "speed2"), new ArmorMaterial(
			DEFENSE_MAP2,
       		12,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_LEATHER,
       		()->Ingredient.of(Items.EMERALD),
       		List.of(new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "speed2"))),
       		0,	// getToughness
       		0	// getKnockbackResistance
		)
	);

    private final double speedBoost;

    public SpeedShoeBase(Holder<ArmorMaterial> material, ArmorItem.Type armorItemType, Item.Properties properties, double speedBoost) {
        super(material, armorItemType, properties);
        this.speedBoost = speedBoost;
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.UNCOMMON.addToTooltip(tooltip);
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		ItemAttributeModifiers modifiers = super.getDefaultAttributeModifiers();

		return modifiers.withModifierAdded(
			Attributes.MOVEMENT_SPEED,
			new AttributeModifier(ResourceLocation.tryBuild(MobZ.MODID, "speed"), this.speedBoost, AttributeModifier.Operation.ADD_VALUE),
			EquipmentSlotGroup.bySlot(this.getEquipmentSlot())
		);
	}
}
