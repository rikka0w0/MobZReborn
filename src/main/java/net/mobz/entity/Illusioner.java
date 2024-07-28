package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Illusioner extends net.minecraft.world.entity.monster.Illusioner {
	public Illusioner(EntityType<? extends net.minecraft.world.entity.monster.Illusioner> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.illusioner.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.4D)
				// TODO: this will not work as expected!
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.illusioner.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.ILLUIDLEEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.ILLUDEATHEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.ILLUHURTEVENT.get();
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.illusioner.can_join_raid.check(this);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.illusioner.spawn && this.level().isDay()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}
