package net.mobz.entity;

import java.util.EnumSet;
import java.util.function.Predicate;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZSounds;

public class Withender extends WitherBoss {
    private static final Predicate<LivingEntity> CAN_ATTACK_PREDICATE;

    public Withender(EntityType<? extends WitherBoss> entityType_1, Level world_1) {
        super(entityType_1, world_1);
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createWithenderAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.WithenderLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.6D).add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 4.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Withender.DescendAtHalfHealthGoal());
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(EnderMan.class));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(EnderEntity.class));
        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, CAN_ATTACK_PREDICATE));
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.WITHIDLEEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.WITHHURTEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.WITHDEATHEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader viewableWorld_1) {
        return viewableWorld_1.isUnobstructed(this)
                && Configs.instance.WithenderSpawn;

    }

    class DescendAtHalfHealthGoal extends Goal {
        public DescendAtHalfHealthGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }
    }

    static {

        CAN_ATTACK_PREDICATE = (livingEntity) -> {
            return livingEntity.getMobType() != MobType.UNDEAD && livingEntity.attackable();
        };

    }

}
