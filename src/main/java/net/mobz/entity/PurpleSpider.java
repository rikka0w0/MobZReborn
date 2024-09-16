package net.mobz.entity;

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

public class PurpleSpider extends Spider {
    public PurpleSpider(EntityType<? extends Spider> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createMobzAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.purple_spider.life * MobZ.configs.life_multiplier)
                .add(Attributes.MOVEMENT_SPEED, 0.31D).add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.purple_spider.attack * MobZ.configs.damage_multiplier);
    }

    @Override
	public boolean doHurtTarget(Entity victim) {
		boolean flag = super.doHurtTarget(victim);

        if (flag && victim instanceof LivingEntity livingEntity &&
        		this.random.nextInt(8) == 3 && !this.level().isClientSide) {
        	livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 0, false, false));
        }

        return flag;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.purple_spider.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}
