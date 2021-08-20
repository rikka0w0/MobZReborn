package net.mobz.item.weapon;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class SwordMaterial implements Tier {
    @Override
    public int getUses() {
        return 826;
    }

    @Override
    public float getSpeed() {
        return 1.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 7;
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
        return Ingredient.of(Items.DRAGON_EGG);
    }

}