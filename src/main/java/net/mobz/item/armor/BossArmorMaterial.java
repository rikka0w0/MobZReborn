package net.mobz.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.mobz.init.MobZItems;

public class BossArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 18, 20, 22, 16 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 4, 7, 9, 4 };

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
        return 25;
    }

    @Override
    public SoundEvent getEquipSound() {
    	return SoundEvents.ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(MobZItems.BOSS_INGOT);
    }

    @Override
    public String getName() {
        return "boss";
    }

    @Override
    public float getToughness() {
        return 2;
    }

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

}