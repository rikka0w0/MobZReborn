package net.mobz.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.mobz.init.MobZItems;

public class AMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 17, 19, 21, 15 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 2, 5, 6, 2 };

	@Override
	public int getDurabilityForType(ArmorItem.Type armorItemType) {
		return BASE_DURABILITY[armorItemType.getSlot().getIndex()] * 25;
	}

	@Override
	public int getDefenseForType(ArmorItem.Type armorItemType) {
		return PROTECTION_AMOUNTS[armorItemType.getSlot().getIndex()];
	}

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(MobZItems.AMAT_INGOT.get());
    }

    @Override
    public String getName() {
        return "amat";
    }

    @Override
    public float getToughness() {
        return 1;
    }

	@Override
	public float getKnockbackResistance() {
		return 0;
	}
}