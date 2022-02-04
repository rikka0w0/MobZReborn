package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Illusioner extends net.minecraft.entity.monster.IllusionerEntity {

   public Illusioner(EntityType<? extends net.minecraft.entity.monster.IllusionerEntity> entityType, World world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeModifierMap.MutableAttribute createAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.IllusionerLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.4D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.IllusionerAttack * MobZ.configs.DamageMultiplicatorMob)
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
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox()) && this.level.isDay()
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.ILLUSIONER)
            && MobZ.configs.IllusionerSpawn;

   }
}
