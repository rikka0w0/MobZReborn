package net.mobz.item.weapon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class AxeMaterial implements IItemTier {

    @Override
    public int getUses() {
        return 761;
    }

    @Override
    public float getSpeed() {
        return 1.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 11;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

}