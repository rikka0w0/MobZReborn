package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.entity.attack.GolemAttack;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class LavaGolem extends IronGolemEntity {

   public LavaGolem(EntityType<? extends IronGolemEntity> entityType, World world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeModifierMap.MutableAttribute createLavaGolemAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.LavaGolemLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.LavaGolemAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.5D);
   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(1, new GolemAttack(this, 1.0D, true));
      this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
      // this.goalSelector.addGoal(5, new OfferFlowerGoal(this));
      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
      this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
      this.initCustomGoals();
   }

   protected void initCustomGoals() {
      this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(ZoglinEntity.class));
      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(skeli3.class));
      this.targetSelector.addGoal(4, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(SkeletonEntity.class));
      this.targetSelector.addGoal(5, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(WitherSkeletonEntity.class));
      this.targetSelector.addGoal(6, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(Dog.class));
      this.targetSelector.addGoal(7, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(WithEntity.class));
      this.targetSelector.addGoal(8, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(PiglinEntity.class));
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return MobZSounds.GOLEMHITEVENT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return MobZSounds.GOLEMDEATHEVENT;
   }

   @Override
   protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
      this.playSound(MobZSounds.GOLEMWALKEVENT, 1.0F, 1.0F);
   }

   @Override
   public boolean removeWhenFarAway(double double_1) {
      return true;
   }

   @Override
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getCurrentDifficultyAt(posentity).getDifficulty() != Difficulty.PEACEFUL
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.LAVAGOLEM)
            && MobZ.configs.LavaGolemSpawn;

   }

}