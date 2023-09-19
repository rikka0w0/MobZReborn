package net.mobz.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.mobz.init.MobZItems;

public class LifeArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 16, 18, 20, 14 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 2, 4, 5, 2 };

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
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(MobZItems.HARDENEDMETAL_INGOT.get());
    }

    @Override
    public String getName() {
        return "life";
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