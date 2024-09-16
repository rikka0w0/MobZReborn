package net.mobz.entity;

import java.util.EnumSet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.entity.attack.FrostballEntity;
import net.mobz.init.MobZSounds;

public class FrostBlaze extends Blaze {
	private float field_7214 = 0.5F;
	private int field_7215;
	private static final EntityDataAccessor<Byte> BLAZE_FLAGS;

	public FrostBlaze(EntityType<? extends FrostBlaze> entityType_1, Level world_1) {
		super(entityType_1, world_1);
		this.setPathfindingMalus(PathType.WATER, 8.0F);
		this.xpReward = 10;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.frost_blaze.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.23D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.frost_blaze.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.frost_blaze.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new FrostBlaze.ShootFireballGoal(this));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BLAZE_FLAGS, (byte) 0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SNOW_GOLEM_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.SNOW_GOLEM_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SNOW_GOLEM_DEATH;
	}

	@Override
	public void aiStep() {
		if (!this.onGround() && this.getDeltaMovement().y < 0.0D) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
		}

		if (this.level().isClientSide) {
			if (this.random.nextInt(24) == 0 && !this.isSilent()) {
				this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D,
						MobZSounds.NOTHINGEVENT.get(), this.getSoundSource(), 1.0F + this.random.nextFloat(),
						this.random.nextFloat() * 0.7F + 0.3F, false);
			}

			for (int int_1 = 0; int_1 < 2; ++int_1) {
				this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, this.getRandomX(0.5D), this.getRandomY(),
						this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}

		super.aiStep();
	}

	@Override
	protected void customServerAiStep() {
		--this.field_7215;
		if (this.field_7215 <= 0) {
			this.field_7215 = 100;
			this.field_7214 = 0.5F + (float) this.random.nextGaussian() * 3.0F;
		}

		LivingEntity livingEntity_1 = this.getTarget();
		if (livingEntity_1 != null && livingEntity_1.getEyeY() > this.getEyeY() + this.field_7214
				&& this.canAttack(livingEntity_1)) {
			Vec3 vec3d_1 = this.getDeltaMovement();
			this.setDeltaMovement(
					this.getDeltaMovement().add(0.0D, (0.30000001192092896D - vec3d_1.y) * 0.30000001192092896D, 0.0D));
			this.hasImpulse = true;
		}

		super.customServerAiStep();
	}

	@Override
	public boolean causeFallDamage(float float_1, float float_2, DamageSource source) {
		return false;
	}

	@Override
	public boolean displayFireAnimation() {
		return false;
	}

	@Override
	public boolean isOnFire() {
		return this.isCharged();
	}

	private boolean isCharged() {
		return (this.entityData.get(BLAZE_FLAGS) & 1) != 0;
	}

	private void setCharged(boolean boolean_1) {
		byte byte_1 = this.entityData.get(BLAZE_FLAGS);
		if (boolean_1) {
			byte_1 = (byte) (byte_1 | 1);
		} else {
			byte_1 &= -2;
		}

		this.entityData.set(BLAZE_FLAGS, byte_1);
	}

	////////////////////////////////////////////////
	public void attack(LivingEntity target, float f) {
		Snowball snowballEntity = new Snowball(this.level(), this);
		double d = target.getEyeY() - 1.100000023841858D;
		double e = target.getX() - this.getX();
		double g = d - snowballEntity.getY();
		double h = target.getZ() - this.getZ();
		double i = Math.sqrt(e * e + h * h) * 0.2F;
		snowballEntity.shoot(e, g + i, h, 1.6F, 12.0F);
		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(snowballEntity);
	}

	/////////////////////////////////////
	static {
		BLAZE_FLAGS = SynchedEntityData.defineId(FrostBlaze.class, EntityDataSerializers.BYTE);
	}

	static class ShootFireballGoal extends Goal {
		private final FrostBlaze blaze;
		private int field_7218;
		private int field_7217;
		private int field_19420;

		public ShootFireballGoal(FrostBlaze blazeEntity_1) {
			this.blaze = blazeEntity_1;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity livingEntity_1 = this.blaze.getTarget();
			return livingEntity_1 != null && livingEntity_1.isAlive() && this.blaze.canAttack(livingEntity_1);
		}

		@Override
		public void start() {
			this.field_7218 = 0;
		}

		@Override
		public void stop() {
			this.blaze.setCharged(false);
			this.field_19420 = 0;
		}

		@Override
		public void tick() {
			--this.field_7217;
			LivingEntity livingEntity_1 = this.blaze.getTarget();
			if (livingEntity_1 != null) {
				boolean boolean_1 = this.blaze.getSensing().hasLineOfSight(livingEntity_1);
				if (boolean_1) {
					this.field_19420 = 0;
				} else {
					++this.field_19420;
				}

				double double_1 = this.blaze.distanceToSqr(livingEntity_1);
				if (double_1 < 4.0D) {
					if (!boolean_1) {
						return;
					}

					if (this.field_7217 <= 0) {
						this.field_7217 = 20;
						this.blaze.doHurtTarget(livingEntity_1);
					}

					this.blaze.getMoveControl().setWantedPosition(livingEntity_1.getX(), livingEntity_1.getY(),
							livingEntity_1.getZ(), 1.0D);
				} else if (double_1 < this.method_6995() * this.method_6995() && boolean_1) {
					double double_2 = livingEntity_1.getX() - this.blaze.getX();
					double double_3 = livingEntity_1.getY(0.5D) - this.blaze.getY(0.5D);
					double double_4 = livingEntity_1.getZ() - this.blaze.getZ();
					if (this.field_7217 <= 0) {
						++this.field_7218;
						if (this.field_7218 == 1) {
							this.field_7217 = 60;
							this.blaze.setCharged(true);
						} else if (this.field_7218 <= 4) {
							this.field_7217 = 6;
						} else {
							this.field_7217 = 100;
							this.field_7218 = 0;
							this.blaze.setCharged(false);
						}

						if (this.field_7218 > 1) {
							double float_1 = Math.sqrt(Math.sqrt(double_1)) * 0.5F;
							this.blaze.level().levelEvent((Player) null, 1018, this.blaze.blockPosition(), 0);

							for (int fireCount = 0; fireCount < 1; ++fireCount) {
								Vec3 displacementVector = new Vec3(
										double_2 + this.blaze.getRandom().nextGaussian() * float_1,
										double_3,
										double_4 + this.blaze.getRandom().nextGaussian() * float_1
									);
								FrostballEntity bullet = new FrostballEntity(this.blaze.level(),
										this.blaze, displacementVector.normalize());
								bullet.setPos(bullet.getX(), this.blaze.getY(0.5D) + 0.5D, bullet.getZ());
								this.blaze.level().addFreshEntity(bullet);
							}
						}
					}

					this.blaze.getLookControl().setLookAt(livingEntity_1, 10.0F, 10.0F);
				} else if (this.field_19420 < 5) {
					this.blaze.getMoveControl().setWantedPosition(livingEntity_1.getX(), livingEntity_1.getY(),
							livingEntity_1.getZ(), 1.0D);
				}

				super.tick();
			}
		}

		private double method_6995() {
			return this.blaze.getAttribute(Attributes.FOLLOW_RANGE).getValue();
		}
	}
}
