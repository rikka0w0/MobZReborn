package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class PillagerBoss extends PillagerEntity {
    private int cooldown = 0;
    private final int requiredCooldown = 120;

    public PillagerBoss(EntityType<PillagerBoss> entityType_1, World world_1) {
        super(entityType_1, world_1);
        this.xpReward = 50;
        ItemStack itemStack = new ItemStack(MobZWeapons.ArmoredSword);
        ItemStack itemStack2 = new ItemStack(MobZItems.PILLAGERSTAFF);
        this.setItemSlot(EquipmentSlotType.MAINHAND, itemStack2);
        this.setItemSlot(EquipmentSlotType.OFFHAND, itemStack);
    }

    public static AttributeModifierMap.MutableAttribute createPillagerBossAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.PillagerBossLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.PillagerBossAttack * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 30.0F, 1.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {

    }

    @Override
    protected void customServerAiStep() {
        EffectInstance slow = new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 0, false, false);

        if (getTarget() != null && !level.isClientSide && distanceToSqr(getTarget()) < 4096D && canSee(getTarget())) {

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
        Vector3d vec3d_1 = this.getViewVector(1.0F);
        double double_3 = target.getX() - (this.getX() + vec3d_1.x * 2.0D);
        double double_4 = target.getBoundingBox().getYsize() + (double) (target.getBbHeight() / 2.0F)
                - (0.5D + this.getY() + (double) (this.getBbHeight() / 2.0F)) + 1D;
        double double_5 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D);
        double double_9 = target.getBoundingBox().getYsize() + (double) (target.getBbHeight() / 2.0F)
                - (0.5D + this.getY() + (double) (this.getBbHeight() / 2.0F)) + 0.7D;
        double double_10 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D) + 0.7D;
        double double_11 = target.getZ() - (this.getZ() + vec3d_1.z * 2.0D) - 0.7D;
        WitherSkullEntity skull1 = new WitherSkullEntity(level, this, double_3, double_4, double_5);
        WitherSkullEntity skull2 = new WitherSkullEntity(level, this, double_3, double_9, double_10);
        WitherSkullEntity skull3 = new WitherSkullEntity(level, this, double_3, double_9, double_11);
        double double_6 = this.getX() + vec3d_1.x * 2.0D;
        double double_7 = this.getY() + (double) this.getBbHeight();
        double double_8 = this.getZ() + vec3d_1.z * 2.0D;
        skull1.absMoveTo(double_6, double_7, double_8);
        skull2.absMoveTo(double_6, double_7, double_8);
        skull3.absMoveTo(double_6, double_7, double_8);
        level.addFreshEntity(skull1);
        level.addFreshEntity(skull2);
        level.addFreshEntity(skull3);

    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

}
