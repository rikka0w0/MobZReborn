package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.entity.attack.GolemAttack;
import net.mobz.init.MobZSounds;

public class IceGolem extends IronGolem {
	public IceGolem(EntityType<? extends IronGolem> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.ice_golem.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.26D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.ice_golem.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.5D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new GolemAttack(this, 1.0D, true));
		this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
		// this.goalSelector.addGoal(5, new OfferFlowerGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.initCustomGoals();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		BlockPos posentity = this.blockPosition();
		return MobZ.configs.ice_golem.spawn
				&& this.level().getCurrentDifficultyAt(posentity).getDifficulty() != Difficulty.PEACEFUL
				&& this.level().isBrightOutside()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	protected void initCustomGoals() {
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Zombie.class));
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Skeleton.class));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Spider.class));
		this.targetSelector.addGoal(4, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Creeper.class));
		this.targetSelector.addGoal(5, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Slime.class));
		this.targetSelector.addGoal(6, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(FrostBlaze.class));
		this.targetSelector.addGoal(7, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(FrostCreeper.class));
		this.targetSelector.addGoal(8, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Boar.class));
		this.targetSelector.addGoal(9, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Blackbear.class));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.GOLEMHITEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.GOLEMDEATHEVENT.get();
	}

	@Override
	protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
		this.playSound(MobZSounds.GOLEMWALKEVENT.get(), 1.0F, 1.0F);
	}

	@Override
	public boolean removeWhenFarAway(double double_1) {
		return true;
	}
}