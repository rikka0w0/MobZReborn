package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;

public class Ender extends EnderMan {

    public Ender(EntityType<? extends EnderMan> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.ender.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.ender.attack * MobZ.configs.damage_multiplier)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.ender.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

    }
}
