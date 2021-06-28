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
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;

public class BossEntity extends ZombieEntity {

    public BossEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.xpReward = 60;
    }

    public static AttributeModifierMap.MutableAttribute createBossEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.BossZombieLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.21D)
                .add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.BossZombieAttack * Configs.instance.DamageMultiplicatorMob)
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
        /*this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(SwordItems.BossSword));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));
        this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(ArmorItems.boss_chestplate));
        this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(ArmorItems.boss_boots));
        this.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(ArmorItems.boss_leggings));
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(ArmorItems.boss_helmet));*/
        
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(net.minecraft.item.Items.DIAMOND_SWORD));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(MobZItems.SHIELD));
        this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(net.minecraft.item.Items.GOLDEN_CHESTPLATE));
        this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(net.minecraft.item.Items.GOLDEN_BOOTS));
        this.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(net.minecraft.item.Items.GOLDEN_LEGGINGS));
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(net.minecraft.item.Items.GOLDEN_HELMET));
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
                && Configs.instance.BossZombieSpawn;
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
