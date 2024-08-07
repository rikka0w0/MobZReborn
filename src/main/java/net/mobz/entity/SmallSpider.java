package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;

public class SmallSpider extends CaveSpider {

    private int lifeTicks;
    private boolean alive;

    public SmallSpider(EntityType<? extends CaveSpider> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createSpiSmallAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5D * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 3D * MobZ.configs.damage_multiplier)
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
            this.hurt(this.damageSources().starve(), 1.0F);
        }

    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return true;
    }
}
