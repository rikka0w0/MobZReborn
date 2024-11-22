package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Bowman extends Pillager {
	public Bowman(EntityType<? extends Pillager> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.bowman.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.33D)
				.add(Attributes.FOLLOW_RANGE, 34.0D);
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
		float attack = (float) (MobZ.configs.bowman.attack * MobZ.configs.damage_multiplier);
		this.performCrossbowAttack(this, attack);
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
	protected void playStepSound(BlockPos pos, BlockState state) {
		if (!state.liquid()) {
			this.playSound(MobZSounds.LEATHERWALKEVENT.get(), 0.15F, 1F);
		}
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
		return;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0D, 8.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IceGolem.class, true));
		this.targetSelector.addGoal(4, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(IceGolem.class));
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.bowman.can_join_raid.check(this);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		BlockPos posentity = this.blockPosition();
		return MobZ.configs.bowman.spawn && this.level().isDay() && view.canSeeSky(posentity)
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}