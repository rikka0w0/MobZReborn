package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class TankEntity extends Zombie {

    public TankEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 10;
    }

    public static AttributeSupplier.Builder createTankEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.TankLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.TankAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.2D).add(Attributes.ARMOR, 3D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.TANK.get())
                && MobZ.configs.TankSpawn;

    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false);
        if (target instanceof LivingEntity && !level.isClientSide) {
            bob.addEffect(weakness);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.AMBIENTTANKEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTTANKEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHTANKEVENT.get();
    }

    protected SoundEvent getStepSound() {
        return MobZSounds.STEPTANKEVENT.get();
    }

    @Override
    public boolean isBaby() {
        return false;
    }

}
