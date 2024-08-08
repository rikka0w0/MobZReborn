package net.mobz.item.armor;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.UUID;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ArmorUtils {
	@SuppressWarnings("unchecked")
	public static EnumMap<ArmorItem.Type, UUID> getModifierUUIDMap() {
		try {
			Field field = ArmorItem.class.getDeclaredField("ARMOR_MODIFIER_UUID_PER_TYPE");
			field.setAccessible(true);
			return (EnumMap<ArmorItem.Type, UUID>) field.get(null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UUID getModifierUUID(ArmorItem.Type type) {
		return getModifierUUIDMap().get(type);
	}

	public static UUID getModifierUUID(ArmorItem.Type type, ItemAttributeModifiers existings) {
		EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
		return existings.modifiers().stream()
			.filter(entry -> entry.slot().equals(equipmentSlotGroup))
			.findFirst()
			.map(entry -> entry.modifier().id())
			.orElse(null);
	}
}
