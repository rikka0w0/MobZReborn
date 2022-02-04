package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class BigBossEntity extends ZombieEntity {

    public BigBossEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 60;
    }

    public static AttributeModifierMap.MutableAttribute createBigBossEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.BigBossLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.21D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.BigBossAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity,
                        MobZEntities.BIGBOSSENTITY)
                && MobZ.configs.BigBossSpawn;

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.AMBIENTTANKEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTTANKEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHTANKEVENT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return MobZSounds.STEPTANKEVENT;
    }

}
