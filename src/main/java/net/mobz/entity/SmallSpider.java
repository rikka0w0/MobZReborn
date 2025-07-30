package net.mobz.entity;

import javax.annotation.Nullable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mobz.MobZ;

public class SmallSpider extends CaveSpider {
	private int limitedLifeTicks;
	private boolean hasLimitedLife = false;

	@Nullable
	public Entity owner = null;

	public SmallSpider(EntityType<? extends CaveSpider> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createSpiSmallAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 5D * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 3D * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 15.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.removeAllGoals(goal -> goal instanceof MeleeAttackGoal);
		this.targetSelector.removeAllGoals(target -> target instanceof NearestAttackableTargetGoal);
		this.goalSelector.addGoal(4, new SpiderAttackGoal(this));
		this.targetSelector.addGoal(2, new SpiderTargetGoal<>(this, Player.class));
		this.targetSelector.addGoal(3, new SpiderTargetGoal<>(this, IronGolem.class));
	}

	public void setLimitedLife(int lifeTicks) {
		this.hasLimitedLife = true;
		this.limitedLifeTicks = lifeTicks;
	}
	@Override

	public void tick() {
		super.tick();

		if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
			this.limitedLifeTicks = 20;
			this.hurt(this.damageSources().starve(), 0.5F);
		}
	}

	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);

		if (this.hasLimitedLife) {
			valueOutput.putInt("LifeTicks", this.limitedLifeTicks);
		}
	}

	@Override
	public void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);

		valueInput.getInt("LifeTicks").ifPresentOrElse(this::setLimitedLife, () -> this.setLimitedLife(20));
	}

	@Override
	public void restoreFrom(Entity oldEntity) {
		super.restoreFrom(oldEntity);

		if (oldEntity instanceof SmallSpider oldSmallSpider) {
			this.owner = oldSmallSpider.owner;
		}
	}

	protected static class SpiderAttackGoal extends MeleeAttackGoal {
		public SpiderAttackGoal(Spider spider) {
			super(spider, 1.0, true);
		}

		@Override
		public boolean canUse() {
			return super.canUse() && !this.mob.isVehicle();
		}
	}

	protected static class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public SpiderTargetGoal(Spider spider, Class<T> entityTypeToTarget) {
			super(spider, entityTypeToTarget, true);
		}
	}
}
