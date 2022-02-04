package net.mobz.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;


public class WithEntity extends BlazeEntity {
   private float field_7214 = 0.5F;
   private int field_7215;
   private static final DataParameter<Byte> BLAZE_FLAGS;

   public WithEntity(EntityType<? extends WithEntity> entityType_1, World world_1) {
      super(entityType_1, world_1);
      this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
      this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
      this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
      this.xpReward = 14;
   }

   public static AttributeModifierMap.MutableAttribute createWithEntityAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.WitherBlazeLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.23D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.WitherBlazeAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 48.0D);
   }

   @Override
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.WITHENTITY)
            && MobZ.configs.WitherBlazeSpawn;

   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(4, new WithEntity.ShootFireballGoal(this));
      this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
      this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers());
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
      this.initCustomGoals();
   }

   protected void initCustomGoals() {
      this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(PiglinEntity.class));
      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(skeli3.class));
      this.targetSelector.addGoal(4, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(SkeletonEntity.class));
      this.targetSelector.addGoal(5, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(WitherSkeletonEntity.class));
      this.targetSelector.addGoal(6, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Dog.class));
      this.targetSelector.addGoal(7, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(WithEntity.class));
      this.targetSelector.addGoal(8, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(LavaGolem.class));
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(BLAZE_FLAGS, (byte) 0);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return SoundEvents.BLAZE_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return SoundEvents.BLAZE_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.BLAZE_DEATH;
   }

   @Override
   public float getBrightness() {
      return 1.0F;
   }

   @Override
   public void aiStep() {
      if (!this.onGround && this.getDeltaMovement().y < 0.0D) {
         this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
      }

      if (this.level.isClientSide) {
         if (this.random.nextInt(24) == 0 && !this.isSilent()) {
            this.level.playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, MobZSounds.NOTHINGEVENT,
                  this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F,
                  false);
         }

         for (int int_1 = 0; int_1 < 2; ++int_1) {
            this.level.addParticle(ParticleTypes.FALLING_LAVA, this.getRandomX(0.5D), this.getRandomY(),
                  this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
         }
      }

      super.aiStep();
   }

   @Override
   protected void customServerAiStep() {
      if (this.isInWater()) {
         this.hurt(DamageSource.DROWN, 1.0F);
      }

      --this.field_7215;
      if (this.field_7215 <= 0) {
         this.field_7215 = 100;
         this.field_7214 = 0.5F + (float) this.random.nextGaussian() * 3.0F;
      }

      LivingEntity livingEntity_1 = this.getTarget();
      if (livingEntity_1 != null && livingEntity_1.getEyeY() > this.getEyeY() + (double) this.field_7214
            && this.canAttack(livingEntity_1)) {
         Vector3d vec3d_1 = this.getDeltaMovement();
         this.setDeltaMovement(
               this.getDeltaMovement().add(0.0D, (0.30000001192092896D - vec3d_1.y) * 0.30000001192092896D, 0.0D));
         this.hasImpulse = true;
      }

      super.customServerAiStep();
   }

   @Override
   public boolean causeFallDamage(float float_1, float float_2) {
      return false;
   }

   @Override
   public boolean isOnFire() {
      return this.isCharged();
   }

   private boolean isCharged() {
      return ((Byte) this.entityData.get(BLAZE_FLAGS) & 1) != 0;
   }

   private void setCharged(boolean boolean_1) {
      byte byte_1 = (Byte) this.entityData.get(BLAZE_FLAGS);
      if (boolean_1) {
         byte_1 = (byte) (byte_1 | 1);
      } else {
         byte_1 &= -2;
      }

      this.entityData.set(BLAZE_FLAGS, byte_1);
   }

   static {
      BLAZE_FLAGS = EntityDataManager.defineId(WithEntity.class, DataSerializers.BYTE);
   }

   static class ShootFireballGoal extends Goal {
      private final WithEntity blaze;
      private int field_7218;
      private int field_7217;
      private int field_19420;

      public ShootFireballGoal(WithEntity blazeEntity_1) {
         this.blaze = blazeEntity_1;
         this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
      }

      public boolean canUse() {
         LivingEntity livingEntity_1 = this.blaze.getTarget();
         return livingEntity_1 != null && livingEntity_1.isAlive() && this.blaze.canAttack(livingEntity_1);
      }

      public void start() {
         this.field_7218 = 0;
      }

      public void stop() {
         this.blaze.setCharged(false);
         this.field_19420 = 0;
      }

      public void tick() {
         --this.field_7217;
         LivingEntity livingEntity_1 = this.blaze.getTarget();
         if (livingEntity_1 != null) {
            boolean boolean_1 = this.blaze.getSensing().canSee(livingEntity_1);
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

               this.blaze.getMoveControl().setWantedPosition(livingEntity_1.getX(), livingEntity_1.getY(), livingEntity_1.getZ(),
                     1.0D);
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
                     float float_1 = MathHelper.sqrt(MathHelper.sqrt(double_1)) * 0.5F;
                     this.blaze.level.levelEvent((PlayerEntity) null, 1018, this.blaze.blockPosition(), 0);

                     for (int int_1 = 0; int_1 < 1; ++int_1) {
                        SmallFireballEntity smallFireballEntity_1 = new SmallFireballEntity(this.blaze.level,
                              this.blaze, double_2 + this.blaze.getRandom().nextGaussian() * (double) float_1, double_3,
                              double_4 + this.blaze.getRandom().nextGaussian() * (double) float_1);
                        smallFireballEntity_1.absMoveTo(smallFireballEntity_1.getX(),
                              this.blaze.getY(0.5D) + 0.5D, smallFireballEntity_1.getZ());
                        this.blaze.level.addFreshEntity(smallFireballEntity_1);
                     }
                  }
               }

               this.blaze.getLookControl().setLookAt(livingEntity_1, 10.0F, 10.0F);
            } else if (this.field_19420 < 5) {
               this.blaze.getMoveControl().setWantedPosition(livingEntity_1.getX(), livingEntity_1.getY(), livingEntity_1.getZ(),
                     1.0D);
            }

            super.tick();
         }
      }

      private double method_6995() {
         return this.blaze.getAttribute(Attributes.FOLLOW_RANGE).getValue();
      }
   }
}
