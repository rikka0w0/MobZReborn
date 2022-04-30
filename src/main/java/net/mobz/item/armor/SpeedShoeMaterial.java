package net.mobz.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.mobz.init.MobZItems;

public class SpeedShoeMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 11, 12, 13, 10 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 1, 2, 3, 1 };

    @Override
    public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
        return BASE_DURABILITY[equipmentSlot.getIndex()] * 25;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
        return PROTECTION_AMOUNTS[equipmentSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(MobZItems.BEARLEATHER.get());
    }

    @Override
    public String getName() {
        return "speed";
    }

    @Override
    public float getToughness() {
        return 0;
    }

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

}