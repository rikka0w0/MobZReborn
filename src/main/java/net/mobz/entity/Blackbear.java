package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mobz.init.MobZSounds;

public class Blackbear extends PandaEntity {

   public Blackbear(EntityType<? extends PandaEntity> entityType, World world) {
      super(entityType, world);
   }

   public static AttributeModifierMap.MutableAttribute createBlackbearAttributes() {
      return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.17D)
            .add(Attributes.ATTACK_DAMAGE, 6.0D);
   }

   @Override
	public boolean canHoldItem(ItemStack stack) {
      return false;
   }

   @Override
   public boolean isSneezing() {
      return false;
   }

   @Override
   public boolean isSitting() {
      return false;
   }

   @Override
   public boolean isOnBack() {
      return false;
   }

   @Override
   public boolean isEating() {
      return false;
   }

   @Override
   public boolean isRolling() {
      return false;
   }

   @Override
   public boolean isLazy() {
      return false;
   }

   @Override
   public boolean isWorried() {
      return false;
   }

   @Override
   public boolean isPlayful() {
      return false;
   }

   @Override
   public boolean isWeak() {
      return false;
   }

   @Override
   public boolean isAggressive() {
      return false;
   }

   @Override
   public boolean doHurtTarget(Entity target) {
      this.playSound(MobZSounds.PBITEEVENT, 1.0F, 1.0F);
      return super.doHurtTarget(target);
   }

   @Override
   public boolean isScared() {
      return false;
   }

   @Override
   public boolean isFood(ItemStack stack) {
      return false;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return SoundEvents.POLAR_BEAR_AMBIENT;

   }

   @Override
   protected void playStepSound(BlockPos pos, BlockState state) {
      this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.POLAR_BEAR_DEATH;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.POLAR_BEAR_HURT;
   }

}