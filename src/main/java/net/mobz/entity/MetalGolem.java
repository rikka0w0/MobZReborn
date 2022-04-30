package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MoveBackToVillageGoal;
import net.minecraft.world.entity.ai.goal.OfferFlowerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.entity.attack.GolemAttack;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;

public class MetalGolem extends IronGolem {

  public MetalGolem(EntityType<? extends IronGolem> entityType, Level world) {
    super(entityType, world);
    this.xpReward = 25;
  }

  public static AttributeSupplier.Builder createMetalGolemAttributes() {
    return Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH,
            MobZ.configs.MetalGolemLife * MobZ.configs.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.KNOCKBACK_RESISTANCE, 1.5D)
        .add(Attributes.ATTACK_DAMAGE,
            MobZ.configs.MetalGolemAttack * MobZ.configs.DamageMultiplicatorMob);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new GolemAttack(this, 1.0D, true));
    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 36.0F));
    this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.8D, false));
    this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 0.9D, false, 10, () -> {
      return false;
    }));
    this.goalSelector.addGoal(5, new OfferFlowerGoal(this));
    this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.65D));
    this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
    this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (livingEntity) -> {
      return livingEntity instanceof Enemy && !(livingEntity instanceof Creeper);
    }));
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    IronGolem.Crackiness crack = this.getCrackiness();
    boolean bl = super.hurt(source, amount);
    if (bl && this.getCrackiness() != crack) {
      this.playSound(MobZSounds.MGOLEMBREAKEVENT.get(), 1.0F, 1.0F);
    }

    return bl;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource_1) {
    return MobZSounds.MGOLEMHITEVENT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.GOLEMDEATHEVENT.get();
  }

  @Override
  protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
    this.playSound(MobZSounds.GOLEMWALKEVENT.get(), 1.0F, 1.0F);
  }

  @Override
  public boolean removeWhenFarAway(double double_1) {
    return false;
  }

  @Override
  public boolean requiresCustomPersistence() {
    return true;
  }

  @Override
  public boolean checkSpawnObstruction(LevelReader view) {
    return MobZ.configs.MetalGolemSpawn;
  }

  @Override
  protected InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemStack = player.getItemInHand(hand);
    Item item = itemStack.getItem();
    if (item != MobZItems.HARDENEDMETAL_INGOT) {
      return InteractionResult.PASS;
    } else {
      float f = this.getHealth();
      this.heal(40.0F);
      if (this.getHealth() == f) {
        return InteractionResult.PASS;
      } else {
        float g = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
        this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, g);
        if (!player.getAbilities().instabuild) {
          itemStack.shrink(1);
        }

        return InteractionResult.sidedSuccess(this.level.isClientSide);
      }
    }
  }

}