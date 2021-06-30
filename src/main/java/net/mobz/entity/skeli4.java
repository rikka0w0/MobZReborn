package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class skeli4 extends SkeletonEntity {

    public skeli4(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createskeli4Attributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.LostSkeletonLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.255D).add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.LostSkeletonAttack * Configs.instance.DamageMultiplicatorMob);
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(difficulty);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.VSword));
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
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SKELI4)
                && Configs.instance.LostSkeletonSpawn;

    }
}