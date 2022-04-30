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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class Knight5Entity extends Vindicator {

    public Knight5Entity(EntityType<? extends Vindicator> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 20;

    }

    public static AttributeSupplier.Builder createKnight5EntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.LordofDarknessLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.LordofDarknessAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 26.0D);
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, false);
        MobEffectInstance wither = new MobEffectInstance(MobEffects.WITHER, 80, 0, false, false);
        if (target instanceof LivingEntity && !level.isClientSide) {
            bob.addEffect(nausea);
            bob.addEffect(wither);
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
        super.populateDefaultEquipmentSlots(localDifficulty_1);
        if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.WitherSword.get()));
            this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(MobZWeapons.WitherSword.get()));
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
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
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity,
                        MobZEntities.KNIGHT5ENTITY.get())
                && MobZ.configs.LordofDarknessSpawn;

    }

}
