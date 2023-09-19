package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class FastEntity extends Zombie {

    public FastEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);

    }

    public static AttributeSupplier.Builder createFastEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.SpeedyZombieLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.SpeedyZombieAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.SpeedyZombieSpawn && MobSpawnHelper.checkSpawnObstruction(this, view);

    }

    @Override
    public boolean canBreakDoors() {
        return false;
    }

    @Override
    protected SoundEvent getStepSound() {
        return MobZSounds.STEPSPEEDEVENT.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYSPEEDEVENT.get();
    }

    @Override
    public boolean isBaby() {
        return false;
    }

}
