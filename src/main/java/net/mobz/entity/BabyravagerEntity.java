package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZSounds;

public class BabyravagerEntity extends Ravager {

  public BabyravagerEntity(EntityType<? extends Ravager> type, Level world) {
    super(type, world);
  }

  public static AttributeSupplier.Builder createBabyravagerEntityAttributes() {
    return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.BabyRavagerLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.3D)
        .add(Attributes.ATTACK_DAMAGE,
            Configs.instance.BabyRavagerAttack * Configs.instance.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.ARMOR, 1.5D)
        .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D).add(Attributes.ATTACK_KNOCKBACK, 1.5D);
  }

  @Override
  public SoundEvent getCelebrateSound() {
    return MobZSounds.NOTHINGEVENT;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return MobZSounds.RAVIDLEEVENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource source) {
    return MobZSounds.RAVHURTEVENT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.RAVDEATHEVENT;
  }

  @Override
  public boolean canBeControlledByRider() {
    return false;
  }

}