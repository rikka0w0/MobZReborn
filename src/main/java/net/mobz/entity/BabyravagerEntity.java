package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class BabyravagerEntity extends RavagerEntity {

  public BabyravagerEntity(EntityType<? extends RavagerEntity> type, World world) {
    super(type, world);
  }

  public static AttributeModifierMap.MutableAttribute createBabyravagerEntityAttributes() {
    return MonsterEntity.createMonsterAttributes()
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