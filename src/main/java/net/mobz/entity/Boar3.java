package net.mobz.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Boar3 extends Pig {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar3(EntityType<? extends Pig> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createBoar3Attributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

	@Override
	public LivingEntity getControllingPassenger() {
		return null;
	}

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.BOARSAYEVENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.BOARSAYEVENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.BOARDEATHEVENT.get();
    }

    @Override
    public boolean isSaddled() {
        return false;
    }

    @Override
	public Boar3 getBreedOffspring(ServerLevel world, AgeableMob passiveEntity) {
        return MobZEntities.BOAR3.get().create(world);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    static {

        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
