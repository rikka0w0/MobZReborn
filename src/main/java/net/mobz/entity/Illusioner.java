package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Illusioner extends net.minecraft.world.entity.monster.Illusioner {

   public Illusioner(EntityType<? extends net.minecraft.world.entity.monster.Illusioner> entityType, Level world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.IllusionerLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.4D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.IllusionerAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 24.0D);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return MobZSounds.ILLUIDLEEVENT.get();
   }

   @Override
   protected SoundEvent getDeathSound() {
      return MobZSounds.ILLUDEATHEVENT.get();
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return MobZSounds.ILLUHURTEVENT.get();
   }

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && this.level().canSeeSky(this.blockPosition());
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.IllusionerSpawn
				&& this.level().isDay()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}
}
