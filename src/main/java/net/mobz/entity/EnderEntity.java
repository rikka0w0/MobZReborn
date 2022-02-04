package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class EnderEntity extends EndermanEntity {

    public EnderEntity(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createEnderEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,
                        MobZ.configs.EndermanLife * MobZ.configs.LifeMultiplicatorMob)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE,
                        MobZ.configs.EndermanAttack * MobZ.configs.DamageMultiplicatorMob)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.ENDER)
                && MobZ.configs.EndermanSpawn;

    }
}
