package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class LostSkeleton extends Skeleton {
	public LostSkeleton(EntityType<? extends Skeleton> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.lost_skeleton.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.255D)
				.add(Attributes.FOLLOW_RANGE, 30.0D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.lost_skeleton.attack * MobZ.configs.damage_multiplier);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.STONE_TOMAHAWK.get()));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.SKELASAYEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.SKELAHURTEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.SKELADEATHEVENT.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		if (!state.liquid()) {
			this.playSound(MobZSounds.SKELASTEPEVENT.get(), 0.15F, 1F);
		}
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.lost_skeleton.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}