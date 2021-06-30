package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.world.World;

public class TestEntity extends VexEntity {
    public TestEntity(EntityType<? extends VexEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createTestEntityAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }
}