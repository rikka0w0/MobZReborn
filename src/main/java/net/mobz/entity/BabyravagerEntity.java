package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class BabyravagerEntity extends Ravager {

  public BabyravagerEntity(EntityType<? extends Ravager> type, Level world) {
    super(type, world);
  }

  public static AttributeSupplier.Builder createBabyravagerEntityAttributes() {
    return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            MobZ.configs.BabyRavagerLife * MobZ.configs.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.3D)
        .add(Attributes.ATTACK_DAMAGE,
            MobZ.configs.BabyRavagerAttack * MobZ.configs.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.ARMOR, 1.5D)
        .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D).add(Attributes.ATTACK_KNOCKBACK, 1.5D);
  }

  @Override
  public SoundEvent getCelebrateSound() {
    return MobZSounds.NOTHINGEVENT.get();
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return MobZSounds.RAVIDLEEVENT.get();
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource source) {
    return MobZSounds.RAVHURTEVENT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.RAVDEATHEVENT.get();
  }

  @Override
  public boolean canBeControlledByRider() {
    return false;
  }

}