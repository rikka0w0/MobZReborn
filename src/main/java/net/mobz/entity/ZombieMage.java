package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class ZombieMage extends EvokerLike {
	public ZombieMage(EntityType<? extends ZombieMage> entityType, Level world) {
		super(entityType, world, SmallZombie.class);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.zombie_mage.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.45D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.zombie_mage.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.zombie_mage.spawn
				&& !this.isPatrolLeader()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.zombie_mage.can_join_raid.check(this);
	}

	@Override
	protected Entity getSummonOwnerOf(Entity entity) {
		if (!(entity instanceof SmallZombie smallZombie))
			return null;

		return smallZombie.owner;
	}

	@Override
	protected void summon() {
		ServerLevel serverWorld = (ServerLevel) this.level();

		for (int i = 0; i < 3; ++i) {
			BlockPos blockPos = this.blockPosition().offset(-2 + this.random.nextInt(5), 1, -2 + this.random.nextInt(5));
			SmallZombie smallZombie = MobZEntities.SMALL_ZOMBIE.get().create(this.level(), EntitySpawnReason.MOB_SUMMONED);
			if (smallZombie != null) {
				smallZombie.snapTo(blockPos, 0.0F, 0.0F);
				smallZombie.finalizeSpawn(serverWorld, this.level().getCurrentDifficultyAt(blockPos), EntitySpawnReason.MOB_SUMMONED, null);
				smallZombie.owner = this;
				this.level().addFreshEntity(smallZombie);
				serverWorld.gameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Context.of(this));
			}
		}
	}
}
