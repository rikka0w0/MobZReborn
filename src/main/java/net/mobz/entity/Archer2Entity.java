package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Archer2Entity extends PillagerEntity {

    public Archer2Entity(EntityType<? extends PillagerEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 20;
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
    }

    public static AttributeModifierMap.MutableAttribute createArcher2EntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.ArcherLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.345D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.ArcherAttack * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            this.playSound(MobZSounds.LEATHERWALKEVENT, 0.15F, 1F);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity,
                        MobZEntities.ARCHER2ENTITY)
                && Configs.instance.ArcherSpawn;

    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

}