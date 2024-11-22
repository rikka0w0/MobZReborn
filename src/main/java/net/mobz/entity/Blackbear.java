package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Blackbear extends Panda {
	public Blackbear(EntityType<? extends Panda> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.black_bear.life)
				.add(Attributes.MOVEMENT_SPEED, 0.17D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.black_bear.attack);
	}

	@Override
	public void setAttributes() {
		// Need this to set life properly!
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(MobZ.configs.black_bear.life);
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isSneezing() {
		return false;
	}

	@Override
	public boolean isSitting() {
		return false;
	}

	@Override
	public boolean isOnBack() {
		return false;
	}

	@Override
	public boolean isEating() {
		return false;
	}

	@Override
	public boolean isRolling() {
		return false;
	}

	@Override
	public boolean isLazy() {
		return false;
	}

	@Override
	public boolean isWorried() {
		return false;
	}

	@Override
	public boolean isPlayful() {
		return false;
	}

	@Override
	public boolean isWeak() {
		return false;
	}

	@Override
	public boolean isAggressive() {
		return false;
	}

	@Override
	public boolean doHurtTarget(ServerLevel serverLevel, Entity victim) {
		this.playSound(MobZSounds.PBITEEVENT.get(), 1.0F, 1.0F);
		return super.doHurtTarget(serverLevel, victim);
	}

	@Override
	public boolean isScared() {
		return false;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.POLAR_BEAR_AMBIENT;

	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.POLAR_BEAR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.POLAR_BEAR_HURT;
	}

}