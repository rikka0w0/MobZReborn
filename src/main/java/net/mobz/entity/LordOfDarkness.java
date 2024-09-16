package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class LordOfDarkness extends Vindicator {

    public LordOfDarkness(EntityType<? extends Vindicator> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 20;

    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.lord_of_darkness.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.lord_of_darkness.attack * MobZ.configs.damage_multiplier)
                .add(Attributes.FOLLOW_RANGE, 26.0D);
    }

	@Override
	public boolean doHurtTarget(Entity victim) {
		boolean flag = super.doHurtTarget(victim);

		if (flag && victim instanceof LivingEntity livingEntity && !this.level().isClientSide) {
			livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, false));
			livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 0, false, false));
		}

		return flag;
	}

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
        if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.WITHER_SWORD.get()));
            this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(MobZWeapons.WITHER_SWORD.get()));
        }
    }

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverWorld, DamageSource damageSource, boolean flag) {
		return;
	}

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.DARKIDLEEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.DARKHITEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DARKDEATHEVENT.get();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.lord_of_darkness.spawn
        		&& !this.isPatrolLeader()
        		&& MobSpawnHelper.checkSpawnObstruction(this, view);
    }

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.lord_of_darkness.can_join_raid.check(this);
	}
}
