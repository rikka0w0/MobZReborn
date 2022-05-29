package net.mobz.entity;

import java.util.UUID;
import java.util.function.Predicate;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public abstract class FriendEntity extends TamableAnimal implements NeutralMob {
    public static final Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE;

    public FriendEntity(EntityType<? extends FriendEntity> entityType, Level world) {
        super(entityType, world);
        this.setTame(false);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword.get()));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(5,
                new NonTameRandomTargetGoal<>(this, Animal.class, false, FOLLOW_TAMED_PREDICATE));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(MobZSounds.LEATHERWALKEVENT.get(), 0.15F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT.get();
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
            if (entity != null && !(entity instanceof Player)
                    && !(entity instanceof AbstractArrow)) {
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (this.level.isClientSide) {
            boolean bl = this.isOwnedBy(player) || this.isTame()
                    || isTameItem(itemStack) && !this.isTame() && !this.isAngry();
            return bl ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }

                    this.heal((float) item.getFoodProperties().getNutrition());
                    return InteractionResult.SUCCESS;
                }

                if (this.canEquipItemFromPlayer(itemStack)) {
                	ItemStack newStack = itemStack.copy();
                	this.setItemSlot(EquipmentSlot.OFFHAND, newStack);
                	return InteractionResult.SUCCESS;
                }

				InteractionResult interactionresult = super.mobInteract(player, hand);
				if (this.isOwnedBy(player)) {
					this.setOrderedToSit(!this.isOrderedToSit());
					this.jumping = false;
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					return InteractionResult.SUCCESS;
				}

				return interactionresult;
            } else if (isTameItem(itemStack) && !this.isAngry()) {
                if (!player.getAbilities().instabuild) {
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

                return InteractionResult.SUCCESS;
            }

            return super.mobInteract(player, hand);
        }
    }

    @Override
	public FriendEntity getBreedOffspring(ServerLevel world, AgeableMob passiveEntity) {
        FriendEntity FriendEntity = (FriendEntity) this.getType().create(this.level);
        UUID uUID = this.getOwnerUUID();
        if (uUID != null) {
            FriendEntity.setOwnerUUID(uUID);
            FriendEntity.setTame(true);
        }

        return FriendEntity;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            if (target instanceof FriendEntity) {
                FriendEntity FriendEntity = (FriendEntity) target;
                return !FriendEntity.isTame() || FriendEntity.getOwner() != owner;
            } else if (target instanceof Player && owner instanceof Player
                    && !((Player) owner).canHarmPlayer((Player) target)) {
                return false;
            } else if (target instanceof AbstractHorse && ((AbstractHorse) target).isTamed()) {
                return false;
            } else {
                return !(target instanceof TamableAnimal) || !((TamableAnimal) target).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public abstract boolean isFood(ItemStack stack);

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

    protected abstract boolean isTameItem(ItemStack stack);

    protected abstract boolean canEquipItemFromPlayer(ItemStack stack);

	// Friend = Katherine
	public static class KatherineEntity extends FriendEntity {
		public KatherineEntity(EntityType<? extends KatherineEntity> entityType, Level world) {
			super(entityType, world);
		}

		public static AttributeSupplier.Builder createFriendEntityAttributes() {
			return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.27D).add(Attributes.MAX_HEALTH, 20.0D)
					.add(Attributes.ATTACK_DAMAGE, 5.0D);
		}

	    @Override
	    public void setTame(boolean tamed) {
	        super.setTame(tamed);
	        if (tamed) {
	            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
	            this.setHealth(20.0F);
	        } else {
	            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
	        }

	        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	    }

		@Override
		public boolean isFood(ItemStack stack) {
			return stack.is(MobZ.KATHERINE_FOOD_TAG);
		}

		@Override
		protected boolean isTameItem(ItemStack stack) {
			return stack.is(MobZ.KATHERINE_TAME_TAG);
		}

		@Override
		protected boolean canEquipItemFromPlayer(ItemStack stack) {
			return stack.is(MobZ.KATHERINE_EQUIP_TAG);
		}
	}

	// Knight4 = Fiora
	public static class FioraEntity extends FriendEntity {
		public FioraEntity(EntityType<? extends FioraEntity> entityType, Level level) {
			super(entityType, level);
		}

		public static AttributeSupplier.Builder createKnight4EntityAttributes() {
			return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.27D).add(Attributes.MAX_HEALTH, 20.0D)
					.add(Attributes.ATTACK_DAMAGE, 5.0D);
		}

		@Override
		public boolean isFood(ItemStack stack) {
			return stack.is(MobZ.FIORA_FOOD_TAG);
		}

		@Override
		protected boolean isTameItem(ItemStack stack) {
			return stack.is(MobZ.FIORA_TAME_TAG);
		}

		@Override
		protected boolean canEquipItemFromPlayer(ItemStack stack) {
			return stack.is(MobZ.FIORA_EQUIP_TAG);
		}
	}
}
