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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class SpiderMage extends SpellcasterIllager {
	private Sheep wololoTarget;

	public SpiderMage(EntityType<? extends SpiderMage> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH,
					MobZ.configs.spider_mage.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.45D)
				.add(Attributes.ATTACK_DAMAGE,
					MobZ.configs.spider_mage.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new SpiderMage.LookAtTargetOrWololoTarget());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(4, new SpiderMage.SummonSpider());
		this.goalSelector.addGoal(5, new SpiderMage.ConjureFangsGoal());
		this.goalSelector.addGoal(6, new SpiderMage.WololoGoal());
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
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.spider_mage.can_join_raid.check(this);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.spider_mage.spawn && !this.isPatrolLeader()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.EVOKER_CELEBRATE;
	}

	@Override
	public boolean isAlliedTo(Entity entity_1) {
		if (entity_1 == null) {
			return false;
		} else if (entity_1 == this) {
			return true;
		} else if (super.isAlliedTo(entity_1)) {
			return true;
		} else if (entity_1 instanceof SmallSpider) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.EVEIDLEEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.EVEDEATHEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.EVEHURTEVENT.get();
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
			if (SpiderMage.this.getTarget() != null) {
				return false;
			} else if (SpiderMage.this.isCastingSpell()) {
				return false;
			} else if (SpiderMage.this.tickCount < this.nextAttackTickCount) {
				return false;
			} else if (!SpiderMage.this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				return false;
			} else {
				List<Sheep> list = SpiderMage.this.level().getNearbyEntities(Sheep.class, this.purpleSheepPredicate,
						SpiderMage.this, SpiderMage.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
				if (list.isEmpty()) {
					return false;
				} else {
					SpiderMage.this.setWololoTarget(list.get(SpiderMage.this.random.nextInt(list.size())));
					return true;
				}
			}
		}

		@Override
		public boolean canContinueToUse() {
			return SpiderMage.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
		}

		@Override
		public void stop() {
			super.stop();
			SpiderMage.this.setWololoTarget((Sheep) null);
		}

		@Override
		protected void performSpellCasting() {
			Sheep sheepEntity = SpiderMage.this.getWololoTarget();
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

	class SummonSpider extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final TargetingConditions closeVexPredicate =
			TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

		private SummonSpider() {
			super();
		}

		@Override
		public boolean canUse() {
			if (!super.canUse()) {
				return false;
			} else {
				int i = SpiderMage.this.level().getNearbyEntities(SmallSpider.class, this.closeVexPredicate, SpiderMage.this,
					SpiderMage.this.getBoundingBox().inflate(16.0D)).size();
				return SpiderMage.this.random.nextInt(8) + 1 > i;
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
			ServerLevel serverWorld = (ServerLevel) SpiderMage.this.level();

			for (int i = 0; i < 3; ++i) {
				BlockPos blockPos = SpiderMage.this.blockPosition().offset(-2 + SpiderMage.this.random.nextInt(5), 1,
						-2 + SpiderMage.this.random.nextInt(5));
				SmallSpider vexEntity = MobZEntities.SMALL_SPIDER.get().create(SpiderMage.this.level());
				vexEntity.moveTo(blockPos, 0.0F, 0.0F);
				vexEntity.finalizeSpawn(serverWorld, SpiderMage.this.level().getCurrentDifficultyAt(blockPos),
						MobSpawnType.MOB_SUMMONED, null);
				// vexEntity.setOwner(spider_mage.this);
				// vexEntity.setBounds(blockPos);
				vexEntity.setLifeTicks(20 * (30 + SpiderMage.this.random.nextInt(90)));
				SpiderMage.this.level().addFreshEntity(vexEntity);
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
			LivingEntity livingEntity = SpiderMage.this.getTarget();
			double d = Math.min(livingEntity.getY(), SpiderMage.this.getY());
			double e = Math.max(livingEntity.getY(), SpiderMage.this.getY()) + 1.0D;
			float f = (float) Mth.atan2(livingEntity.getZ() - SpiderMage.this.getZ(),
					livingEntity.getX() - SpiderMage.this.getX());
			int j;
			if (SpiderMage.this.distanceToSqr(livingEntity) < 9.0D) {
				float h;
				for (j = 0; j < 5; ++j) {
					h = f + j * 3.1415927F * 0.4F;
					this.conjureFangs(SpiderMage.this.getX() + Mth.cos(h) * 1.5D,
							SpiderMage.this.getZ() + Mth.sin(h) * 1.5D, d, e, h, 0);
				}

				for (j = 0; j < 8; ++j) {
					h = f + j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
					this.conjureFangs(SpiderMage.this.getX() + Mth.cos(h) * 2.5D,
							SpiderMage.this.getZ() + Mth.sin(h) * 2.5D, d, e, h, 3);
				}
			} else {
				for (j = 0; j < 16; ++j) {
					double l = 1.25D * (j + 1);
					int m = 1 * j;
					this.conjureFangs(SpiderMage.this.getX() + Mth.cos(f) * l,
						SpiderMage.this.getZ() + Mth.sin(f) * l, d, e, f, m);
				}
			}
		}

		private void conjureFangs(double x, double z, double maxY, double y, float f, int warmup) {
			BlockPos blockPos = BlockPos.containing(x, y, z);
			boolean bl = false;
			double d = 0.0D;

			do {
				BlockPos blockPos2 = blockPos.below();
				BlockState blockState = SpiderMage.this.level().getBlockState(blockPos2);
				if (blockState.isFaceSturdy(SpiderMage.this.level(), blockPos2, Direction.UP)) {
					if (!SpiderMage.this.level().isEmptyBlock(blockPos)) {
						BlockState blockState2 = SpiderMage.this.level().getBlockState(blockPos);
						VoxelShape voxelShape = blockState2.getCollisionShape(SpiderMage.this.level(), blockPos);
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
				SpiderMage.this.level().addFreshEntity(new EvokerFangs(SpiderMage.this.level(), x,
					blockPos.getY() + d, z, f, warmup, SpiderMage.this));
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
			if (SpiderMage.this.getTarget() != null) {
				SpiderMage.this.getLookControl().setLookAt(SpiderMage.this.getTarget(),
						SpiderMage.this.getMaxHeadYRot(), SpiderMage.this.getMaxHeadXRot());
			} else if (SpiderMage.this.getWololoTarget() != null) {
				SpiderMage.this.getLookControl().setLookAt(SpiderMage.this.getWololoTarget(),
						SpiderMage.this.getMaxHeadYRot(), SpiderMage.this.getMaxHeadXRot());
			}

		}
	}

}
