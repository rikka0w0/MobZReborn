package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZWeapons;

public class SpiritOfDeath extends Vex {
	public SpiritOfDeath(EntityType<? extends Vex> entityType, Level world) {
		super(entityType, world);

	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.spirit_of_death.life * MobZ.configs.life_multiplier)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.spirit_of_death.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 18.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
	}
}