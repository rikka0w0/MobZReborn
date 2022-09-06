package net.mobz.entity;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class SCreeperEntity extends Creeper {

  public SCreeperEntity(EntityType<? extends Creeper> entityType, Level world) {
    super(entityType, world);
  }

  public static AttributeSupplier.Builder createSCreeperEntityAttributes() {
    return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            MobZ.configs.SoulCreeperLife * MobZ.configs.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 32.0D);
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource_1) {
    return MobZSounds.SAYCREEPEVENT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.DEATHCREEPEVENT.get();
  }

  @Override
  public boolean checkSpawnObstruction(LevelReader view) {
    BlockPos blockunderentity = this.blockPosition().below();
    BlockPos posentity = this.blockPosition();
    return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
        && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
        && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SCREEPER.get())
        && (this.level.getBlockState(blockunderentity).is(Blocks.SOUL_SAND)
            || this.level.getBlockState(blockunderentity).is(Blocks.SOUL_SOIL))
        && MobZ.configs.SoulCreeperSpawn;
  }
}
