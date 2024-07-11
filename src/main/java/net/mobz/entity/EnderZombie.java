package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class EnderZombie extends Zombie {
    public EnderZombie(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.ender_zombie.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.ender_zombie.attack * MobZ.configs.damage_multiplier)
                .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.ARMOR, 2D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYENDEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.ENDHURTEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHENDEVENT.get();
    }

    @Override
    protected SoundEvent getStepSound() {
        return MobZSounds.STEPTANKEVENT.get();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.ender_zombie.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

    }

    @Override
    public boolean isBaby() {
        return false;
    }
}
