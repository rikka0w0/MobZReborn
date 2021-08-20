package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.level.Level;

public class TestEntity extends Vex {
    public TestEntity(EntityType<? extends Vex> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createTestEntityAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }
}