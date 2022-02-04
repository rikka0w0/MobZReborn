package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class BabyravagerEntity extends RavagerEntity {

  public BabyravagerEntity(EntityType<? extends RavagerEntity> type, World world) {
    super(type, world);
  }

  public static AttributeModifierMap.MutableAttribute createBabyravagerEntityAttributes() {
    return MonsterEntity.createMonsterAttributes()
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