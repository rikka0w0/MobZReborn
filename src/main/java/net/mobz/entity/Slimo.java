package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class Slimo extends SlimeEntity {

    public Slimo(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createSlimoAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1D);
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && this.level.isDay() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SLIMO)
                && MobZ.configs.GrassSlimeSpawn;

    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isTiny() {
        return true;
    }

    @Override
    protected IParticleData getParticleType() {
        return ParticleTypes.FALLING_HONEY;
    }

}
