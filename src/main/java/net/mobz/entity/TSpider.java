package net.mobz.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class TSpider extends SpiderEntity {
    public TSpider(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createTSpiderAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobZ.configs.TinySpiderLife)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.ATTACK_DAMAGE, MobZ.configs.TinySpiderAttack);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader view) {
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.isUnobstructed(this) && !level.containsAnyLiquid(this.getBoundingBox())
                && this.level.getMaxLocalRawBrightness(posentity) <= 7
                && this.level.getBlockState(posentity).getBlock().isPossibleToRespawnInThis()
                && this.level.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, MobZEntities.TSPIDER)
                && MobZ.configs.TinySpiderSpawn;

    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.10F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.NOTHINGEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MobZSounds.NOTHINGEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.NOTHINGEVENT;
    }
}
