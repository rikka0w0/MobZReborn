package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class CookieCreeper extends Creeper {
	public CookieCreeper(EntityType<? extends Creeper> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.cookie_creeper.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.SAYCRIPEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.DEATHCRIPEVENT.get();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.cookie_creeper.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}
