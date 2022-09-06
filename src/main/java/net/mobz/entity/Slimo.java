package net.mobz.entity;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class Slimo extends Slime {

    public Slimo(EntityType<? extends Slime> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 1;
    }

    public static AttributeSupplier.Builder createSlimoAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1D);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader view) {
        BlockPos blockunderentity = this.blockPosition().below();
        BlockPos posentity = this.blockPosition();
        return view.isUnobstructed(this) && this.level.isDay() && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SLIMO.get())
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
    protected ParticleOptions getParticleType() {
        return ParticleTypes.FALLING_HONEY;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.getType().getDimensions();
    }
}
