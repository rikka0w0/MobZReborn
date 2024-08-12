/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.mobz.item.weapon;

import java.util.function.Supplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

/**
 * Helper class to define a custom tier
 */
public record SimpleTier(
		TagKey<Block> incorrectBlocksForDrops,
		int uses,
		float speed,
		float attackDamageBonus,
		int enchantmentValue,
		Supplier<Ingredient> repairIngredient
	) implements Tier {

	public static SimpleTier of(int uses, float speed, float attackDamageBonus, int enchantmentValue) {
		return new SimpleTier(null, uses, speed, attackDamageBonus, enchantmentValue, ()->Ingredient.EMPTY);
	}

	public static SimpleTier of(int uses, float speed, float attackDamageBonus, int enchantmentValue, Supplier<? extends ItemLike> repairItem) {
		return new SimpleTier(null, uses, speed, attackDamageBonus, enchantmentValue, ()->Ingredient.of(repairItem.get()));
	}

	@Override
	public int getUses() {
		return this.uses;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.attackDamageBonus;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@Override
	public String toString() {
		return "SimpleTier[" + "incorrectBlocksForDrops=" + incorrectBlocksForDrops + ", " + "uses=" + uses + ", "
				+ "speed=" + speed + ", " + "attackDamageBonus=" + attackDamageBonus + ", " + "enchantmentValue="
				+ enchantmentValue + ", " + "repairIngredient=" + repairIngredient + ']';
	}

	@Override
	public int getLevel() {
		return 1;
	}
}
