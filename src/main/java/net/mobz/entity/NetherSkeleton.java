package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class NetherSkeleton extends Skeleton {

    public NetherSkeleton(EntityType<? extends Skeleton> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.NetherSkeleton.life * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.26D).add(Attributes.FOLLOW_RANGE, 30.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SKELISAYEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.SKELIHURTEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.SKELIDEATHEVENT.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.liquid()) {
            this.playSound(MobZSounds.SKELISTEPEVENT.get(), 0.15F, 1F);
        }
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(LavaGolem.class));
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.NetherSkeleton.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}