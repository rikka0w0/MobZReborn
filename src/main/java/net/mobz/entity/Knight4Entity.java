package net.mobz.entity;

import java.util.UUID;
import java.util.function.Predicate;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class Knight4Entity extends TameableEntity implements IAngerable {
    public static final Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE;

    public Knight4Entity(EntityType<? extends Knight4Entity> entityType, World world) {
        super(entityType, world);
        this.setTame(false);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(5,
                new NonTamedTargetGoal<>(this, AnimalEntity.class, false, FOLLOW_TAMED_PREDICATE));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
    }

    public static AttributeModifierMap.MutableAttribute createKnight4EntityAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(MobZSounds.LEATHERWALKEVENT, 0.15F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof PlayerEntity)
                    && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean bl = target.hurt(DamageSource.mobAttack(this),
                (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (bl) {
            this.doEnchantDamageEffects(this, target);
        }

        return bl;
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (this.level.isClientSide) {
            boolean bl = this.isOwnedBy(player) || this.isTame()
                    || item == Items.GOLD_NUGGET && !this.isTame() && !this.isAngry();
            return bl ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.abilities.instabuild) {
                        itemStack.shrink(1);
                    }

                    this.heal((float) item.getFoodProperties().getNutrition());
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.BLUE_ORCHID))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.BLUE_ORCHID));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.CORNFLOWER))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.CORNFLOWER));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.WHITE_TULIP))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.WHITE_TULIP));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.PINK_TULIP))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.PINK_TULIP));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.RED_TULIP))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.RED_TULIP));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.ORANGE_TULIP))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.ORANGE_TULIP));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.ALLIUM))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.ALLIUM));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.AZURE_BLUET))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.AZURE_BLUET));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.DANDELION))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.DANDELION));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.OXEYE_DAISY))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.OXEYE_DAISY));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.LILY_OF_THE_VALLEY))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.LILY_OF_THE_VALLEY));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.POPPY))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.POPPY));
                    return ActionResultType.SUCCESS;
                }
                if (itemStack.sameItemStackIgnoreDurability(new ItemStack(Items.SHIELD))) {
                    this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));
                    return ActionResultType.SUCCESS;
                }

            } else if (item == Items.BONE && !this.isAngry()) {
                if (!player.abilities.instabuild) {
                    itemStack.shrink(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }

                return ActionResultType.SUCCESS;
            }

            return super.mobInteract(player, hand);
        }
    }

    @Override
	public Knight4Entity getBreedOffspring(ServerWorld world, AgeableEntity passiveEntity) {
        Knight4Entity Knight4Entity = (Knight4Entity) MobZEntities.KNIGHT4ENTITY.create(this.level);
        UUID uUID = this.getOwnerUUID();
        if (uUID != null) {
            Knight4Entity.setOwnerUUID(uUID);
            Knight4Entity.setTame(true);
        }

        return Knight4Entity;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof Knight4Entity) {
                Knight4Entity Knight4Entity = (Knight4Entity) target;
                return !Knight4Entity.isTame() || Knight4Entity.getOwner() != owner;
            } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity
                    && !((PlayerEntity) owner).canHarmPlayer((PlayerEntity) target)) {
                return false;
            } else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity) target).isTamed()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity) target).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.MELON_SLICE;
    }

    static {

        FOLLOW_TAMED_PREDICATE = (livingEntity) -> {
            EntityType<?> entityType = livingEntity.getType();
            return entityType == EntityType.SHEEP || entityType == EntityType.RABBIT || entityType == EntityType.FOX;
        };
    }

    @Override
    public int getRemainingPersistentAngerTime() {

        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int ticks) {

    }

    @Override
    public UUID getPersistentAngerTarget() {

        return null;
    }

    @Override
    public void setPersistentAngerTarget(UUID uuid) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

}
