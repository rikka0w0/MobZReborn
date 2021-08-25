package net.mobz.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.mobz.MathUtils;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class ToadEntity extends Animal {
	private static final UUID JUMP_SPEED_BOOST = UUID.fromString("0fa7caca-4f09-11eb-ae93-0242ac130002");
	private static final AttributeModifier JUMP_SPEED_BOOST_MOD = new AttributeModifier(JUMP_SPEED_BOOST, "Jump Speed Boost", 0.6F, AttributeModifier.Operation.ADDITION);

	private static final EntityDataAccessor<Integer> TONGUE_ENTITY = SynchedEntityData.defineId(ToadEntity.class, EntityDataSerializers.INT);

	private boolean onGroundPrev;
	private int ticksUntilJump;
	public float tongueDistance;
	public float targetTongueDistance;
	public float mouthDistance = 0;
	public int eatCooldown = 0;
	public boolean hasBaby;

	public ToadEntity(EntityType<? extends ToadEntity> entityType, Level world) {
		super(entityType, world);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(TONGUE_ENTITY, -1);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new LookAtTongueTarget(this));
		this.goalSelector.addGoal(0, new MakeTadpoleGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
		this.goalSelector.addGoal(3, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(getToadFoodTag()), false));
		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
		this.goalSelector.addGoal(12, new RandomLookAroundGoal(this));
	}

	public void setTongueEntity(LivingEntity e) {
		entityData.set(TONGUE_ENTITY, e.getId());
		if(!level.isClientSide()) {
			this.playSound(MobZSounds.TOAD_MOUTH, 1F, 1F + ((float) random.nextGaussian() / 5F));
		}
	}

	public boolean hasTongueEntity()
	{
		return entityData.get(TONGUE_ENTITY) != -1;
	}

	public int getTongueEntityID()
	{
		return entityData.get(TONGUE_ENTITY);
	}

	public void clearTongueEntity() {
		entityData.set(TONGUE_ENTITY, -1);
	}

	public float getWalkTargetValue(BlockPos pos, LevelReader world)
	{
		if(world.getBlockState(pos).is(Blocks.LILY_PAD)) return 100;

		return super.getWalkTargetValue(pos, world);
	}

	private void dragVictim(LivingEntity victim) {
		if (this.isDeadOrDying() || this.isRemoved() || !this.hasLineOfSight(victim)) {
			clearTongueEntity();
			return;
		}

		if (this.isTongueReady()) {
			double xx = MathUtils.approachValue(victim.position().x, getX(), 0.2D);
			double yy = MathUtils.approachValue(victim.position().y, getY() + 0.2F, 0.1D);
			double zz = MathUtils.approachValue(victim.position().z, getZ(), 0.2D);
			victim.absMoveTo(xx, yy, zz);
			victim.setDeltaMovement(0, 0, 0);
		}
	}

	private final TargetingConditions predicate = TargetingConditions.forNonCombat().selector((e)->e.distanceTo(e) < 10);

	@Override
	public void tick()
	{
		super.tick();

		if(hasBaby && getAge() == 0) hasBaby = false;

		if(hasTongueEntity())
		{
			Entity e = level.getEntity(getTongueEntityID());
			if(e != null && !e.isPassenger())
			{
				getLookControl().setLookAt(e.getX(), (e.getBoundingBox().minY + 0.5F), e.getZ(), 100, 100);
				yBodyRot = getTargetYaw();
				yHeadRot = getTargetYaw();
				this.setXRot(getTargetPitch());

				float speed = 10;
				targetTongueDistance = (this.distanceTo(e) * 16) - ((float) (e.getBoundingBox().maxX - e.getBoundingBox().minX) * 16F);
				if(tongueDistance > targetTongueDistance) speed *= 2;

				tongueDistance = MathUtils.approachValue(tongueDistance, targetTongueDistance, speed);

				// Tongue touches / reaches the target
				if (Math.abs(tongueDistance - targetTongueDistance) < 1e-3) {
					if (tongueDistance < 6F) {
						attackVictim((LivingEntity) e);
					} else {
						dragVictim((LivingEntity) e);
					}
				}
			} else {
				targetTongueDistance = 0;
				tongueDistance = MathUtils.approachValue(tongueDistance, 0, 20);
			}
		} else {
			targetTongueDistance = 0;
			tongueDistance = MathUtils.approachValue(tongueDistance, 0, 20);
		}
	}

	public boolean isTongueReady()
	{
		float yaw = Math.abs(((yBodyRot + 1) % 360) - getTargetYaw());
		boolean dis = Math.abs(tongueDistance - targetTongueDistance) < 5;
		return dis && (yaw < 4F || yaw >= 360);
	}

	public float getTargetYaw()
	{
		double xx = lookControl.getWantedX() - getX();
		double zz = lookControl.getWantedZ() - getZ();
		return (float) (Mth.atan2(zz, xx) * 57.2957763671875D) - 90.0F;
	}

	public float getTargetPitch()
	{
		double xx = lookControl.getWantedX() - getX();
		double yy = lookControl.getWantedY() - getEyeY();
		double zz = lookControl.getWantedZ() - getZ();
		double sqrt = Math.sqrt(xx * xx + zz * zz);
		return (float) (-(Mth.atan2(yy, sqrt) * 57.2957763671875D));
	}

	public boolean canUseTongue()
	{
		return !this.isPassenger();
	}

	public boolean isFood(ItemStack stack)
	{
		return stack.is(getToadFoodTag());
	}

	@Override
	public void customServerAiStep()
	{

		eatCooldown--;
		if(eatCooldown <= 0 && !hasTongueEntity())
		{
			List<LivingEntity> targets = level.getEntities(
					EntityTypeTest.forClass(LivingEntity.class),
					getBoundingBox().inflate(3, 3, 3),
					this::isToadTarget);
			LivingEntity closest = level.getNearestEntity(targets, predicate, this, getX(), getY(), getZ());

			if(!canUseTongue() || closest == null || closest.isPassenger() || targets.isEmpty()) {
				clearTongueEntity();
			} else {
				eatCooldown = 100;
				setTongueEntity(closest);
			}
		}else
		{
			Entity e = level.getEntity(getTongueEntityID());
			if(!canUseTongue() || e == null || !e.isAlive()) clearTongueEntity();
		}

		if(this.ticksUntilJump > 0)
		{
			--this.ticksUntilJump;
		}

		if(ticksUntilJump <= 0 && moveControl.hasWanted())
		{
			ticksUntilJump = this.random.nextInt(100 - 20) + 20;
			jumpFromGround();
			AttributeInstance entityAttributeInstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			entityAttributeInstance.removeModifier(JUMP_SPEED_BOOST);
			entityAttributeInstance.addTransientModifier(JUMP_SPEED_BOOST_MOD);

			this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}

		if(onGround && !onGroundPrev)
		{
			this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(JUMP_SPEED_BOOST_MOD);
		}

		onGroundPrev = onGround;
	}

	protected SoundEvent getJumpSound()
	{
		return MobZSounds.TOAD_JUMP;
	}

	public static AttributeSupplier.Builder createEntityAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag)
	{
		super.addAdditionalSaveData(tag);
		tag.putBoolean("HasBaby", hasBaby);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag)
	{
		super.readAdditionalSaveData(tag);
		hasBaby = tag.getBoolean("HasBaby");
	}

	public void setHasBaby(boolean hasBaby)
	{
		this.hasBaby = hasBaby;
	}

	public void spawnChildFromBreeding(ServerLevel serverWorld, Animal other)
	{

		ServerPlayer player = this.getLoveCause();
		if(player == null && other.getLoveCause() != null)
		{
			player = other.getLoveCause();
		}

		if(player != null)
		{
			player.awardStat(Stats.ANIMALS_BRED);
			CriteriaTriggers.BRED_ANIMALS.trigger(player, this, other, null);
		}

		setHasBaby(true);
		this.setAge(6000);
		other.setAge(6000);
		this.resetLove();
		other.resetLove();
		serverWorld.broadcastEntityEvent(this, (byte) 18);
		if(serverWorld.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
		{
			serverWorld.addFreshEntity(new ExperienceOrb(serverWorld, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity)
	{
		//Unused!!
		return null;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return MobZSounds.TOAD_CROAK;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return MobZSounds.TOAD_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return MobZSounds.TOAD_HURT;
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + ((float) random.nextGaussian() / 8F);
	}

	private class LookAtTongueTarget extends Goal
	{
		private final ToadEntity toad;

		public LookAtTongueTarget(ToadEntity entity)
		{
			super();
			this.toad = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return toad.hasTongueEntity();
		}

		@Override

		public boolean canContinueToUse()
		{
			return toad.hasTongueEntity();
		}
	}

	public class MakeTadpoleGoal extends MoveToBlockGoal
	{
		private final ToadEntity toad;

		public MakeTadpoleGoal(ToadEntity toad, double speed, int range)
		{
			super(toad, speed, range, 5);
			this.toad = toad;
		}

		public boolean canUse()
		{
			return this.toad.hasBaby && super.canUse();
		}

		public boolean canContinueToUse()
		{
			return super.canContinueToUse() && this.toad.hasBaby;
		}

		@Override
		public void tick()
		{
			super.tick();

			BlockPos blockPos = this.mob.blockPosition();
			if(getMoveToTarget().closerThan(toad.position(), 2F))
			{
				getNavigation().moveTo(blockPos.getX() + 0.5F, blockPos.getY(), blockPos.getZ() + 0.5F, 1F);
			}

			if(toad.isInWater())
			{
				ServerLevel world = (ServerLevel) this.toad.level;
				this.toad.setHasBaby(false);

				TadpoleEntity tadpole = MobZEntities.TADPOLE.create(world);
				if(tadpole != null)
				{
					world.playSound(null, blockPos, MobZSounds.TOAD_HAVE_BABY, SoundSource.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
					tadpole.setBaby(true);
					tadpole.moveTo(toad.getX(), toad.getY(), toad.getZ(), 0.0F, 0.0F);
					world.addFreshEntityWithPassengers(tadpole);
				}
			}

		}

		public double acceptedDistance()
		{
			return 0.0D;
		}

		@Override
		protected boolean isValidTarget(LevelReader world, BlockPos pos)
		{
			FluidState state = world.getFluidState(pos);
			return state.isSource() && state.is(FluidTags.WATER) && world.getBlockState(pos.above()).isAir();
		}
	}

	public boolean isToadTarget(Entity entity) {
		return canToadTarget(entity) && this.hasLineOfSight(entity);
	}

	public static Tag<Item> getToadFoodTag() {
		return ItemTags.getAllTags().getTagOrEmpty(MobZ.TOAD_FOOD);
	}

	public static boolean canToadTarget(Entity entity) {
		return EntityTypeTags.getAllTags().getTagOrEmpty(MobZ.TOAD_TARGET).contains(entity.getType());
	}

	public void attackVictim(LivingEntity victim) {
		if (victim instanceof Player) {
			victim.hurt(DamageSource.indirectMobAttack(this, null), 1F);
		} else {
			victim.discard();
		}
	}
}
