package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.mobz.init.MobZEntities;

public class GoldenChicken extends Chicken {
	private static final Ingredient BREEDING_INGREDIENT;
	public float field_6741;
	public float field_6743;
	public float field_6738;
	public float field_6736;
	public float field_6737 = 1.0F;
	public int eggLayTime;
	public boolean jockey;

	public GoldenChicken(EntityType<? extends GoldenChicken> entityType_1, Level world_1) {
		super(entityType_1, world_1);
		this.eggLayTime = this.random.nextInt(6000) + 6000;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, BREEDING_INGREDIENT, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose entityPose_1, EntityDimensions entityDimensions_1) {
		return this.isBaby() ? entityDimensions_1.height * 0.85F : entityDimensions_1.height * 0.92F;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.field_6736 = this.field_6741;
		this.field_6738 = this.field_6743;
		this.field_6743 = (float) (this.field_6743 + (this.onGround() ? -1 : 4) * 0.3D);
		this.field_6743 = Mth.clamp(this.field_6743, 0.0F, 1.0F);
		if (!this.onGround() && this.field_6737 < 1.0F) {
			this.field_6737 = 1.0F;
		}

		this.field_6737 = (float) (this.field_6737 * 0.9D);
		Vec3 vec3d_1 = this.getDeltaMovement();
		if (!this.onGround() && vec3d_1.y < 0.0D) {
			this.setDeltaMovement(vec3d_1.multiply(1.0D, 0.6D, 1.0D));
		}

		this.field_6741 += this.field_6737 * 2.0F;
		if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey()
				&& --this.eggTime <= 0) {
			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F,
					(this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(Items.GOLD_NUGGET);
			this.eggTime = this.random.nextInt(6000) + 6000;
		}

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.CHICKEN_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.CHICKEN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CHICKEN_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Override
	public GoldenChicken getBreedOffspring(ServerLevel world, AgeableMob passiveEntity_1) {
		return MobZEntities.GOLDEN_CHICKEN.get().create(world);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_INGREDIENT.test(stack);
	}

	@Override
	public int getExperienceReward() {
		return this.isChickenJockey() ? 10 : super.getExperienceReward();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag_1) {
		super.addAdditionalSaveData(compoundTag_1);
		compoundTag_1.putBoolean("IsChickenJockey", this.isChickenJockey);
		compoundTag_1.putInt("EggLayTime", this.eggTime);
	}

	@Override
	public boolean removeWhenFarAway(double double_1) {
		return true;
	}

	@Override
	public boolean isChickenJockey() {
		return false;
	}

	@Override
	public void setChickenJockey(boolean boolean_1) {
		this.isChickenJockey = boolean_1;
	}

	static {
		BREEDING_INGREDIENT = Ingredient.of(Items.GOLD_NUGGET);
	}
}
