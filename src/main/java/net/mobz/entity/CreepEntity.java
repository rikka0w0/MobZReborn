package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class CreepEntity extends Creeper {

   public CreepEntity(EntityType<? extends Creeper> entityType, Level world) {
      super(entityType, world);
   }

   public static AttributeSupplier.Builder createCreepEntityAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.FrostCreeperLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 32.0D);
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return MobZSounds.SAYCREEPEVENT.get();
   }

   @Override
   protected SoundEvent getDeathSound() {
      return MobZSounds.DEATHCREEPEVENT.get();
   }

   @Override
   public boolean checkSpawnObstruction(LevelReader view) {
      return MobZ.configs.FrostCreeperSpawn && MobSpawnHelper.checkSpawnObstruction(this, view);

   }
}
