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

	public static final Holder<ArmorMaterial> MATERIAL = Registry.registerForHolder(
		BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.tryBuild(MobZ.MODID, "life"), new ArmorMaterial(
			DEFENSE_MAP,
       		10,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_IRON,
       		()->Ingredient.of(MobZItems.HARDENEDMETAL_INGOT.get()),
       		List.of(
   				new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "life")),
    			new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "life"))
   			),
       		0,	// getToughness
       		0	// getKnockbackResistance
		)
	);

    private final double lifeBoost = 3.0D;

    public LifeArmorBase(ArmorItem.Type armorItemType, Item.Properties properties) {
        super(MATERIAL, armorItemType, properties.durability(DURABILITY_MAP.get(armorItemType)));
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.RARE.addToTooltip(tooltip);
    }

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		ItemAttributeModifiers modifiers = super.getDefaultAttributeModifiers();

		return modifiers.withModifierAdded(
			Attributes.MAX_HEALTH,
			new AttributeModifier(ResourceLocation.tryBuild(MobZ.MODID, "life"), this.lifeBoost, AttributeModifier.Operation.ADD_VALUE),
			EquipmentSlotGroup.bySlot(this.getEquipmentSlot())
		);
	}
}
