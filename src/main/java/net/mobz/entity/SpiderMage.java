package net.mobz.entity;

import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class SpiderMage extends EvokerLike {
	public SpiderMage(EntityType<? extends SpiderMage> entityType, Level world) {
		super(entityType, world, SmallSpider.class);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.spider_mage.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.45D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.spider_mage.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public boolean canJoinRaid() {
		return super.canJoinRaid() && MobZ.configs.spider_mage.can_join_raid.check(this);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.spider_mage.spawn && !this.isPatrolLeader()
				&& MobSpawnHelper.checkSpawnObstruction(this, view);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.EVEIDLEEVENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MobZSounds.EVEDEATHEVENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return MobZSounds.EVEHURTEVENT.get();
	}

	@Override
	protected Entity getSummonOwnerOf(Entity entity) {
		if (!(entity instanceof SmallSpider smallSpider))
			return null;

		return smallSpider.owner;
	}

	@Override
	protected void summon() {
		ServerLevel serverWorld = (ServerLevel) SpiderMage.this.level();

		for (int i = 0; i < 3; ++i) {
			BlockPos blockPos = SpiderMage.this.blockPosition().offset(-2 + SpiderMage.this.random.nextInt(5), 1,
					-2 + SpiderMage.this.random.nextInt(5));
			SmallSpider smallSpider = MobZEntities.SMALL_SPIDER.get().create(SpiderMage.this.level(),
					EntitySpawnReason.MOB_SUMMONED);
			smallSpider.snapTo(blockPos, 0.0F, 0.0F);
			smallSpider.finalizeSpawn(serverWorld, SpiderMage.this.level().getCurrentDifficultyAt(blockPos),
					EntitySpawnReason.MOB_SUMMONED, null);
			smallSpider.owner = SpiderMage.this;
			smallSpider.setTarget(this.getTarget());
			smallSpider.setLimitedLife(20 * (20 + SpiderMage.this.random.nextInt(60)));
			SpiderMage.this.level().addFreshEntity(smallSpider);
			serverWorld.gameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Context.of(this));
		}
	}
}
