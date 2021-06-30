package net.mobz.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZSounds;

public class Boar3 extends PigEntity {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar3(EntityType<? extends PigEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createBoar3Attributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean canBeControlledByRider() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MobZSounds.BOARSAYEVENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return MobZSounds.BOARSAYEVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MobZSounds.BOARDEATHEVENT;
    }

    @Override
    public boolean isSaddled() {
        return false;
    }

    public Boar3 method_6574(AgeableEntity passiveEntity_1) {
        return (Boar3) MobZEntities.BOAR3.create(this.level);
    }

    @Override
	public Boar3 getBreedOffspring(ServerWorld world, AgeableEntity passiveEntity) {
        return (Boar3) MobZEntities.BOAR3.create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    static {

        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
