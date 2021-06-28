package net.mobz.item.weapon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import net.mobz.init.MobZItems;

public class SwordBossMaterial implements IItemTier {
    @Override
    public int getUses() {
        return 561;
    }

    @Override
    public float getSpeed() {
        return 1.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 8;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(MobZItems.BOSS_INGOT);
    }

}