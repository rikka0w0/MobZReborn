package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;

public class Knight extends Vindicator {

	public Knight(EntityType<? extends Vindicator> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.IslandKnightLife * MobZ.configs.LifeMultiplicatorMob)
				.add(Attributes.MOVEMENT_SPEED, 0.32D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.IslandKnightAttack * MobZ.configs.DamageMultiplicatorMob)
				.add(Attributes.FOLLOW_RANGE, 18.0D);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		if (!state.liquid()) {
			this.playSound(MobZSounds.LEATHERWALKEVENT.get(), 0.15F, 1F);
		}
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(MobZItems.SHIELD.get()));
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
		return;
	}

	@Override
	public boolean requiresCustomPersistence() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.NOTHINGEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.PLAYER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PLAYER_DEATH;
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && this.level().canSeeSky(this.blockPosition());
	}
}
