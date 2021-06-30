package net.mobz.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.mobz.init.MobZItems;

public class SpeedShoeMaterial implements IArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 11, 12, 13, 10 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 1, 2, 3, 1 };

    @Override
    public int getDurabilityForSlot(EquipmentSlotType equipmentSlot) {
        return BASE_DURABILITY[equipmentSlot.getIndex()] * 25;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType equipmentSlot) {
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
        return Ingredient.of(MobZItems.BEARLEATHER);
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