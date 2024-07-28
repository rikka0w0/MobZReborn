package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class PillagerBoss extends Pillager {
    private int cooldown = 0;

    public PillagerBoss(EntityType<PillagerBoss> entityType_1, Level world_1) {
        super(entityType_1, world_1);
        this.xpReward = 50;
        ItemStack itemStack = new ItemStack(MobZWeapons.ARMORED_SWORD.get());
        ItemStack itemStack2 = new ItemStack(MobZItems.PILLAGER_STAFF.get());
        this.setItemSlot(EquipmentSlot.MAINHAND, itemStack2);
        this.setItemSlot(EquipmentSlot.OFFHAND, itemStack);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.pillager_boss.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.pillager_boss.attack * MobZ.configs.damage_multiplier)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 30.0F, 1.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
    	// Must not have the cross bow
    }

    @Override
    protected void customServerAiStep() {
        MobEffectInstance slow = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0, false, false);
        int requiredCooldown = this.level().getDifficulty() == Difficulty.HARD ?
        		MobZ.configs.pillager_boss.wither_attack_cooldown_hard : MobZ.configs.pillager_boss.wither_attack_cooldown;

        if (getTarget() != null && !this.level().isClientSide && distanceToSqr(getTarget()) < 4096D && hasLineOfSight(getTarget())) {

            cooldown++;
            if (cooldown >= requiredCooldown) {
                cooldown = 0;
                performRangedAttack(getTarget(), 1);
            }
            if (cooldown >= (requiredCooldown - 20)) {
                getTarget().addEffect(slow);
            }
        } else {
            cooldown = 0;
        }
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float f) {
        Vec3 vec3d_1 = this.getViewVector(1.0F);
        double double_3 = target.getX() - (this.getX() + vec3d_1.x * 2.0D);
        double double_4 = this.getY() - target.getBoundingBox().getYsize() + target.getBbHeight() / 2.0F
                - (0.5D + this.getY() + this.getBbHeight() / 2.0F) + 1D;
        double double_5 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D);
        double double_9 = this.getY() - target.getBoundingBox().getYsize() + target.getBbHeight() / 2.0F
                - (0.5D + this.getY() + this.getBbHeight() / 2.0F) + 0.7D;
        double double_10 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D) + 0.7D;
        double double_11 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D) - 0.7D;
        WitherSkull skull1 = new WitherSkull(this.level(), this, double_3, double_4, double_5);
        WitherSkull skull2 = new WitherSkull(this.level(), this, double_3, double_9, double_10);
        WitherSkull skull3 = new WitherSkull(this.level(), this, double_3, double_9, double_11);
        double double_6 = this.getX() + vec3d_1.x * 2.0D;
        double double_7 = this.getY() + this.getBbHeight();
        double double_8 = this.getZ() + vec3d_1.z * 2.0D;
        skull1.setPosRaw(double_6, double_7, double_8);
        skull2.setPosRaw(double_6, double_7, double_8);
        skull3.setPosRaw(double_6, double_7, double_8);
        this.level().addFreshEntity(skull1);
        this.level().addFreshEntity(skull2);
        this.level().addFreshEntity(skull3);
    }

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.pillager_boss.can_join_raid.check(this);
	}

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }
}
