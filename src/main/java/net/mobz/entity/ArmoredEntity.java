package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZWeapons;

public class ArmoredEntity extends ZombieEntity {
   public ArmoredEntity(EntityType<? extends ZombieEntity> entityType, World world) {
      super(entityType, world);
      this.xpReward = 20;
   }

   public static AttributeModifierMap.MutableAttribute createArmoredEntityAttributes() {
      return MonsterEntity.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  Configs.instance.ArmoredZombieLife * Configs.instance.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.23D)
            .add(Attributes.ATTACK_DAMAGE,
                  Configs.instance.ArmoredZombieAttack * Configs.instance.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.ARMOR, 3D)
            .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
   }

   @Override
   public boolean canPickUpLoot() {
      return false;
   }

   @Override
   protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
      super.populateDefaultEquipmentSlots(localDifficulty_1);
      if (this.level.getDifficulty() == Difficulty.NORMAL) {
         this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
         this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
         this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
      } else {
         if (this.level.getDifficulty() == Difficulty.EASY) {
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
            this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
            this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.LEATHER_BOOTS));
         } else {
            if (this.level.getDifficulty() == Difficulty.HARD) {
               this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
               this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
               this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.DIAMOND_BOOTS));
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
   public boolean checkSpawnObstruction(IWorldReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
            && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.ARMORED)
            && Configs.instance.ArmoredZombieSpawn;

   }

   @Override
   public boolean isBaby() {
      return false;
   }
}
