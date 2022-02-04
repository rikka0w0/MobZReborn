package net.mobz.entity;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class IslandKnightSpecial extends ZombieEntity {

  public IslandKnightSpecial(EntityType<? extends ZombieEntity> entityType, World world) {
    super(entityType, world);
    this.xpReward = 30;
  }

  public static AttributeModifierMap.MutableAttribute createIslandKnightSpecialAttributes() {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            MobZ.configs.WilliamLife * MobZ.configs.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.32D)
        .add(Attributes.ATTACK_DAMAGE,
            MobZ.configs.WilliamAttack * MobZ.configs.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
  }

  @Override
  public boolean canPickUpLoot() {
    return false;
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new SwimGoal(this));
    this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
    this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
    this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
  }

  @Override
  protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
    super.populateDefaultEquipmentSlots(localDifficulty_1);
    if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
      this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
      this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(MobZItems.SHIELD));
    }
  }

  @Override
  public CreatureAttribute getMobType() {
    return CreatureAttribute.UNDEFINED;
  }

  @Override
  protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
    return;
  }

  @Override
  public boolean requiresCustomPersistence() {
    return true;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return MobZSounds.NOTHINGEVENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource_1) {
    return SoundEvents.PLAYER_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.PLAYER_DEATH;
  }

  @Override
  protected boolean isSunSensitive() {
    return false;
  }

  @Override
  protected SoundEvent getStepSound() {
    return MobZSounds.ARMORWALKEVENT;
  }

  @Override
  public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
    LivingEntity bob = (LivingEntity) target;
    EffectInstance weakness = new EffectInstance(Effects.WEAKNESS, 140, 0, false, false);
    if (target instanceof LivingEntity && !level.isClientSide) {
      bob.addEffect(weakness);
    }
  }

  @Override
  public boolean isBaby() {
    return false;
  }

  @Override
  public boolean isUnderWaterConverting() {
    return false;
  }
}
