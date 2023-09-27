package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class BossEntity extends Zombie {

    public BossEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 60;
    }

    public static AttributeSupplier.Builder createBossEntityAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.BossZombieLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.21D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.BossZombieAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 36.0D).add(Attributes.ARMOR, -4D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
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
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.BossSword.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(MobZItems.SHIELD.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(MobZArmors.boss_chestplate.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(MobZArmors.boss_boots.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(MobZArmors.boss_leggings.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MobZArmors.boss_helmet.get()));
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource_1, int int_1, boolean boolean_1) {
        return;
    }

    @Override
    public boolean removeWhenFarAway(double double_1) {
        return false;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.BossZombieSpawn
				&& this.level().isNight()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        MobEffectInstance fatigue = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120, 0, false, false);
        if (target instanceof LivingEntity) {
            bob.addEffect(fatigue);
        }
    }
}
