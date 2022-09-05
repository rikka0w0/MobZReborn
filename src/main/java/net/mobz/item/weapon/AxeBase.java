package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class AxeBase extends SwordItem {
	public AxeBase(Tier IItemTier_1, Item.Properties properties) {
		super(IItemTier_1, 1, -3.4f, properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.axe.tooltip"));
	}

	private static Supplier<MobEffectInstance> slow1 = ()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, false);
	private static Supplier<MobEffectInstance> strength = ()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, 0, false, false, false);
	private static Supplier<MobEffectInstance> defense = ()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<MobEffectInstance> slow2 = ()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, false, false, false);
	private static Supplier<MobEffectInstance> blind2 = ()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<MobEffectInstance> strength2 = ()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<MobEffectInstance> defense2 = ()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<MobEffectInstance> slow3 = ()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 0, false, false, false);
	private static Supplier<MobEffectInstance> blind3 = ()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<MobEffectInstance> strength3 = ()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<MobEffectInstance> defense3 = ()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<MobEffectInstance> slow4 = ()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 0, false, false, false);
	private static Supplier<MobEffectInstance> blind4 = ()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<MobEffectInstance> strength4 = ()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<MobEffectInstance> defense4 = ()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false);

	@Override
	public boolean hurtEnemy(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        itemStack_1.hurtAndBreak(1, livingEntity_2, (livingEntity_1x) ->
            livingEntity_1x.broadcastBreakEvent(EquipmentSlot.MAINHAND));

		Random random = new Random();
		int randomNumber = random.nextInt() % 4;

		if (randomNumber < 0) {
			randomNumber = randomNumber * (-1);
		}

		switch (randomNumber) {
		case 0:
			livingEntity_1.addEffect(slow1.get());
			livingEntity_2.addEffect(strength.get());
			livingEntity_2.addEffect(defense.get());
			return true;
		case 1:
			livingEntity_1.addEffect(slow2.get());
			livingEntity_1.addEffect(blind2.get());
			livingEntity_2.addEffect(strength2.get());
			livingEntity_2.addEffect(defense2.get());
			return true;
		case 2:
			livingEntity_1.addEffect(slow3.get());
			livingEntity_1.addEffect(blind3.get());
			livingEntity_2.addEffect(strength3.get());
			livingEntity_2.addEffect(defense3.get());
			return true;
		case 3:
			livingEntity_1.addEffect(slow4.get());
			livingEntity_1.addEffect(blind4.get());
			livingEntity_2.addEffect(strength4.get());
			livingEntity_2.addEffect(defense4.get());
			return true;
		default:
			return true;
		}
	}
}
