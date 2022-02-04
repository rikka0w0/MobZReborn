package net.mobz.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class TankEntity extends ZombieEntity {

    public TankEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 10;
    }

    public static AttributeModifierMap.MutableAttribute createTankEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
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
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.TANK)
                && MobZ.configs.TankSpawn;

    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        EffectInstance weakness = new EffectInstance(Effects.WEAKNESS, 100, 0, false, false);
        if (target instanceof LivingEntity && !level.isClientSide) {
            bob.addEffect(weakness);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.AMBIENTTANKEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTTANKEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHTANKEVENT;
    }

    protected SoundEvent getStepSound() {
        return MobZSounds.STEPTANKEVENT;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

}
