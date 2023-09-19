package net.mobz.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.MobType;
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
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class Mage2Entity extends SpellcasterIllager {
   private Sheep wololoTarget;

   public Mage2Entity(EntityType<? extends SpellcasterIllager> entityType, Level world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createMage2EntityAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.ZombieMageLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.45D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.ZombieMageAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 20.0D);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new Mage2Entity.LookAtTargetOrWololoTarget());
      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
      this.goalSelector.addGoal(4, new Mage2Entity.SummonVexGoal());
      this.goalSelector.addGoal(5, new Mage2Entity.ConjureFangsGoal());
      this.goalSelector.addGoal(6, new Mage2Entity.WololoGoal());
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
		return MobZ.configs.ZombieMageSpawn
				&& !this.isPatrolLeader()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && this.level().canSeeSky(this.blockPosition());
	}

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   @Override
   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);
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
      } else if (other instanceof LivingEntity && ((LivingEntity) other).getMobType() == MobType.ILLAGER) {
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
   public void applyRaidBuffs(int wave, boolean unused) {
   }

   public class WololoGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
      private final TargetingConditions purpleSheepPredicate = TargetingConditions.forNonCombat().range(16.0D)
    		  .selector((sheepTarget) -> {
    			  return ((Sheep)sheepTarget).getColor() == DyeColor.BLUE;
    		  });

      public WololoGoal() {
         super();
      }

      @Override
	public boolean canUse() {
         if (Mage2Entity.this.getTarget() != null) {
            return false;
         } else if (Mage2Entity.this.isCastingSpell()) {
            return false;
         } else if (Mage2Entity.this.tickCount < this.nextAttackTickCount) {
            return false;
         } else if (!Mage2Entity.this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return false;
         } else {
            List<Sheep> list = Mage2Entity.this.level().getNearbyEntities(Sheep.class, this.purpleSheepPredicate,
                  Mage2Entity.this, Mage2Entity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            if (list.isEmpty()) {
               return false;
            } else {
               Mage2Entity.this.setWololoTarget(list.get(Mage2Entity.this.random.nextInt(list.size())));
               return true;
            }
         }
      }

      @Override
	public boolean canContinueToUse() {
         return Mage2Entity.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
      }

      @Override
	public void stop() {
         super.stop();
         Mage2Entity.this.setWololoTarget((Sheep) null);
      }

      @Override
	protected void performSpellCasting() {
         Sheep sheepEntity = Mage2Entity.this.getWololoTarget();
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
            int i = Mage2Entity.this.level().getNearbyEntities(SmallZombie.class, this.closeVexPredicate, Mage2Entity.this,
                  Mage2Entity.this.getBoundingBox().inflate(16.0D)).size();
            return Mage2Entity.this.random.nextInt(8) + 1 > i;
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
			ServerLevel serverWorld = (ServerLevel) Mage2Entity.this.level();

         for (int i = 0; i < 3; ++i) {
            BlockPos blockPos = Mage2Entity.this.blockPosition().offset(-2 + Mage2Entity.this.random.nextInt(5), 1,
                  -2 + Mage2Entity.this.random.nextInt(5));
            SmallZombie SmallZombie = MobZEntities.SMALLZOMBIE.get().create(Mage2Entity.this.level());
            SmallZombie.moveTo(blockPos, 0.0F, 0.0F);
				SmallZombie.finalizeSpawn(serverWorld, Mage2Entity.this.level().getCurrentDifficultyAt(blockPos),
                  MobSpawnType.MOB_SUMMONED, null, (CompoundTag) null);
            SmallZombie.setOwner(Mage2Entity.this);
            SmallZombie.setBounds(blockPos);
            SmallZombie.setLifeTicks(20 * (30 + Mage2Entity.this.random.nextInt(90)));
            Mage2Entity.this.level().addFreshEntity(SmallZombie);
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
         LivingEntity livingEntity = Mage2Entity.this.getTarget();
         double d = Math.min(livingEntity.getY(), Mage2Entity.this.getY());
         double e = Math.max(livingEntity.getY(), Mage2Entity.this.getY()) + 1.0D;
         float f = (float) Mth.atan2(livingEntity.getZ() - Mage2Entity.this.getZ(),
               livingEntity.getX() - Mage2Entity.this.getX());
         int j;
         if (Mage2Entity.this.distanceToSqr(livingEntity) < 9.0D) {
            float h;
            for (j = 0; j < 5; ++j) {
               h = f + j * 3.1415927F * 0.4F;
               this.conjureFangs(Mage2Entity.this.getX() + Mth.cos(h) * 1.5D,
                     Mage2Entity.this.getZ() + Mth.sin(h) * 1.5D, d, e, h, 0);
            }

            for (j = 0; j < 8; ++j) {
               h = f + j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
               this.conjureFangs(Mage2Entity.this.getX() + Mth.cos(h) * 2.5D,
                     Mage2Entity.this.getZ() + Mth.sin(h) * 2.5D, d, e, h, 3);
            }
         } else {
            for (j = 0; j < 16; ++j) {
               double l = 1.25D * (j + 1);
               int m = 1 * j;
               this.conjureFangs(Mage2Entity.this.getX() + Mth.cos(f) * l,
                     Mage2Entity.this.getZ() + Mth.sin(f) * l, d, e, f, m);
            }
         }

      }

      private void conjureFangs(double x, double z, double maxY, double y, float f, int warmup) {
         BlockPos blockPos = BlockPos.containing(x, y, z);
         boolean bl = false;
         double d = 0.0D;

         do {
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = Mage2Entity.this.level().getBlockState(blockPos2);
            if (blockState.isFaceSturdy(Mage2Entity.this.level(), blockPos2, Direction.UP)) {
               if (!Mage2Entity.this.level().isEmptyBlock(blockPos)) {
                  BlockState blockState2 = Mage2Entity.this.level().getBlockState(blockPos);
                  VoxelShape voxelShape = blockState2.getCollisionShape(Mage2Entity.this.level(), blockPos);
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
            Mage2Entity.this.level().addFreshEntity(new EvokerFangs(Mage2Entity.this.level(), x,
                  blockPos.getY() + d, z, f, warmup, Mage2Entity.this));
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
         if (Mage2Entity.this.getTarget() != null) {
            Mage2Entity.this.getLookControl().setLookAt(Mage2Entity.this.getTarget(),
                  Mage2Entity.this.getMaxHeadYRot(), Mage2Entity.this.getMaxHeadXRot());
         } else if (Mage2Entity.this.getWololoTarget() != null) {
            Mage2Entity.this.getLookControl().setLookAt(Mage2Entity.this.getWololoTarget(),
                  Mage2Entity.this.getMaxHeadYRot(), Mage2Entity.this.getMaxHeadXRot());
         }

      }
   }

}
