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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.mobz.init.MobZEntities;

public class Boar2 extends PigEntity {
    private static final Ingredient BREEDING_INGREDIENT;

    public Boar2(EntityType<? extends PigEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createBoar2Attributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean canBeControlledByRider() {
        return false;
    }

    @Override
    public boolean isSaddled() {
        return false;
    }

    public Boar2 method_6574(AgeableEntity passiveEntity_1) {
        return (Boar2) MobZEntities.BOAR2.create(this.level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
	public Boar2 getBreedOffspring(ServerWorld world, AgeableEntity passiveEntity) {
        return (Boar2) MobZEntities.BOAR2.create(this.level);
    }

    static {

        BREEDING_INGREDIENT = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }

}
