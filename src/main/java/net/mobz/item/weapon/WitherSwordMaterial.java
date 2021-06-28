package net.mobz.item.weapon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class WitherSwordMaterial implements IItemTier {
    @Override
    public int getUses() {
        return 516;
    }

    @Override
    public float getSpeed() {
        return 1.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}