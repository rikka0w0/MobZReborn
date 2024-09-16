package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Archer extends Pillager {

    public Archer(EntityType<? extends Pillager> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 20;
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.archer.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.345D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
    	float attack = (float) (MobZ.configs.archer.attack* MobZ.configs.damage_multiplier);
        this.performCrossbowAttack(this, attack);
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
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.liquid()) {
            this.playSound(MobZSounds.LEATHERWALKEVENT.get(), 0.15F, 1F);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT.get();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.archer.spawn && !this.isPatrolLeader() && MobSpawnHelper.checkSpawnObstruction(this, view);
    }

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.archer.can_join_raid.check(this);
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
		return;
	}
}