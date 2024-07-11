package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class Pigman extends Piglin {

    public Pigman(EntityType<? extends Piglin> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.pigman.attack * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D).add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.pigman.attack * MobZ.configs.damage_multiplier);
    }

    @Override
    protected boolean canAddToInventory(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public boolean isImmuneToZombification() {
        return false;
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYPIGEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTPIGEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHPIGEVENT.get();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.pigman.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}
