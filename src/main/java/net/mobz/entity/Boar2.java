package net.mobz.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.mobz.init.MobZEntities;

public class Boar2 extends Pig {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar2(EntityType<? extends Pig> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createBoar2Attributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

	@Override
	public Entity getControllingPassenger() {
		return null;
	}

    @Override
    public boolean isSaddled() {
        return false;
    }

    public Boar2 method_6574(AgeableMob passiveEntity_1) {
        return (Boar2) MobZEntities.BOAR2.get().create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
	public Boar2 getBreedOffspring(ServerLevel world, AgeableMob passiveEntity) {
        return (Boar2) MobZEntities.BOAR2.get().create(this.level);
    }

    static {

        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
