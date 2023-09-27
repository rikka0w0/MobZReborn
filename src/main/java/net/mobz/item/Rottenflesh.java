package net.mobz.item;

import java.util.Random;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

public class Rottenflesh extends SimpleItem {
	public static final FoodProperties FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.8F).meat().build();

	public Rottenflesh(Item.Properties properties) {
		super(properties.food(FOOD_COMPONENT));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		MobEffectInstance hunger = new MobEffectInstance(MobEffects.HUNGER, 600, 0, true, false);
		Random random = new Random();
		int randomNumber = random.nextInt() % 2;

		if (randomNumber < 0) {
			randomNumber = randomNumber * (-1);
		}
		if (!world.isClientSide && randomNumber == 0) {

			entity.addEffect(hunger);
		}

		return super.finishUsingItem(stack, world, entity);
	}

}
