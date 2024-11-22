package net.mobz.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class SmallZombie extends Zombie {
	public Entity owner;

	public SmallZombie(EntityType<? extends Zombie> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 6D * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 3D * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 15.0D)
				.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0D);
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	public boolean isUnderWaterConverting() {
		return false;
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MobZSounds.SAYSPEEDEVENT.get();
	}

	@Override
	protected SoundEvent getStepSound() {
		return MobZSounds.STEPSPEEDEVENT.get();
	}

	@Override
	public boolean shouldDropExperience() {
		return false;
	}

	@Override
	public boolean isBaby() {
		return false;
	}

    @Override
    public void restoreFrom(Entity oldEntity) {
    	super.restoreFrom(oldEntity);

    	if (oldEntity instanceof SmallZombie oldSmallZombie) {
    		this.owner = oldSmallZombie.owner;
    	}
    }
}
