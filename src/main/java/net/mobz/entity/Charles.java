package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class Charles extends Vindicator {
	private int cooldown = 0;

	public Charles(EntityType<? extends Vindicator> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 50;

	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.charles.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.32D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.charles.attack * MobZ.configs.damage_multiplier)
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
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ARMORED_SWORD.get()));
		}
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
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
		int cooldownMax = Math.max(MobZ.configs.charles.vex_summon_cooldown, MobZ.configs.charles.slowdown_attack_cooldown);
		LivingEntity target = getTarget();

		if (!this.level().isClientSide && target != null && distanceToSqr(target) < 4096D && hasLineOfSight(target)) {
			this.cooldown++;
			if (this.cooldown >= MobZ.configs.charles.vex_summon_cooldown) {
				summonVexToAttack(getTarget());
			}

			if (this.cooldown >= MobZ.configs.charles.slowdown_attack_cooldown) {
				getTarget().addEffect(slow);
			}

			if (this.cooldown >= cooldownMax) {
				this.cooldown = 0;
			}
		} else {
			this.cooldown = 0;
		}
	}

	public void summonVexToAttack(LivingEntity target) {
		BlockPos blockPos = Charles.this.blockPosition().offset(-2 + Charles.this.random.nextInt(5), 1,
				-2 + Charles.this.random.nextInt(5));
		SpiritOfDeath vexEntity = MobZEntities.SPIRIT_OF_DEATH.get().create(this.level());
		vexEntity.moveTo(blockPos, 0.0F, 0.0F);
		vexEntity.finalizeSpawn((ServerLevelAccessor) this.level(),
				Charles.this.level().getCurrentDifficultyAt(blockPos), MobSpawnType.MOB_SUMMONED, null);
		Charles.this.level().addFreshEntity(vexEntity);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.charles.can_join_raid.check(this);
	}
}
