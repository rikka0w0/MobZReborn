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

public class Boar extends PigEntity {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar(EntityType<? extends PigEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createBoarAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D)
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

    public Boar method_6574(AgeableEntity passiveEntity_1) {
        return (Boar) MobZEntities.BOAR.create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
	public Boar getBreedOffspring(ServerWorld world, AgeableEntity passiveEntity) {
        return (Boar) MobZEntities.BOAR.create(this.level);
    }

    static {
        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
