package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.world.World;


public class Brownbear extends PolarBearEntity {
    public Brownbear(EntityType<? extends PolarBearEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createBrownbearAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D);
    }
}