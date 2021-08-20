package net.mobz.item.weapon;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class DebugoMat implements Tier {
    @Override
    public int getUses() {
        return 2020;
    }

    @Override
    public float getSpeed() {
        return 5.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 1995;
    }

    @Override
    public int getLevel() {
        return 10;
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