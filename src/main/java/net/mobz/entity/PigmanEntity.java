package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;

public class PigmanEntity extends PiglinEntity {

    public PigmanEntity(EntityType<? extends PiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createPigmanEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        Configs.instance.PigmanLife * Configs.instance.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5D).add(Attributes.ATTACK_DAMAGE,
                        Configs.instance.PigmanAttack * Configs.instance.DamageMultiplicatorMob);
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
        return MobZSounds.SAYPIGEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.HURTPIGEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.DEATHPIGEVENT;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.PIG)
                && Configs.instance.PigmanSpawn;

    }

}
