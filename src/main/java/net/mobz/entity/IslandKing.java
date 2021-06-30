package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class IslandKing extends VindicatorEntity {
  private int cooldown = 0;
  private final int requiredCooldown = 200;

  public IslandKing(EntityType<? extends VindicatorEntity> entityType, World world) {
    super(entityType, world);
    this.xpReward = 50;

  }

  public static AttributeModifierMap.MutableAttribute createIslandKingAttributes() {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.KingCharlesLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.32D)
        .add(Attributes.ATTACK_DAMAGE,
            Configs.instance.KingCharlesAttack * Configs.instance.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 18.0D);
  }

  @Override
  protected void playStepSound(BlockPos pos, BlockState state) {
    if (!state.getMaterial().isLiquid()) {
      this.playSound(MobZSounds.LEATHERWALKEVENT, 0.15F, 1F);
    }
  }

  @Override
  protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
    super.populateDefaultEquipmentSlots(localDifficulty_1);
    if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
      this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
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
  protected void customServerAiStep() {
    EffectInstance slow = new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 0, false, false);

    if (getTarget() != null && !level.isClientSide && distanceToSqr(getTarget()) < 4096D && canSee(getTarget())) {

      cooldown++;
      if (cooldown >= requiredCooldown) {
        cooldown = 0;
        attack(getTarget(), 1);
      }
      if (cooldown >= (requiredCooldown - 20)) {
        getTarget().addEffect(slow);
      }
    } else {
      cooldown = 0;
    }
  }

  public void attack(LivingEntity target, float f) {
    BlockPos blockPos = IslandKing.this.blockPosition().offset(-2 + IslandKing.this.random.nextInt(5), 1,
        -2 + IslandKing.this.random.nextInt(5));
    IslandVexEntity vexEntity = (IslandVexEntity) MobZEntities.ISLANDVEXENTITY.create(IslandKing.this.level);
    vexEntity.moveTo(blockPos, 0.0F, 0.0F);
    vexEntity.finalizeSpawn((IServerWorld) IslandKing.this.level, IslandKing.this.level.getCurrentDifficultyAt(blockPos),
        SpawnReason.MOB_SUMMONED, null, (CompoundNBT) null);
    IslandKing.this.level.addFreshEntity(vexEntity);
  }

}
