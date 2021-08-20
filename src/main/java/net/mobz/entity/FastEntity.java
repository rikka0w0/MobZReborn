package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class FastEntity extends Zombie {

    public FastEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);

    }

    public static AttributeSupplier.Builder createFastEntityAttributes() {
        return Monster.createMonsterAttributes()
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
    public boolean checkSpawnObstruction(LevelReader view) {
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
