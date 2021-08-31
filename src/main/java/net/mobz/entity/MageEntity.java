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

public class MageEntity extends SpellcasterIllager {
   private Sheep wololoTarget;

   public MageEntity(EntityType<? extends MageEntity> entityType, Level world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createMageEntityAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.SpiderMageLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.45D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.SpiderMageAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 20.0D);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new MageEntity.LookAtTargetOrWololoTarget());
      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
      this.goalSelector.addGoal(4, new MageEntity.SummonSpider());
      this.goalSelector.addGoal(5, new MageEntity.ConjureFangsGoal());
      this.goalSelector.addGoal(6, new MageEntity.WololoGoal());
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
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   @Override
   public boolean checkSpawnObstruction(LevelReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.MAGEENTITY)
            && MobZ.configs.SpiderMageSpawn;

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
   public boolean isAlliedTo(Entity entity_1) {
      if (entity_1 == null) {
         return false;
      } else if (entity_1 == this) {
         return true;
      } else if (super.isAlliedTo(entity_1)) {
         return true;
      } else if (entity_1 instanceof SpiSmall) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return MobZSounds.EVEIDLEEVENT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return MobZSounds.EVEDEATHEVENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return MobZSounds.EVEHURTEVENT;
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

      public boolean canUse() {
         if (MageEntity.this.getTarget() != null) {
            return false;
         } else if (MageEntity.this.isCastingSpell()) {
            return false;
         } else if (MageEntity.this.tickCount < this.nextAttackTickCount) {
            return false;
         } else if (!MageEntity.this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return false;
         } else {
            List<Sheep> list = MageEntity.this.level.getNearbyEntities(Sheep.class, this.purpleSheepPredicate,
                  MageEntity.this, MageEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            if (list.isEmpty()) {
               return false;
            } else {
               MageEntity.this.setWololoTarget((Sheep) list.get(MageEntity.this.random.nextInt(list.size())));
               return true;
            }
         }
      }

      public boolean canContinueToUse() {
         return MageEntity.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
      }

      public void stop() {
         super.stop();
         MageEntity.this.setWololoTarget((Sheep) null);
      }

      protected void performSpellCasting() {
         Sheep sheepEntity = MageEntity.this.getWololoTarget();
         if (sheepEntity != null && sheepEntity.isAlive()) {
            sheepEntity.setColor(DyeColor.RED);
         }

      }

      protected int getCastWarmupTime() {
         return 40;
      }

      protected int getCastingTime() {
         return 60;
      }

      protected int getCastingInterval() {
         return 140;
      }

      protected SoundEvent getSpellPrepareSound() {
         return SoundEvents.EVOKER_PREPARE_WOLOLO;
      }

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

      public boolean canUse() {
         if (!super.canUse()) {
            return false;
         } else {
            int i = MageEntity.this.level.getNearbyEntities(SpiSmall.class, this.closeVexPredicate, MageEntity.this,
                  MageEntity.this.getBoundingBox().inflate(16.0D)).size();
            return MageEntity.this.random.nextInt(8) + 1 > i;
         }
      }

      protected int getCastingTime() {
         return 100;
      }

      protected int getCastingInterval() {
         return 340;
      }

      protected void performSpellCasting() {
			ServerLevel serverWorld = (ServerLevel) MageEntity.this.level;

         for (int i = 0; i < 3; ++i) {
            BlockPos blockPos = MageEntity.this.blockPosition().offset(-2 + MageEntity.this.random.nextInt(5), 1,
                  -2 + MageEntity.this.random.nextInt(5));
            SpiSmall vexEntity = (SpiSmall) MobZEntities.SPISMALL.create(MageEntity.this.level);
            vexEntity.moveTo(blockPos, 0.0F, 0.0F);
				vexEntity.finalizeSpawn(serverWorld, MageEntity.this.level.getCurrentDifficultyAt(blockPos),
                  MobSpawnType.MOB_SUMMONED, null, (CompoundTag) null);
            // vexEntity.setOwner(MageEntity.this);
            // vexEntity.setBounds(blockPos);
            vexEntity.setLifeTicks(20 * (30 + MageEntity.this.random.nextInt(90)));
            MageEntity.this.level.addFreshEntity(vexEntity);
         }

      }

      protected SoundEvent getSpellPrepareSound() {
         return SoundEvents.EVOKER_PREPARE_SUMMON;
      }

      protected SpellcasterIllager.IllagerSpell getSpell() {
         return SpellcasterIllager.IllagerSpell.SUMMON_VEX;
      }
   }

   class ConjureFangsGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
      private ConjureFangsGoal() {
         super();
      }

      protected int getCastingTime() {
         return 40;
      }

      protected int getCastingInterval() {
         return 100;
      }

      protected void performSpellCasting() {
         LivingEntity livingEntity = MageEntity.this.getTarget();
         double d = Math.min(livingEntity.getY(), MageEntity.this.getY());
         double e = Math.max(livingEntity.getY(), MageEntity.this.getY()) + 1.0D;
         float f = (float) Mth.atan2(livingEntity.getZ() - MageEntity.this.getZ(),
               livingEntity.getX() - MageEntity.this.getX());
         int j;
         if (MageEntity.this.distanceToSqr(livingEntity) < 9.0D) {
            float h;
            for (j = 0; j < 5; ++j) {
               h = f + (float) j * 3.1415927F * 0.4F;
               this.conjureFangs(MageEntity.this.getX() + (double) Mth.cos(h) * 1.5D,
                     MageEntity.this.getZ() + (double) Mth.sin(h) * 1.5D, d, e, h, 0);
            }

            for (j = 0; j < 8; ++j) {
               h = f + (float) j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
               this.conjureFangs(MageEntity.this.getX() + (double) Mth.cos(h) * 2.5D,
                     MageEntity.this.getZ() + (double) Mth.sin(h) * 2.5D, d, e, h, 3);
            }
         } else {
            for (j = 0; j < 16; ++j) {
               double l = 1.25D * (double) (j + 1);
               int m = 1 * j;
               this.conjureFangs(MageEntity.this.getX() + (double) Mth.cos(f) * l,
                     MageEntity.this.getZ() + (double) Mth.sin(f) * l, d, e, f, m);
            }
         }

      }

      private void conjureFangs(double x, double z, double maxY, double y, float f, int warmup) {
         BlockPos blockPos = new BlockPos(x, y, z);
         boolean bl = false;
         double d = 0.0D;

         do {
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = MageEntity.this.level.getBlockState(blockPos2);
            if (blockState.isFaceSturdy(MageEntity.this.level, blockPos2, Direction.UP)) {
               if (!MageEntity.this.level.isEmptyBlock(blockPos)) {
                  BlockState blockState2 = MageEntity.this.level.getBlockState(blockPos);
                  VoxelShape voxelShape = blockState2.getCollisionShape(MageEntity.this.level, blockPos);
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
            MageEntity.this.level.addFreshEntity(new EvokerFangs(MageEntity.this.level, x,
                  (double) blockPos.getY() + d, z, f, warmup, MageEntity.this));
         }

      }

      protected SoundEvent getSpellPrepareSound() {
         return SoundEvents.EVOKER_PREPARE_ATTACK;
      }

      protected SpellcasterIllager.IllagerSpell getSpell() {
         return SpellcasterIllager.IllagerSpell.FANGS;
      }
   }

   class LookAtTargetOrWololoTarget extends SpellcasterIllager.SpellcasterCastingSpellGoal {
      private LookAtTargetOrWololoTarget() {
         super();
      }

      public void tick() {
         if (MageEntity.this.getTarget() != null) {
            MageEntity.this.getLookControl().setLookAt(MageEntity.this.getTarget(),
                  (float) MageEntity.this.getMaxHeadYRot(), (float) MageEntity.this.getMaxHeadXRot());
         } else if (MageEntity.this.getWololoTarget() != null) {
            MageEntity.this.getLookControl().setLookAt(MageEntity.this.getWololoTarget(),
                  (float) MageEntity.this.getMaxHeadYRot(), (float) MageEntity.this.getMaxHeadXRot());
         }

      }
   }

}
