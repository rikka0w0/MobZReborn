package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;


public class Brownbear extends PolarBear {
    public Brownbear(EntityType<? extends PolarBear> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, MobZ.configs.BrownBear.life)
                .add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, MobZ.configs.BrownBear.attack);
    }
}