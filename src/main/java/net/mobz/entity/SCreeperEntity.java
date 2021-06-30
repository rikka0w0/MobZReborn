package net.mobz.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class SCreeperEntity extends CreeperEntity {

  public SCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
    super(entityType, world);
  }

  public static AttributeModifierMap.MutableAttribute createSCreeperEntityAttributes() {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.SoulCreeperLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 32.0D);
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource_1) {
    return MobZSounds.SAYCREEPEVENT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return MobZSounds.DEATHCREEPEVENT;
  }

  @Override
  public boolean checkSpawnObstruction(IWorldReader view) {
    BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
    BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
    return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
        && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
        && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.SCREEPER)
        && (this.level.getBlockState(blockunderentity).is(Blocks.SOUL_SAND)
            || this.level.getBlockState(blockunderentity).is(Blocks.SOUL_SOIL))
        && Configs.instance.SoulCreeperSpawn;
  }
}
