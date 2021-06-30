package net.mobz.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class MageEntity extends SpellcastingIllagerEntity {
   private SheepEntity wololoTarget;

   public MageEntity(EntityType<? extends MageEntity> entityType, World world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeModifierMap.MutableAttribute createMageEntityAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  Configs.instance.SpiderMageLife * Configs.instance.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.45D)
            .add(Attributes.ATTACK_DAMAGE,
                  Configs.instance.SpiderMageAttack * Configs.instance.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 20.0D);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new SwimGoal(this));
      this.goalSelector.addGoal(1, new MageEntity.LookAtTargetOrWololoTarget());
      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
      this.goalSelector.addGoal(4, new MageEntity.SummonSpider());
      this.goalSelector.addGoal(5, new MageEntity.ConjureFangsGoal());
      this.goalSelector.addGoal(6, new MageEntity.WololoGoal());
      this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
      this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
      this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[] { AbstractRaiderEntity.class })).setAlertOthers());
      this.targetSelector.addGoal(2,
            (new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true)).setUnseenMemoryTicks(300));
      this.targetSelector.addGoal(3,
				(new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false)).setUnseenMemoryTicks(300));
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, false));
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   @Override
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.MAGEENTITY)
            && Configs.instance.SpiderMageSpawn;

   }

   @Override
   public void readAdditionalSaveData(CompoundNBT tag) {
      super.readAdditionalSaveData(tag);
   }

   @Override
   public SoundEvent getCelebrateSound() {
      return SoundEvents.EVOKER_CELEBRATE;
   }

   @Override
   public void addAdditionalSaveData(CompoundNBT tag) {
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

   private void setWololoTarget(@Nullable SheepEntity sheep) {
      this.wololoTarget = sheep;
   }

   @Nullable
   private SheepEntity getWololoTarget() {
      return this.wololoTarget;
   }

   @Override
   protected SoundEvent getCastingSoundEvent() {
      return SoundEvents.EVOKER_CAST_SPELL;
   }

   @Override
   public void applyRaidBuffs(int wave, boolean unused) {
   }

   public class WololoGoal extends SpellcastingIllagerEntity.UseSpellGoal {
      private final EntityPredicate purpleSheepPredicate = (new EntityPredicate()).range(16.0D)
            .allowInvulnerable().selector((livingEntity) -> {
               return ((SheepEntity) livingEntity).getColor() == DyeColor.BLUE;
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
            List<SheepEntity> list = MageEntity.this.level.getNearbyEntities(SheepEntity.class, this.purpleSheepPredicate,
                  MageEntity.this, MageEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            if (list.isEmpty()) {
               return false;
            } else {
               MageEntity.this.setWololoTarget((SheepEntity) list.get(MageEntity.this.random.nextInt(list.size())));
               return true;
            }
         }
      }

      public boolean canContinueToUse() {
         return MageEntity.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
      }

      public void stop() {
         super.stop();
         MageEntity.this.setWololoTarget((SheepEntity) null);
      }

      protected void performSpellCasting() {
         SheepEntity sheepEntity = MageEntity.this.getWololoTarget();
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

      protected SpellcastingIllagerEntity.SpellType getSpell() {
         return SpellcastingIllagerEntity.SpellType.WOLOLO;
      }
   }

   class SummonSpider extends SpellcastingIllagerEntity.UseSpellGoal {
      private final EntityPredicate closeVexPredicate;

      private SummonSpider() {
         super();
         this.closeVexPredicate = (new EntityPredicate()).range(16.0D).allowUnseeable()
               .ignoreInvisibilityTesting().allowInvulnerable().allowSameTeam();
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
			ServerWorld serverWorld = (ServerWorld) MageEntity.this.level;

         for (int i = 0; i < 3; ++i) {
            BlockPos blockPos = MageEntity.this.blockPosition().offset(-2 + MageEntity.this.random.nextInt(5), 1,
                  -2 + MageEntity.this.random.nextInt(5));
            SpiSmall vexEntity = (SpiSmall) MobZEntities.SPISMALL.create(MageEntity.this.level);
            vexEntity.moveTo(blockPos, 0.0F, 0.0F);
				vexEntity.finalizeSpawn(serverWorld, MageEntity.this.level.getCurrentDifficultyAt(blockPos),
                  SpawnReason.MOB_SUMMONED, null, (CompoundNBT) null);
            // vexEntity.setOwner(MageEntity.this);
            // vexEntity.setBounds(blockPos);
            vexEntity.setLifeTicks(20 * (30 + MageEntity.this.random.nextInt(90)));
            MageEntity.this.level.addFreshEntity(vexEntity);
         }

      }

      protected SoundEvent getSpellPrepareSound() {
         return SoundEvents.EVOKER_PREPARE_SUMMON;
      }

      protected SpellcastingIllagerEntity.SpellType getSpell() {
         return SpellcastingIllagerEntity.SpellType.SUMMON_VEX;
      }
   }

   class ConjureFangsGoal extends SpellcastingIllagerEntity.UseSpellGoal {
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
         float f = (float) MathHelper.atan2(livingEntity.getZ() - MageEntity.this.getZ(),
               livingEntity.getX() - MageEntity.this.getX());
         int j;
         if (MageEntity.this.distanceToSqr(livingEntity) < 9.0D) {
            float h;
            for (j = 0; j < 5; ++j) {
               h = f + (float) j * 3.1415927F * 0.4F;
               this.conjureFangs(MageEntity.this.getX() + (double) MathHelper.cos(h) * 1.5D,
                     MageEntity.this.getZ() + (double) MathHelper.sin(h) * 1.5D, d, e, h, 0);
            }

            for (j = 0; j < 8; ++j) {
               h = f + (float) j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
               this.conjureFangs(MageEntity.this.getX() + (double) MathHelper.cos(h) * 2.5D,
                     MageEntity.this.getZ() + (double) MathHelper.sin(h) * 2.5D, d, e, h, 3);
            }
         } else {
            for (j = 0; j < 16; ++j) {
               double l = 1.25D * (double) (j + 1);
               int m = 1 * j;
               this.conjureFangs(MageEntity.this.getX() + (double) MathHelper.cos(f) * l,
                     MageEntity.this.getZ() + (double) MathHelper.sin(f) * l, d, e, f, m);
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
         } while (blockPos.getY() >= MathHelper.floor(maxY) - 1);

         if (bl) {
            MageEntity.this.level.addFreshEntity(new EvokerFangsEntity(MageEntity.this.level, x,
                  (double) blockPos.getY() + d, z, f, warmup, MageEntity.this));
         }

      }

      protected SoundEvent getSpellPrepareSound() {
         return SoundEvents.EVOKER_PREPARE_ATTACK;
      }

      protected SpellcastingIllagerEntity.SpellType getSpell() {
         return SpellcastingIllagerEntity.SpellType.FANGS;
      }
   }

   class LookAtTargetOrWololoTarget extends SpellcastingIllagerEntity.CastingASpellGoal {
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
