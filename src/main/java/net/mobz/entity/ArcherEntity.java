package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class ArcherEntity extends PillagerEntity {

    public ArcherEntity(EntityType<? extends PillagerEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 20;
    }

    public static AttributeModifierMap.MutableAttribute createArcherEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.BowmanLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.33D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.BowmanAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 34.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT;
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
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
        super.populateDefaultEquipmentSlots(localDifficulty_1);
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEFINED;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0D, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IceGolem.class, true));
        this.targetSelector.addGoal(4, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(IceGolem.class));
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && this.level.isDay() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis() && this.level
                        .getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.ARCHERENTITY)
                && MobZ.configs.BowmanSpawn;

    }

}