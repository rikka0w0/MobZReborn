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
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class PigmanEntity extends Piglin {

    public PigmanEntity(EntityType<? extends Piglin> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createPigmanEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.PigmanLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D).add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.PigmanAttack * MobZ.configs.DamageMultiplicatorMob);
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
        return MobZSounds.SAYPIGEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTPIGEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHPIGEVENT.get();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = this.blockPosition().below();
        BlockPos posentity = this.blockPosition();
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.PIG.get())
                && MobZ.configs.PigmanSpawn;

    }

}
