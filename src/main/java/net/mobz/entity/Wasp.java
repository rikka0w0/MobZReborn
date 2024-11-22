package net.mobz.entity;

import java.util.EnumSet;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.AirRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class Wasp extends PathfinderMob implements FlyingAnimal {
	public static final int TICKS_PER_FLAP = Mth.ceil(1.4959966F);
	protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Wasp.class,
			EntityDataSerializers.BYTE);

	private float rollAmount;
	private float rollAmountO;
	private int stayOutOfHiveCountdown;
	int remainingCooldownBeforeLocatingNewHive;
	private int underWaterTicks;

	@Nullable
	protected BlockPos hivePos;

	public Wasp(EntityType<? extends Wasp> entityType, Level level) {
		super(entityType, level);

		this.moveControl = new FlyingMoveControl(this, 20, true);
		this.lookControl = new LookControl(this);
		this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(PathType.WATER, -1.0F);
		this.setPathfindingMalus(PathType.WATER_BORDER, 16.0F);
		this.setPathfindingMalus(PathType.COCOA, -1.0F);
		this.setPathfindingMalus(PathType.FENCE, -1.0F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.4F, true));
		// this.goalSelector.addGoal(1, new Bee.BeeEnterHiveGoal());
		// this.goalSelector.addGoal(5, new Bee.BeeLocateHiveGoal());
		// this.goToHiveGoal = new Bee.BeeGoToHiveGoal();
		// this.goalSelector.addGoal(5, this.goToHiveGoal);

		this.goalSelector.addGoal(8, new BeeWanderGoal());
		this.goalSelector.addGoal(9, new FloatGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3,
				new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
	}

	@Override
	public float getWalkTargetValue(BlockPos p_27788_, LevelReader p_27789_) {
		return p_27789_.getBlockState(p_27788_).isAir() ? 10.0F : 0.0F;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_FLAGS_ID, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_27823_) {
		super.addAdditionalSaveData(p_27823_);
		if (this.hasHive()) {
			p_27823_.put("HivePos", NbtUtils.writeBlockPos(this.getHivePos()));
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_27793_) {
		this.hivePos = null;
		NbtUtils.readBlockPos(p_27793_, "HivePos").ifPresent(value -> this.hivePos = value);
		super.readAdditionalSaveData(p_27793_);
	}

	@Override
	public boolean doHurtTarget(ServerLevel serverLevel, Entity victim) {
		DamageSource damagesource = this.damageSources().sting(this);
		int damage = (int) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		boolean hurted = victim.hurtServer(serverLevel, this.damageSources().sting(this), damage);
		if (hurted) {
			if (this.level() instanceof ServerLevel serverlevel) {
				EnchantmentHelper.doPostAttackEffects(serverlevel, victim, damagesource);
			}

			if (victim instanceof LivingEntity livingVictim) {
				livingVictim.setStingerCount(livingVictim.getStingerCount() + 1);
				int i = 0;
				if (this.level().getDifficulty() == Difficulty.NORMAL) {
					i = 10;
				} else if (this.level().getDifficulty() == Difficulty.HARD) {
					i = 18;
				}

				if (i > 0) {
					livingVictim.addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0), this);
				}
			}

			this.playSound(SoundEvents.BEE_STING, 1.0F, 1.0F);
		}

		return hurted;
	}

	@Override
	public void tick() {
		super.tick();
		this.updateRollAmount();
	}

	void pathfindRandomlyTowards(BlockPos p_27881_) {
		Vec3 vec3 = Vec3.atBottomCenterOf(p_27881_);
		int i = 0;
		BlockPos blockpos = this.blockPosition();
		int j = (int) vec3.y - blockpos.getY();
		if (j > 2) {
			i = 4;
		} else if (j < -2) {
			i = -4;
		}

		int k = 6;
		int l = 8;
		int i1 = blockpos.distManhattan(p_27881_);
		if (i1 < 15) {
			k = i1 / 2;
			l = i1 / 2;
		}

		Vec3 vec31 = AirRandomPos.getPosTowards(this, k, l, i, vec3, (float) Math.PI / 10F);
		if (vec31 != null) {
			this.navigation.setMaxVisitedNodesMultiplier(0.5F);
			this.navigation.moveTo(vec31.x, vec31.y, vec31.z, 1.0D);
		}
	}

	// public int getTravellingTicks() {
	// return this.goToHiveGoal.travellingTicks;
	// }

	// public List<BlockPos> getBlacklistedHives() {
	// return this.goToHiveGoal.blacklistedTargets;
	// }

	boolean wantsToEnterHive() {
		if (this.stayOutOfHiveCountdown <= 0 && this.getTarget() == null) {
			boolean flag = this.level().isRaining() || this.level().isNight();
			return flag && !this.isHiveNearFire();
		} else {
			return false;
		}
	}

	public void setStayOutOfHiveCountdown(int p_27916_) {
		this.stayOutOfHiveCountdown = p_27916_;
	}

	public float getRollAmount(float p_27936_) {
		return Mth.lerp(p_27936_, this.rollAmountO, this.rollAmount);
	}

	private void updateRollAmount() {
		this.rollAmountO = this.rollAmount;
		if (this.isRolling()) {
			this.rollAmount = Math.min(1.0F, this.rollAmount + 0.2F);
		} else {
			this.rollAmount = Math.max(0.0F, this.rollAmount - 0.24F);
		}

	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		if (this.isInWaterOrBubble()) {
			++this.underWaterTicks;
		} else {
			this.underWaterTicks = 0;
		}

		if (this.underWaterTicks > 20) {
			this.hurtServer(serverLevel, this.damageSources().drown(), 1.0F);
		}
	}

	private boolean isHiveNearFire() {
		if (this.hivePos == null) {
			return false;
		} else {
			BlockEntity blockentity = this.level().getBlockEntity(this.hivePos);
			return blockentity instanceof BeehiveBlockEntity
					&& ((BeehiveBlockEntity) blockentity).isFireNearby();
		}
	}

	private boolean doesHiveHaveSpace(BlockPos p_27885_) {
		BlockEntity blockentity = this.level().getBlockEntity(p_27885_);
		if (blockentity instanceof BeehiveBlockEntity) {
			return !((BeehiveBlockEntity) blockentity).isFull();
		} else {
			return false;
		}
	}

	public boolean hasHive() {
		return this.hivePos != null;
	}

	@Nullable
	public BlockPos getHivePos() {
		return this.hivePos;
	}

	public GoalSelector getGoalSelector() {
		return this.goalSelector;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!this.level().isClientSide) {
			if (this.stayOutOfHiveCountdown > 0) {
				--this.stayOutOfHiveCountdown;
			}

			if (this.remainingCooldownBeforeLocatingNewHive > 0) {
				--this.remainingCooldownBeforeLocatingNewHive;
			}

			boolean flag = this.getTarget() != null && this.getTarget().distanceToSqr(this) < 4.0D;
			this.setRolling(flag);
			if (this.tickCount % 20 == 0 && !this.isHiveValid()) {
				this.hivePos = null;
			}
		}
	}

	boolean isHiveValid() {
		if (!this.hasHive()) {
			return false;
		} else {
			BlockEntity blockentity = this.level().getBlockEntity(this.hivePos);
			// TODO: Wasp nest
			return blockentity instanceof BeehiveBlockEntity;
		}
	}

	private boolean isRolling() {
		return this.getFlag(2);
	}

	private void setRolling(boolean p_27930_) {
		this.setFlag(2, p_27930_);
	}

	boolean isTooFarAway(BlockPos p_27890_) {
		return !this.closerThan(p_27890_, 32);
	}

	protected void setFlag(int p_27833_, boolean p_27834_) {
		if (p_27834_) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) | p_27833_));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) & ~p_27833_));
		}

	}

	protected boolean getFlag(int p_27922_) {
		return (this.entityData.get(DATA_FLAGS_ID) & p_27922_) != 0;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.FLYING_SPEED, 1.2F).add(Attributes.MOVEMENT_SPEED, 1.2F)
				.add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	protected PathNavigation createNavigation(Level p_27815_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
			@Override
			public boolean isStableDestination(BlockPos p_27947_) {
				return !Wasp.this.level().getBlockState(p_27947_.below()).isAir();
			}
		};
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(false);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	@Override
	protected void playStepSound(BlockPos p_27820_, BlockState p_27821_) {
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_27845_) {
		return SoundEvents.BEE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BEE_DEATH;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_,
			BlockPos p_27757_) {
	}

	@Override
	public boolean isFlapping() {
		return this.isFlying() && this.tickCount % TICKS_PER_FLAP == 0;
	}

	@Override
	public boolean isFlying() {
		return !this.onGround();
	}

	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
		if (this.isInvulnerableTo(serverLevel, source)) {
			return false;
		} else {
			return super.hurtServer(serverLevel, source, amount);
		}
	}

	@Override
	protected void jumpInLiquid(TagKey<Fluid> p_27825_) {
		this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0.0D, 0.5F * this.getEyeHeight(), this.getBbWidth() * 0.2F);
	}

	boolean closerThan(BlockPos p_27817_, int p_27818_) {
		return p_27817_.closerThan(this.blockPosition(), p_27818_);
	}

	class BeeWanderGoal extends Goal {
		BeeWanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			return Wasp.this.navigation.isDone() && Wasp.this.random.nextInt(10) == 0;
		}

		@Override
		public boolean canContinueToUse() {
			return Wasp.this.navigation.isInProgress();
		}

		@Override
		public void start() {
			Vec3 vec3 = this.findPos();
			if (vec3 != null) {
				Wasp.this.navigation.moveTo(Wasp.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0D);
			}
		}

		@Nullable
		private Vec3 findPos() {
			Vec3 vec3 = Wasp.this.getViewVector(0.0F);

			Vec3 vec32 = HoverRandomPos.getPos(Wasp.this, 8, 7, vec3.x, vec3.z, ((float) Math.PI / 2F), 3, 1);
			return vec32 != null
					? vec32
					: AirAndWaterRandomPos.getPos(Wasp.this, 8, 4, -2, vec3.x, vec3.z,
							(float) Math.PI / 2F);
		}
	}
}
