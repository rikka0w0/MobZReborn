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

public class SpoEntity extends Spider {
    public SpoEntity(EntityType<? extends Spider> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createSpoEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.PurpleSpiderLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.31D).add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.PurpleSpiderAttack * MobZ.configs.DamageMultiplicatorMob);
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        Random random = new Random();
        int randomNumber = (random.nextInt() + 7) % 8;
        if (randomNumber < 0) {
            randomNumber = randomNumber * (-1);
        }
        LivingEntity bob = (LivingEntity) target;
        MobEffectInstance poison = new MobEffectInstance(MobEffects.POISON, 120, 0, false, false);
        if (target instanceof LivingEntity && randomNumber == 3 && !this.level().isClientSide) {
            bob.addEffect(poison);
        }
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        return MobZ.configs.PurpleSpiderSpawn && MobSpawnHelper.checkSpawnObstruction(this, view);
    }
}
