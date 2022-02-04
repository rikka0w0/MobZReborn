package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;

public class IslandKnightNormal extends VindicatorEntity {

  public IslandKnightNormal(EntityType<? extends VindicatorEntity> entityType, World world) {
    super(entityType, world);
  }

  public static AttributeModifierMap.MutableAttribute createIslandKnightNormalAttributes() {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            MobZ.configs.IslandKnightLife * MobZ.configs.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.32D)
        .add(Attributes.ATTACK_DAMAGE,
            MobZ.configs.IslandKnightAttack * MobZ.configs.DamageMultiplicatorMob)
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
      this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
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

}
