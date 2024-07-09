package net.mobz.entity;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class William extends Zombie {

	public William(EntityType<? extends Zombie> entityType, Level world) {
		super(entityType, world);
		this.xpReward = 30;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.WilliamLife * MobZ.configs.LifeMultiplicatorMob)
				.add(Attributes.MOVEMENT_SPEED, 0.32D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.WilliamAttack * MobZ.configs.DamageMultiplicatorMob)
				.add(Attributes.FOLLOW_RANGE, 18.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.9D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword.get()));
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(MobZItems.SHIELD.get()));
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
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	protected SoundEvent getStepSound() {
		return MobZSounds.ARMORWALKEVENT.get();
	}

	@Override
	public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
		LivingEntity bob = (LivingEntity) target;
		MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 140, 0, false, false);
		if (target instanceof LivingEntity && !this.level().isClientSide) {
			bob.addEffect(weakness);
		}
	}

	@Override
	public boolean isBaby() {
		return false;
	}

	@Override
	public boolean isUnderWaterConverting() {
		return false;
	}
}
