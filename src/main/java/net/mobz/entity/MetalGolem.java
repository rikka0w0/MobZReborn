package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.DefendVillageTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ReturnToVillageGoal;
import net.minecraft.entity.ai.goal.ShowVillagerFlowerGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.entity.attack.GolemAttack;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;

public class MetalGolem extends IronGolemEntity {

  public MetalGolem(EntityType<? extends IronGolemEntity> entityType, World world) {
    super(entityType, world);
    this.xpReward = 25;
  }

  public static AttributeModifierMap.MutableAttribute createMetalGolemAttributes() {
    return MobEntity.createMobAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.MetalGolemLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.KNOCKBACK_RESISTANCE, 1.5D)
        .add(Attributes.ATTACK_DAMAGE,
            Configs.instance.MetalGolemAttack * Configs.instance.DamageMultiplicatorMob);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new GolemAttack(this, 1.0D, true));
    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 36.0F));
    this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.8D, false));
    this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 0.9D, false, 10, () -> {
      return false;
    }));
    this.goalSelector.addGoal(5, new ShowVillagerFlowerGoal(this));
    this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.65D));
    this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
    this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (livingEntity) -> {
      return livingEntity instanceof IMob && !(livingEntity instanceof CreeperEntity);
    }));
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    IronGolemEntity.Cracks crack = this.getCrackiness();
    boolean bl = super.hurt(source, amount);
    if (bl && this.getCrackiness() != crack) {
      this.playSound(MobZSounds.MGOLEMBREAKEVENT, 1.0F, 1.0F);
    }

    return bl;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource_1) {
    return MobZSounds.MGOLEMHITEVENT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.GOLEMDEATHEVENT;
  }

  @Override
  protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
    this.playSound(MobZSounds.GOLEMWALKEVENT, 1.0F, 1.0F);
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
  public boolean checkSpawnObstruction(IWorldReader view) {
    return Configs.instance.MetalGolemSpawn;
  }

  @Override
  protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getItemInHand(hand);
    Item item = itemStack.getItem();
    if (item != MobZItems.HARDENEDMETAL_INGOT) {
      return ActionResultType.PASS;
    } else {
      float f = this.getHealth();
      this.heal(40.0F);
      if (this.getHealth() == f) {
        return ActionResultType.PASS;
      } else {
        float g = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
        this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, g);
        if (!player.abilities.instabuild) {
          itemStack.shrink(1);
        }

        return ActionResultType.sidedSuccess(this.level.isClientSide);
      }
    }
  }

}