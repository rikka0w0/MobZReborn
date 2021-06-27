package net.mobz.item;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class Rottenflesh extends SimpleItem {
	public static final Food FOOD_COMPONENT = (new Food.Builder()).nutrition(5).saturationMod(0.8F).meat().build();

	public Rottenflesh(String name, Item.Properties properties) {
		super(name, properties.food(FOOD_COMPONENT));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		EffectInstance hunger = new EffectInstance(Effect.byId(17), 600, 0, true, false);
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
