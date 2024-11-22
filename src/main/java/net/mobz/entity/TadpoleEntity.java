package net.mobz.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;

public class TadpoleEntity extends AbstractFish {
	private int babyTime = -6000;

	public TadpoleEntity(EntityType<? extends TadpoleEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void tick()
	{
		super.tick();
		if(!this.level().isClientSide())
		{
			babyTime++;
			if(!isBaby())
			{
				ToadEntity toad = MobZEntities.TOAD.get().create(this.level(), EntitySpawnReason.CONVERSION);
				toad.moveTo(this.getX(), this.getY(), this.getZ(), yBodyRot, getXRot());
				toad.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0));
				((ServerLevel) this.level()).addFreshEntityWithPassengers(toad);

				this.discard();
			}
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand)
	{
		ItemStack itemStack = player.getItemInHand(hand);
		if(!itemStack.isEmpty() && itemStack.is(ToadEntity.getToadFoodTag()))
		{
			if(this.isBaby())
			{
				itemStack.shrink(1);
				this.growUp(Math.abs(babyTime / 20));
				return InteractionResult.SUCCESS;
			}

			if(this.level().isClientSide)
			{
				return InteractionResult.CONSUME;
			}
		}

		return super.mobInteract(player, hand);
	}

	public void growUp(int age)
	{
		babyTime += age;
	}

	@Override
	public void setBaby(boolean baby)
	{
		babyTime = baby ? -24000 : 0;
	}

	@Override
	public boolean isBaby()
	{
		return babyTime < 0;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag)
	{
		super.addAdditionalSaveData(tag);
		tag.putInt("BabyTime", babyTime);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag)
	{
		super.readAdditionalSaveData(tag);
		if(tag.contains("BabyTime")) babyTime = tag.getInt("BabyTime");
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(MobZItems.TADPOLE_BUCKET.get());
	}

	@Override
	protected SoundEvent getFlopSound()
	{
		return SoundEvents.TROPICAL_FISH_FLOP;
	}

	public static AttributeSupplier.Builder createEntityAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}
}
