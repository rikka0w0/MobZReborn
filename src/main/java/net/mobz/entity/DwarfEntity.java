package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VindicatorEntity;
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

public class DwarfEntity extends VindicatorEntity {

    public DwarfEntity(EntityType<? extends VindicatorEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 20;
    }

    public static AttributeModifierMap.MutableAttribute createDwarfEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.DwarfLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.DwarfAttack * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            this.playSound(MobZSounds.LESSHEAVYARMORWALKEVENT, 0.15F, 1F);
        }
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        EffectInstance slow = new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 70, 0, false, false);
        if (target instanceof LivingEntity && !level.isClientSide) {
            bob.addEffect(slow);
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
        super.populateDefaultEquipmentSlots(localDifficulty_1);
        if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.Axe));
            this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(MobZItems.SHIELD));
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
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
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());

        if (Configs.instance.DwarfSpawn_UndergroundOnly &&
				posentity.getY() >= view.getSeaLevel() - 10) {
        	return false;
        }

        return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis() && this.level
                        .getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.DWARFENTITY)
                && Configs.instance.DwarfSpawn;

    }
}
