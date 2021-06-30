package net.mobz.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZSounds;

public class SmallZombie extends ZombieEntity {
    private MobEntity owner;

    public SmallZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createSmallZombieAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6D * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 3D * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 15.0D)
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
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYSPEEDEVENT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return MobZSounds.STEPSPEEDEVENT;
    }

    @Override
    protected boolean shouldDropExperience() {
        return false;
    }

    public void setLifeTicks(int i) {
        i = 2000;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    public void setOwner(Mage2Entity mage2Entity) {
    }

    public void setBounds(BlockPos blockPos_1) {

    }

    public void setOwner(MobEntity owner) {
        MobEntity.class.equals(Mage2Entity.class);
    }

    public Entity getOwner() {
        return this.owner;
    }
}
