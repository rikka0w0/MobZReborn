package net.mobz.entity;

import java.util.EnumSet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Withender extends WitherBoss {
	private static final TargetingConditions.Selector CAN_ATTACK_PREDICATE = (livingEntity, serverLevel) ->
		!livingEntity.getType().is(EntityTypeTags.WITHER_FRIENDS) && livingEntity.attackable();

	public Withender(EntityType<? extends WitherBoss> entityType_1, Level world_1) {
		super(entityType_1, world_1);
		this.xpReward = 50;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.withender.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.ARMOR, 4.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new Withender.DescendAtHalfHealthGoal());
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(EnderMan.class));
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Ender.class));
		this.targetSelector.addGoal(3,
				new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, CAN_ATTACK_PREDICATE));
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
		return;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.WITHIDLEEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.WITHHURTEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.WITHDEATHEVENT.get();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader viewableWorld_1) {
		return viewableWorld_1.isUnobstructed(this)
			&& MobZ.configs.withender.enabled;
	}

	class DescendAtHalfHealthGoal extends Goal {
		public DescendAtHalfHealthGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			return true;
		}
	}
}
