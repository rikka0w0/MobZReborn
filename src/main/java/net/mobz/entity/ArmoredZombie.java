package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZWeapons;

public class ArmoredZombie extends Zombie {
   public ArmoredZombie(EntityType<? extends Zombie> entityType, Level world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createMobzAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.armored_zombie.life * MobZ.configs.life_multiplier)
            .add(Attributes.MOVEMENT_SPEED, 0.23D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.armored_zombie.attack * MobZ.configs.damage_multiplier)
            .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.ARMOR, 3D)
            .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
   }

   @Override
   public boolean canPickUpLoot() {
      return false;
   }

   @Override
   protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
       super.populateDefaultEquipmentSlots(random, difficulty);
      if (this.level().getDifficulty() == Difficulty.NORMAL) {
         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ARMORED_SWORD.get()));
         this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
         this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
      } else {
         if (this.level().getDifficulty() == Difficulty.EASY) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ARMORED_SWORD.get()));
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
            this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
         } else {
            if (this.level().getDifficulty() == Difficulty.HARD) {
               this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ARMORED_SWORD.get()));
               this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
               this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
            } else {
            }
         }
      }
   }

   @Override
   protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
      return;
   }

   @Override
   public boolean checkSpawnObstruction(LevelReader view) {
      return MobZ.configs.armored_zombie.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
   }

   @Override
   public boolean isBaby() {
      return false;
   }
}
