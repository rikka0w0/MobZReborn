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

public class Knight5Entity extends VindicatorEntity {

    public Knight5Entity(EntityType<? extends VindicatorEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 20;

    }

    public static AttributeModifierMap.MutableAttribute createKnight5EntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.LordofDarknessLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.LordofDarknessAttack * Configs.instance.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 26.0D);
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        EffectInstance nausea = new EffectInstance(Effects.CONFUSION, 100, 0, false, false);
        EffectInstance wither = new EffectInstance(Effects.WITHER, 80, 0, false, false);
        if (target instanceof LivingEntity && !level.isClientSide) {
            bob.addEffect(nausea);
            bob.addEffect(wither);
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty_1) {
        super.populateDefaultEquipmentSlots(localDifficulty_1);
        if (this.level.getDifficulty() != Difficulty.PEACEFUL) {
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.WitherSword));
            this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(MobZWeapons.WitherSword));
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.DARKIDLEEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.DARKHITEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DARKDEATHEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !this.isPatrolLeader() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity,
                        MobZEntities.KNIGHT5ENTITY)
                && Configs.instance.LordofDarknessSpawn;

    }

}
