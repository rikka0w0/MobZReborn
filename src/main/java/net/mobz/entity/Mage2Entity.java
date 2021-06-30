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

public class Mage2Entity extends SpellcastingIllagerEntity {
   private SheepEntity wololoTarget;

   public Mage2Entity(EntityType<? extends SpellcastingIllagerEntity> entityType, World world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeModifierMap.MutableAttribute createMage2EntityAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  Configs.instance.ZombieMageLife * Configs.instance.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.45D)
            .add(Attributes.ATTACK_DAMAGE,
                  Configs.instance.ZombieMageAttack * Configs.instance.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 20.0D);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new SwimGoal(this));
      this.goalSelector.addGoal(1, new Mage2Entity.LookAtTargetOrWololoTarget());
      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
      this.goalSelector.addGoal(4, new Mage2Entity.SummonVexGoal());
      this.goalSelector.addGoal(5, new Mage2Entity.ConjureFangsGoal());
      this.goalSelector.addGoal(6, new Mage2Entity.WololoGoal());
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
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.MAGE2ENTITY)
            && Configs.instance.ZombieMageSpawn;

   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
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
   public boolean isAlliedTo(Entity other) {
      if (other == null) {
         return false;
      } else if (other == this) {
         return true;
      } else if (super.isAlliedTo(other)) {
         return true;
      } else if (other instanceof SmallZombie) {
         return this.isAlliedTo(((SmallZombie) other).getOwner());
      } else if (other instanceof LivingEntity && ((LivingEntity) other).getMobType() == CreatureAttribute.ILLAGER) {
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
         if (Mage2Entity.this.getTarget() != null) {
            return false;
         } else if (Mage2Entity.this.isCastingSpell()) {
            return false;
         } else if (Mage2Entity.this.tickCount < this.nextAttackTickCount) {
            return false;
         } else if (!Mage2Entity.this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return false;
         } else {
            List<SheepEntity> list = Mage2Entity.this.level.getNearbyEntities(SheepEntity.class, this.purpleSheepPredicate,
                  Mage2Entity.this, Mage2Entity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            if (list.isEmpty()) {
               return false;
            } else {
               Mage2Entity.this.setWololoTarget((SheepEntity) list.get(Mage2Entity.this.random.nextInt(list.size())));
               return true;
            }
         }
      }

      public boolean canContinueToUse() {
         return Mage2Entity.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
      }

      public void stop() {
         super.stop();
         Mage2Entity.this.setWololoTarget((SheepEntity) null);
      }

      protected void performSpellCasting() {
         SheepEntity sheepEntity = Mage2Entity.this.getWololoTarget();
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

   class SummonVexGoal extends SpellcastingIllagerEntity.UseSpellGoal {
      private final EntityPredicate closeVexPredicate;

      private SummonVexGoal() {
         super();
         this.closeVexPredicate = (new EntityPredicate()).range(16.0D).allowUnseeable()
               .ignoreInvisibilityTesting().allowInvulnerable().allowSameTeam();
      }

      public boolean canUse() {
         if (!super.canUse()) {
            return false;
         } else {
            int i = Mage2Entity.this.level.getNearbyEntities(SmallZombie.class, this.closeVexPredicate, Mage2Entity.this,
                  Mage2Entity.this.getBoundingBox().inflate(16.0D)).size();
            return Mage2Entity.this.random.nextInt(8) + 1 > i;
         }
      }

      protected int getCastingTime() {
         return 100;
      }

      protected int getCastingInterval() {
         return 340;
      }

      protected void performSpellCasting() {
			ServerWorld serverWorld = (ServerWorld) Mage2Entity.this.level;

         for (int i = 0; i < 3; ++i) {
            BlockPos blockPos = Mage2Entity.this.blockPosition().offset(-2 + Mage2Entity.this.random.nextInt(5), 1,
                  -2 + Mage2Entity.this.random.nextInt(5));
            SmallZombie SmallZombie = (SmallZombie) MobZEntities.SMALLZOMBIE.create(Mage2Entity.this.level);
            SmallZombie.moveTo(blockPos, 0.0F, 0.0F);
				SmallZombie.finalizeSpawn(serverWorld, Mage2Entity.this.level.getCurrentDifficultyAt(blockPos),
                  SpawnReason.MOB_SUMMONED, null, (CompoundNBT) null);
            SmallZombie.setOwner(Mage2Entity.this);
            SmallZombie.setBounds(blockPos);
            SmallZombie.setLifeTicks(20 * (30 + Mage2Entity.this.random.nextInt(90)));
            Mage2Entity.this.level.addFreshEntity(SmallZombie);
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
         LivingEntity livingEntity = Mage2Entity.this.getTarget();
         double d = Math.min(livingEntity.getY(), Mage2Entity.this.getY());
         double e = Math.max(livingEntity.getY(), Mage2Entity.this.getY()) + 1.0D;
         float f = (float) MathHelper.atan2(livingEntity.getZ() - Mage2Entity.this.getZ(),
               livingEntity.getX() - Mage2Entity.this.getX());
         int j;
         if (Mage2Entity.this.distanceToSqr(livingEntity) < 9.0D) {
            float h;
            for (j = 0; j < 5; ++j) {
               h = f + (float) j * 3.1415927F * 0.4F;
               this.conjureFangs(Mage2Entity.this.getX() + (double) MathHelper.cos(h) * 1.5D,
                     Mage2Entity.this.getZ() + (double) MathHelper.sin(h) * 1.5D, d, e, h, 0);
            }

            for (j = 0; j < 8; ++j) {
               h = f + (float) j * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
               this.conjureFangs(Mage2Entity.this.getX() + (double) MathHelper.cos(h) * 2.5D,
                     Mage2Entity.this.getZ() + (double) MathHelper.sin(h) * 2.5D, d, e, h, 3);
            }
         } else {
            for (j = 0; j < 16; ++j) {
               double l = 1.25D * (double) (j + 1);
               int m = 1 * j;
               this.conjureFangs(Mage2Entity.this.getX() + (double) MathHelper.cos(f) * l,
                     Mage2Entity.this.getZ() + (double) MathHelper.sin(f) * l, d, e, f, m);
            }
         }

      }

      private void conjureFangs(double x, double z, double maxY, double y, float f, int warmup) {
         BlockPos blockPos = new BlockPos(x, y, z);
         boolean bl = false;
         double d = 0.0D;

         do {
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = Mage2Entity.this.level.getBlockState(blockPos2);
            if (blockState.isFaceSturdy(Mage2Entity.this.level, blockPos2, Direction.UP)) {
               if (!Mage2Entity.this.level.isEmptyBlock(blockPos)) {
                  BlockState blockState2 = Mage2Entity.this.level.getBlockState(blockPos);
                  VoxelShape voxelShape = blockState2.getCollisionShape(Mage2Entity.this.level, blockPos);
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
            Mage2Entity.this.level.addFreshEntity(new EvokerFangsEntity(Mage2Entity.this.level, x,
                  (double) blockPos.getY() + d, z, f, warmup, Mage2Entity.this));
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
         if (Mage2Entity.this.getTarget() != null) {
            Mage2Entity.this.getLookControl().setLookAt(Mage2Entity.this.getTarget(),
                  (float) Mage2Entity.this.getMaxHeadYRot(), (float) Mage2Entity.this.getMaxHeadXRot());
         } else if (Mage2Entity.this.getWololoTarget() != null) {
            Mage2Entity.this.getLookControl().setLookAt(Mage2Entity.this.getWololoTarget(),
                  (float) Mage2Entity.this.getMaxHeadYRot(), (float) Mage2Entity.this.getMaxHeadXRot());
         }

      }
   }

}
