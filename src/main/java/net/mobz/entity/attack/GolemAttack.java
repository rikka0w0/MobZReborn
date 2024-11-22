package net.mobz.entity.attack;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.IronGolem;

public class GolemAttack extends MeleeAttackGoal {
	private final IronGolem golem;
	private int ticks;

	public GolemAttack(IronGolem golem, double speed, boolean pauseWhenMobIdle) {
		super(golem, speed, pauseWhenMobIdle);
		this.golem = golem;
	}

	public void start() {
		super.start();
		this.ticks = 0;
	}

	public void stop() {
		super.stop();
		this.golem.setAggressive(false);
	}

	public void tick() {
		super.tick();
		++this.ticks;
		if (this.ticks >= 5 && this.ticks < 10) {
			this.golem.setAggressive(true);
		} else {
			this.golem.setAggressive(false);
		}
	}
}
