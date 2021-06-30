package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class FastEntity extends ZombieEntity {

    public FastEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);

    }

    public static AttributeModifierMap.MutableAttribute createFastEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.SpeedyZombieLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.SpeedyZombieAttack * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.FAST)
                && Configs.instance.SpeedyZombieSpawn;

    }

    @Override
    public boolean canBreakDoors() {
        return false;
    }

    @Override
    protected SoundEvent getStepSound() {
        return MobZSounds.STEPSPEEDEVENT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYSPEEDEVENT;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

}
