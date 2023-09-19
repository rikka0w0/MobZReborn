package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;

public class skeli2 extends Skeleton {
    public skeli2(EntityType<? extends Skeleton> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createskeli2Attributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.OvergrownSkeletonLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 30.0D);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.OvergrownSkeletonSpawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}