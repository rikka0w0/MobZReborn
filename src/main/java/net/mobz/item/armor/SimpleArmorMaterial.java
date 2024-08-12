package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;

public record SimpleArmorMaterial(
		String name,
		EnumMap<Type, Integer> durabilityMap,
		EnumMap<Type, Integer> defenseMap,
		int enchantmentValue,
		SoundEvent equipSound,
		Supplier<Ingredient> repairIngredient,
		float toughness,
		float knockbackResistance
		) implements net.minecraft.world.item.ArmorMaterial {

    public static final EnumMap<ArmorItem.Type, Integer> VANILLA_BASE_DURABILITY = Util.make(new EnumMap<>(ArmorItem.Type.class), p_266653_ -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

	@Override
	public int getDurabilityForType(ArmorItem.Type pType) {
		return VANILLA_BASE_DURABILITY.get(pType) * durabilityMap.get(pType);
	}

	@Override
	public int getDefenseForType(ArmorItem.Type pType) {
		return defenseMap.get(pType);
	}

	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound() {
		return equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return repairIngredient.get();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getToughness() {
		return toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}

}
