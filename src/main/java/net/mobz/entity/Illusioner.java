package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Illusioner extends net.minecraft.world.entity.monster.Illusioner {

   public Illusioner(EntityType<? extends net.minecraft.world.entity.monster.Illusioner> entityType, Level world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  Configs.instance.IllusionerLife * Configs.instance.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.4D)
            .add(Attributes.ATTACK_DAMAGE,
                  Configs.instance.IllusionerAttack * Configs.instance.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 24.0D);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return MobZSounds.ILLUIDLEEVENT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return MobZSounds.ILLUDEATHEVENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return MobZSounds.ILLUHURTEVENT;
   }

   @Override
   public boolean checkSpawnObstruction(LevelReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox()) && this.level.isDay()
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.ILLUSIONER)
            && Configs.instance.IllusionerSpawn;

   }
}
