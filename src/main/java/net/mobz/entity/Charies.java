package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class Charies extends Vindicator {
	private int cooldown = 0;
	private final int requiredCooldown = 200;

	public Charies(EntityType<? extends Vindicator> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 50;

	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.KingCharlesLife * MobZ.configs.LifeMultiplicatorMob)
				.add(Attributes.MOVEMENT_SPEED, 0.32D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.KingCharlesAttack * MobZ.configs.DamageMultiplicatorMob)
				.add(Attributes.FOLLOW_RANGE, 18.0D);
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		if (!state.liquid()) {
			this.playSound(MobZSounds.LEATHERWALKEVENT.get(), 0.15F, 1F);
		}
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword.get()));
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
		return;
	}

	@Override
	public boolean requiresCustomPersistence() {
		return true;
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
	protected void customServerAiStep() {
		MobEffectInstance slow = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, false, false);

		if (getTarget() != null && !this.level().isClientSide && distanceToSqr(getTarget()) < 4096D
				&& hasLineOfSight(getTarget())) {

			cooldown++;
			if (cooldown >= requiredCooldown) {
				cooldown = 0;
				attack(getTarget(), 1);
			}
			if (cooldown >= (requiredCooldown - 20)) {
				getTarget().addEffect(slow);
			}
		} else {
			cooldown = 0;
		}
	}

	public void attack(LivingEntity target, float f) {
		BlockPos blockPos = Charies.this.blockPosition().offset(-2 + Charies.this.random.nextInt(5), 1,
				-2 + Charies.this.random.nextInt(5));
		IslandVex vexEntity = MobZEntities.ISLANDVEX.get().create(this.level());
		vexEntity.moveTo(blockPos, 0.0F, 0.0F);
		vexEntity.finalizeSpawn((ServerLevelAccessor) this.level(),
				Charies.this.level().getCurrentDifficultyAt(blockPos), MobSpawnType.MOB_SUMMONED, null,
				(CompoundTag) null);
		Charies.this.level().addFreshEntity(vexEntity);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && this.level().canSeeSky(this.blockPosition());
	}
}
