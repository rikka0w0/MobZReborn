package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class skeli4 extends Skeleton {

    public skeli4(EntityType<? extends Skeleton> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createskeli4Attributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.LostSkeletonLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.255D).add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.LostSkeletonAttack * Configs.instance.DamageMultiplicatorMob);
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.VSword));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.SKELASAYEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.SKELAHURTEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.SKELADEATHEVENT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            this.playSound(MobZSounds.SKELASTEPEVENT, 0.15F, 1F);
        }
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SKELI4)
                && Configs.instance.LostSkeletonSpawn;

    }
}