package net.mobz.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.mobz.init.MobZItems;

public class SpeedShoeMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 11, 12, 13, 10 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { 1, 2, 3, 1 };

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