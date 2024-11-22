package net.mobz.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.function.BiConsumer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.mobz.MobZ;
import net.mobz.data.RewardLoot;
import net.mobz.init.MobZEntities;
import net.mobz.tags.MobZItemTags;

public class GoldenChicken extends Chicken {
	public GoldenChicken(EntityType<? extends GoldenChicken> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Animal.createAnimalAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.golden_chicken.life)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, this::isFood, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	@Override
	public float getAgeScale() {
		return this.isBaby() ? 0.924F : 1.0F;
	}

	@Override
	public boolean dropFromGiftLootTable(ServerLevel serverLevel, ResourceKey<LootTable> lootTable,
			BiConsumer<ServerLevel, ItemStack> itemStackSpawner) {
		if (lootTable == BuiltInLootTables.CHICKEN_LAY) {
			lootTable = RewardLoot.GOLDEN_CHICKEN_LAY;
		}
		return super.dropFromGiftLootTable(serverLevel, lootTable, itemStackSpawner);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.CHICKEN_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.CHICKEN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CHICKEN_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Override
	public GoldenChicken getBreedOffspring(ServerLevel world, AgeableMob passiveEntity_1) {
		return MobZEntities.GOLDEN_CHICKEN.get().create(world, EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(MobZItemTags.GOLDEN_CHICKEN_FOOD);
	}

	@Override
	public boolean removeWhenFarAway(double double_1) {
		return false;
	}

	@Override
	public boolean isChickenJockey() {
		return false;
	}

	@Override
	public void setChickenJockey(boolean dummy) {
		// Bypass the vanilla logic on purpose
	}
}
