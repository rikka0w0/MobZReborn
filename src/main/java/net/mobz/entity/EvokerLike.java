package net.mobz.entity;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class EvokerLike extends SpellcasterIllager {
	@Nullable
	private Sheep wololoTarget;

	protected final Class<? extends LivingEntity> summonClass;

	protected EvokerLike(EntityType<? extends EvokerLike> entityType, Level level,
			Class<? extends LivingEntity> summonClass) {
		super(entityType, level);
		this.summonClass = summonClass;
	}

	protected abstract void summon();

	@Nullable
	protected abstract Entity getSummonOwnerOf(Entity entity);

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new EvokerLike.LookAtVictimOrWololoTarget());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(4, new EvokerLike.SummonGoal());
		this.goalSelector.addGoal(5, new EvokerLike.ConjureFangsGoal());
		this.goalSelector.addGoal(6, new EvokerLike.WololoGoal());
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
		this.targetSelector.addGoal(2,
				new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3,
				new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.EVOKER_CELEBRATE;
	}

	@Override
	protected boolean considersEntityAsAlly(Entity other) {
		if (other == this) {
			return true;
		} else if (super.considersEntityAsAlly(other)) {
			return true;
		} else {
			Entity owner = this.getSummonOwnerOf(other);
			return owner == null ? false : this.considersEntityAsAlly(owner);
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

	@Override
	protected SoundEvent getCastingSoundEvent() {
		return SoundEvents.EVOKER_CAST_SPELL;
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverWorld, int wave, boolean p_37845_) {
	}

	public class WololoGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final TargetingConditions wololoTargeting = TargetingConditions.forNonCombat().range(16.0)
				.selector((targetEntity, serverLevel) -> ((Sheep) targetEntity).getColor() == DyeColor.BLUE);

		@Override
		public boolean canUse() {
			if (EvokerLike.this.getTarget() != null) {
				return false;
			} else if (EvokerLike.this.isCastingSpell()) {
				return false;
			} else if (EvokerLike.this.tickCount < this.nextAttackTickCount) {
				return false;
			} else {
				List<Sheep> list = getServerLevel(EvokerLike.this).getNearbyEntities(Sheep.class, this.wololoTargeting,
						EvokerLike.this, EvokerLike.this.getBoundingBox().inflate(16.0, 4.0, 16.0));
				if (list.isEmpty()) {
					return false;
				} else {
					EvokerLike.this.wololoTarget = list.get(EvokerLike.this.random.nextInt(list.size()));
					return true;
				}
			}
		}

		@Override
		public boolean canContinueToUse() {
			return EvokerLike.this.wololoTarget != null && this.attackWarmupDelay > 0;
		}

		@Override
		public void stop() {
			super.stop();
			EvokerLike.this.wololoTarget = null;
		}

		@Override
		protected void performSpellCasting() {
			Sheep sheep = EvokerLike.this.wololoTarget;
			if (sheep != null && sheep.isAlive()) {
				sheep.setColor(DyeColor.RED);
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

	class SummonGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final TargetingConditions nearbySummoned = TargetingConditions.forNonCombat().range(16.0)
				.ignoreLineOfSight().ignoreInvisibilityTesting();

		@Override
		public boolean canUse() {
			if (!super.canUse()) {
				return false;
			} else {
				int i = getServerLevel(EvokerLike.this.level()).getNearbyEntities(EvokerLike.this.summonClass, this.nearbySummoned,
						EvokerLike.this, EvokerLike.this.getBoundingBox().inflate(16.0)).size();
				return EvokerLike.this.random.nextInt(8) + 1 > i;
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
			EvokerLike.this.summon();
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

	public class ConjureFangsGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
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
			LivingEntity livingentity = EvokerLike.this.getTarget();
			double d0 = Math.min(livingentity.getY(), EvokerLike.this.getY());
			double d1 = Math.max(livingentity.getY(), EvokerLike.this.getY()) + 1.0;
			float f = (float) Mth.atan2(livingentity.getZ() - EvokerLike.this.getZ(),
					livingentity.getX() - EvokerLike.this.getX());
			if (EvokerLike.this.distanceToSqr(livingentity) < 9.0) {
				for (int i = 0; i < 5; i++) {
					float f1 = f + (float) i * (float) Math.PI * 0.4F;
					this.createSpellEntity(EvokerLike.this.getX() + (double) Mth.cos(f1) * 1.5,
							EvokerLike.this.getZ() + (double) Mth.sin(f1) * 1.5, d0, d1, f1, 0);
				}

				for (int k = 0; k < 8; k++) {
					float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
					this.createSpellEntity(EvokerLike.this.getX() + (double) Mth.cos(f2) * 2.5,
							EvokerLike.this.getZ() + (double) Mth.sin(f2) * 2.5, d0, d1, f2, 3);
				}
			} else {
				for (int l = 0; l < 16; l++) {
					double d2 = 1.25 * (double) (l + 1);
					int j = 1 * l;
					this.createSpellEntity(EvokerLike.this.getX() + (double) Mth.cos(f) * d2,
							EvokerLike.this.getZ() + (double) Mth.sin(f) * d2, d0, d1, f, j);
				}
			}
		}

		private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_,
				float p_32677_, int p_32678_) {
			BlockPos blockpos = BlockPos.containing(p_32673_, p_32676_, p_32674_);
			boolean flag = false;
			double d0 = 0.0;

			do {
				BlockPos blockpos1 = blockpos.below();
				BlockState blockstate = EvokerLike.this.level().getBlockState(blockpos1);
				if (blockstate.isFaceSturdy(EvokerLike.this.level(), blockpos1, Direction.UP)) {
					if (!EvokerLike.this.level().isEmptyBlock(blockpos)) {
						BlockState blockstate1 = EvokerLike.this.level().getBlockState(blockpos);
						VoxelShape voxelshape = blockstate1.getCollisionShape(EvokerLike.this.level(), blockpos);
						if (!voxelshape.isEmpty()) {
							d0 = voxelshape.max(Direction.Axis.Y);
						}
					}

					flag = true;
					break;
				}

				blockpos = blockpos.below();
			} while (blockpos.getY() >= Mth.floor(p_32675_) - 1);

			if (flag) {
				EvokerLike.this.level().addFreshEntity(new EvokerFangs(EvokerLike.this.level(), p_32673_,
						(double) blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, EvokerLike.this));
				EvokerLike.this.level().gameEvent(GameEvent.ENTITY_PLACE,
						new Vec3(p_32673_, (double) blockpos.getY() + d0, p_32674_),
						GameEvent.Context.of(EvokerLike.this));
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

	public class LookAtVictimOrWololoTarget extends SpellcasterIllager.SpellcasterCastingSpellGoal {
		@Override
		public void tick() {
			if (EvokerLike.this.getTarget() != null) {
				EvokerLike.this.getLookControl().setLookAt(EvokerLike.this.getTarget(),
						(float) EvokerLike.this.getMaxHeadYRot(), (float) EvokerLike.this.getMaxHeadXRot());
			} else if (EvokerLike.this.wololoTarget != null) {
				EvokerLike.this.getLookControl().setLookAt(EvokerLike.this.wololoTarget,
						(float) EvokerLike.this.getMaxHeadYRot(), (float) EvokerLike.this.getMaxHeadXRot());
			}
		}
	}
}
