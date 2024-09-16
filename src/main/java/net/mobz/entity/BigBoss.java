package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class BigBoss extends Zombie {

	public BigBoss(EntityType<? extends Zombie> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 60;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.bigboss.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.bigboss.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.KNOCKBACK_RESISTANCE, 10D)
				.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	public boolean isUnderWaterConverting() {
		return false;
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	public boolean isBaby() {
		return false;
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
		return;
	}

	@Override
	public boolean requiresCustomPersistence() {
		return true;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.bigboss.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.AMBIENTTANKEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.HURTTANKEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.DEATHTANKEVENT.get();
	}

	@Override
	protected SoundEvent getStepSound() {
		return MobZSounds.STEPTANKEVENT.get();
	}

}
