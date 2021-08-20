package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class PigmanEntity extends Piglin {

    public PigmanEntity(EntityType<? extends Piglin> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createPigmanEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.PigmanLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D).add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.PigmanAttack * Configs.instance.DamageMultiplicatorMob);
    }

    @Override
    protected boolean canAddToInventory(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public boolean isImmuneToZombification() {
        return false;
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SAYPIGEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTPIGEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHPIGEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.PIG)
                && Configs.instance.PigmanSpawn;

    }

}
