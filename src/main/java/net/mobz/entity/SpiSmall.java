package net.mobz.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.mobz.Configs;

public class SpiSmall extends CaveSpiderEntity {

    private int lifeTicks;
    private boolean alive;

    public SpiSmall(EntityType<? extends CaveSpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createSpiSmallAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5D * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 3D * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 15.0D);
    }

    public void setLifeTicks(int lifeTicks) {
        this.alive = true;
        this.lifeTicks = lifeTicks;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.alive && --this.lifeTicks <= 0) {
            this.lifeTicks = 20;
            this.hurt(DamageSource.STARVE, 1.0F);
        }

    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return true;
    }
}
