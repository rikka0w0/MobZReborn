package net.mobz.entity;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
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

public class Boar extends Pig {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar(EntityType<? extends Pig> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createBoarAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D)
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

    public Boar method_6574(AgableMob passiveEntity_1) {
        return (Boar) MobZEntities.BOAR.create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
	public Boar getBreedOffspring(ServerLevel world, AgableMob passiveEntity) {
        return (Boar) MobZEntities.BOAR.create(this.level);
    }

    static {
        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
