package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class FullIronEntity extends Zombie {

   public FullIronEntity(EntityType<? extends FullIronEntity> entityType_1, Level world_1) {
      super(entityType_1, world_1);
      this.xpReward = 20;
   }

   public static AttributeSupplier.Builder createFullIronEntityAttributes() {
      return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH,
                  MobZ.configs.SteveLife * MobZ.configs.LifeMultiplicatorMob)
            .add(Attributes.MOVEMENT_SPEED, 0.26D)
            .add(Attributes.ATTACK_DAMAGE,
                  MobZ.configs.SteveAttack * MobZ.configs.DamageMultiplicatorMob)
            .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
   }

   @Override
   public boolean canPickUpLoot() {
      return false;
   }

   @Override
   public boolean checkSpawnObstruction(LevelReader view) {
      BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
      BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
      return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
            && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis() && this.level
                  .getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.FULLIRONENTITY.get())
            && MobZ.configs.SteveSpawn;

   }

   @Override
   protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
      super.populateDefaultEquipmentSlots(localDifficulty_1);
      if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
         this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
         this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
         this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
         this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
         this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
         this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
      }
   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
      this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.9D));
      this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
   }

   @Override
   protected boolean convertsInWater() {
      return false;
   }

   @Override
   protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
      return;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return MobZSounds.NOTHINGEVENT.get();
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
   protected SoundEvent getStepSound() {
      return MobZSounds.LIGHTERARMORWALKEVENT.get();
   }

}
