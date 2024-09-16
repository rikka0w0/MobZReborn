package net.mobz.entity;

import java.util.Random;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;

public class BlueSpider extends Spider {
    public BlueSpider(EntityType<? extends Spider> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.blue_spider.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.31D).add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.blue_spider.attack * MobZ.configs.damage_multiplier);
    }

    @Override
	public boolean doHurtTarget(Entity victim) {
		boolean flag = super.doHurtTarget(victim);

        Random random = new Random();
        int randomNumber = (random.nextInt() + 7) % 5;
        if (randomNumber < 0) {
            randomNumber = randomNumber * (-1);
        }

        if (flag && victim instanceof LivingEntity bob && randomNumber == 3 && !this.level().isClientSide) {
            bob.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 0, false, false));
        }

        return flag;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.blue_spider.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}
