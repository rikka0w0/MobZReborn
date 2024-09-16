package net.mobz.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class ZombieMage extends SpellcasterIllager {
	private Sheep wololoTarget;

	public ZombieMage(EntityType<? extends SpellcasterIllager> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH,
					MobZ.configs.zombie_mage.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.45D)
				.add(Attributes.ATTACK_DAMAGE,
					MobZ.configs.zombie_mage.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new ZombieMage.LookAtTargetOrWololoTarget());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(4, new ZombieMage.SummonVexGoal());
		this.goalSelector.addGoal(5, new ZombieMage.ConjureFangsGoal());
		this.goalSelector.addGoal(6, new ZombieMage.WololoGoal());
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[] { Raider.class })).setAlertOthers());
		this.targetSelector.addGoal(2,
				(new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3,
				(new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.zombie_mage.spawn
				&& !this.isPatrolLeader()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.zombie_mage.can_join_raid.check(this);
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.EVOKER_CELEBRATE;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
	}

	@Override
	public boolean isAlliedTo(Entity other) {
		if (other == null) {
			return false;
		} else if (other == this) {
			return true;
		} else if (super.isAlliedTo(other)) {
			return true;
		} else if (other instanceof SmallZombie) {
			return this.isAlliedTo(((SmallZombie) other).getOwner());
		} else if (other instanceof LivingEntity && other.getType().is(EntityTypeTags.ILLAGER_FRIENDS)) {
			return this.getTeam() == null && other.getTeam() == null;
		} else {
			return false;
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.EVOKER_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.EVOKER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.EVOKER_HURT;
	}

	private void setWololoTarget(@Nullable Sheep sheep) {
		this.wololoTarget = sheep;
	}

	@Nullable
	private Sheep getWololoTarget() {
		return this.wololoTarget;
	}

	@Override
	protected SoundEvent getCastingSoundEvent() {
		return SoundEvents.EVOKER_CAST_SPELL;
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverWorld, int wave, boolean p_37845_) {
	}

	public class WololoGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final TargetingConditions purpleSheepPredicate = TargetingConditions.forNonCombat().range(16.0D)
				.selector((sheepTarget) -> {
					return ((Sheep) sheepTarget).getColor() == DyeColor.BLUE;
				});

		public WololoGoal() {
			super();
		}

		@Override
		public boolean canUse() {
			if (ZombieMage.this.getTarget() != null) {
				return false;
			} else if (ZombieMage.this.isCastingSpell()) {
				return false;
			} else if (ZombieMage.this.tickCount < this.nextAttackTickCount) {
				return false;
			} else if (!ZombieMage.this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				return false;
			} else {
				List<Sheep> list = ZombieMage.this.level().getNearbyEntities(Sheep.class, this.purpleSheepPredicate,
						ZombieMage.this, ZombieMage.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
				if (list.isEmpty()) {
					return false;
				} else {
					ZombieMage.this.setWololoTarget(list.get(ZombieMage.this.random.nextInt(list.size())));
					return true;
				}
			}
		}

		@Override
		public boolean canContinueToUse() {
			return ZombieMage.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
		}

		@Override
		public void stop() {
			super.stop();
			ZombieMage.this.setWololoTarget((Sheep) null);
		}

		@Override
		protected void performSpellCasting() {
			Sheep sheepEntity = ZombieMage.this.getWololoTarget();
			if (sheepEntity != null && sheepEntity.isAlive()) {
				sheepEntity.setColor(DyeColor.RED);
			}

		}

		@Override
		protected int getCastWarmupTime() {
			return 40;
		}

		@Override
		protected int getCastingTime() {
			return 60;
		}

		@Override
		protected int getCastingInterval() {
			return 140;
		}

		@Override
		protected SoundEvent getSpellPrepareSound() {
			return SoundEvents.EVOKER_PREPARE_WOLOLO;
		}

		@Override
		protected SpellcasterIllager.IllagerSpell getSpell() {
			return SpellcasterIllager.IllagerSpell.WOLOLO;
		}
	}

	class SummonVexGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final TargetingConditions closeVexPredicate
		= TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

		private SummonVexGoal() {
			super();
		}

		@Override
		public boolean canUse() {
			if (!super.canUse()) {
				return false;
			} else {
				int i = ZombieMage.this.level().getNearbyEntities(SmallZombie.class, this.closeVexPredicate, ZombieMage.this,
						ZombieMage.this.getBoundingBox().inflate(16.0D)).size();
				return ZombieMage.this.random.nextInt(8) + 1 > i;
			}
		}

		@Override
		protected int getCastingTime() {
			return 100;
		}

		@Override
		protected int getCastingInterval() {
			return 340;
		}

		@Override
		protected void performSpellCasting() {
			ServerLevel serverWorld = (ServerLevel) ZombieMage.this.level();

			for (int i = 0; i < 3; ++i) {
				BlockPos blockPos = ZombieMage.this.blockPosition().offset(-2 + ZombieMage.this.random.nextInt(5), 1,
						-2 + ZombieMage.this.random.nextInt(5));
				SmallZombie SmallZombie = MobZEntities.SMALL_ZOMBIE.get().create(ZombieMage.this.level());
				SmallZombie.moveTo(blockPos, 0.0F, 0.0F);
				SmallZombie.finalizeSpawn(serverWorld, ZombieMage.this.level().getCurrentDifficultyAt(blockPos),
						MobSpawnType.MOB_SUMMONED, null);
				SmallZombie.setOwner(ZombieMage.this);
				SmallZombie.setBounds(blockPos);
				SmallZombie.setLifeTicks(20 * (30 + ZombieMage.this.random.nextInt(90)));
				ZombieMage.this.level().addFreshEntity(SmallZombie);
			}

		}

		@Override
		protected SoundEvent getSpellPrepareSound() {
			return SoundEvents.EVOKER_PREPARE_SUMMON;
		}

		@Override
		protected SpellcasterIllager.IllagerSpell getSpell() {
			return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
		}
	}

	class ConjureFangsGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private ConjureFangsGoal() {
			super();
		}

		@Override
		protected int getCastingTime() {
			return 40;
		}

		@Override
		protected int getCastingInterval() {
			return 100;
		}

		@Override
		protected void performSpellCasting() {
			LivingEntity livingEntity = ZombieMage.this.getTarget();
			double d = Math.min(livingEntity.getY(), ZombieMage.this.getY());
			double e = Math.max(livingEntity.getY(), ZombieMage.this.getY()) + 1.0D;
			float f = (float) Mth.atan2(livingEntity.getZ() - ZombieMage.this.getZ(),
					livingEntity.getX() - ZombieMage.this.getX());
			int j;
			if (ZombieMage.this.distanceToSqr(livingEntity) < 9.0D) {
				float h;
				for (j = 0; j < 5; ++j) {
					h = f + j * 3.1415927F * 0.4F;
					this.conjureFangs(ZombieMage.this.getX() + Mth.cos(h) * 1.5D,
							ZombieMage.this.getZ() + Mth.sin(h) * 1.5D, d, e, h, 0);
				}

				for (j = 0; j < 8; ++j) {
					h = f + j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
					this.conjureFangs(ZombieMage.this.getX() + Mth.cos(h) * 2.5D,
							ZombieMage.this.getZ() + Mth.sin(h) * 2.5D, d, e, h, 3);
				}
			} else {
				for (j = 0; j < 16; ++j) {
					double l = 1.25D * (j + 1);
					int m = 1 * j;
					this.conjureFangs(ZombieMage.this.getX() + Mth.cos(f) * l,
					ZombieMage.this.getZ() + Mth.sin(f) * l, d, e, f, m);
				}
			}

		}

		private void conjureFangs(double x, double z, double maxY, double y, float f, int warmup) {
			BlockPos blockPos = BlockPos.containing(x, y, z);
			boolean bl = false;
			double d = 0.0D;

			do {
				BlockPos blockPos2 = blockPos.below();
				BlockState blockState = ZombieMage.this.level().getBlockState(blockPos2);
				if (blockState.isFaceSturdy(ZombieMage.this.level(), blockPos2, Direction.UP)) {
					if (!ZombieMage.this.level().isEmptyBlock(blockPos)) {
						BlockState blockState2 = ZombieMage.this.level().getBlockState(blockPos);
						VoxelShape voxelShape = blockState2.getCollisionShape(ZombieMage.this.level(), blockPos);
						if (!voxelShape.isEmpty()) {
							d = voxelShape.max(Direction.Axis.Y);
						}
					}

					bl = true;
					break;
				}

				blockPos = blockPos.below();
			} while (blockPos.getY() >= Mth.floor(maxY) - 1);

			if (bl) {
				ZombieMage.this.level().addFreshEntity(new EvokerFangs(ZombieMage.this.level(), x,
					blockPos.getY() + d, z, f, warmup, ZombieMage.this));
			}

		}

		@Override
		protected SoundEvent getSpellPrepareSound() {
			return SoundEvents.EVOKER_PREPARE_ATTACK;
		}

		@Override
		protected SpellcasterIllager.IllagerSpell getSpell() {
			return SpellcasterIllager.IllagerSpell.FANGS;
		}
	}

	class LookAtTargetOrWololoTarget extends SpellcasterIllager.SpellcasterCastingSpellGoal {
		private LookAtTargetOrWololoTarget() {
			super();
		}

		@Override
		public void tick() {
			if (ZombieMage.this.getTarget() != null) {
				ZombieMage.this.getLookControl().setLookAt(ZombieMage.this.getTarget(),
						ZombieMage.this.getMaxHeadYRot(), ZombieMage.this.getMaxHeadXRot());
			} else if (ZombieMage.this.getWololoTarget() != null) {
				ZombieMage.this.getLookControl().setLookAt(ZombieMage.this.getWololoTarget(),
						ZombieMage.this.getMaxHeadYRot(), ZombieMage.this.getMaxHeadXRot());
			}

		}
	}

}
