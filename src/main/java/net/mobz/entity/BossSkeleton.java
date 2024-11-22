package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class BossSkeleton extends Skeleton {
	public BossSkeleton(EntityType<? extends Skeleton> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 50;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.boss_skeleton.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.FOLLOW_RANGE, 30.0D)
				.add(Attributes.ARMOR, 2D);
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
		return MobZ.configs.boss_skeleton.spawn
				&& this.level().isNight()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}