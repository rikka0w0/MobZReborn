package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Dog extends Wolf {

    public Dog(EntityType<? extends Wolf> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createDogAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.NetherWolfLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.NetherWolfAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    public boolean isFood(ItemStack itemStack_1) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.WGROWLEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MobZSounds.WHURTEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.WDEATHEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getMaxLocalRawBrightness(posentity) <= 10
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.DOG)
                && MobZ.configs.NetherWolfSpawn;

    }
}
