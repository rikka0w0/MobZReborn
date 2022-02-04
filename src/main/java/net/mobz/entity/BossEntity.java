package net.mobz.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class BossEntity extends ZombieEntity {

    public BossEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 60;
    }

    public static AttributeModifierMap.MutableAttribute createBossEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
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
    protected void populateDefaultEquipmentSlots(DifficultyInstance localDifficulty) {
        super.populateDefaultEquipmentSlots(localDifficulty);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.BossSword));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(MobZItems.SHIELD));
        this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(MobZArmors.boss_chestplate));
        this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(MobZArmors.boss_boots));
        this.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(MobZArmors.boss_leggings));
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(MobZArmors.boss_helmet));
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
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && this.level.isNight() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.BOSS)
                && MobZ.configs.BossZombieSpawn;
    }

    @Override
    public void doEnchantDamageEffects(LivingEntity attacker, Entity target) {
        LivingEntity bob = (LivingEntity) target;
        EffectInstance fatigue = new EffectInstance(Effect.byId(4), 120, 0, false, false);
        if (target instanceof LivingEntity) {
            bob.addEffect(fatigue);
        }
    }
}
