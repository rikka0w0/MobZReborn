package net.mobz.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;

public class SpoEntity extends SpiderEntity {
    public SpoEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createSpoEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.PurpleSpiderLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.31D).add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.PurpleSpiderAttack * Configs.instance.DamageMultiplicatorMob);
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        Random random = new Random();
        int randomNumber = (random.nextInt() + 7) % 8;
        if (randomNumber < 0) {
            randomNumber = randomNumber * (-1);
        }
        LivingEntity bob = (LivingEntity) target;
        EffectInstance poison = new EffectInstance(Effects.POISON, 120, 0, false, false);
        if (target instanceof LivingEntity && randomNumber == 3 && !level.isClientSide) {
            bob.addEffect(poison);
        }
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SPO)
                && Configs.instance.PurpleSpiderSpawn;

    }

}
