package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AxeBase extends SwordItem {
	public AxeBase(IItemTier IItemTier_1, Item.Properties properties) {
		super(IItemTier_1, 1, -3.4f, properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent("item.mobz.axe.tooltip"));
	}

	private static Supplier<EffectInstance> slow1 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 0, false, false, false);
	private static Supplier<EffectInstance> strength = ()->new EffectInstance(Effects.DAMAGE_BOOST, 50, 0, false, false, false);
	private static Supplier<EffectInstance> defense = ()->new EffectInstance(Effects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<EffectInstance> slow2 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 0, false, false, false);
	private static Supplier<EffectInstance> blind2 = ()->new EffectInstance(Effects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<EffectInstance> strength2 = ()->new EffectInstance(Effects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<EffectInstance> defense2 = ()->new EffectInstance(Effects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<EffectInstance> slow3 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 120, 0, false, false, false);
	private static Supplier<EffectInstance> blind3 = ()->new EffectInstance(Effects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<EffectInstance> strength3 = ()->new EffectInstance(Effects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<EffectInstance> defense3 = ()->new EffectInstance(Effects.DAMAGE_RESISTANCE, 40, 0, false, false, false);
	private static Supplier<EffectInstance> slow4 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 140, 0, false, false, false);
	private static Supplier<EffectInstance> blind4 = ()->new EffectInstance(Effects.BLINDNESS, 20, 0, false, false, false);
	private static Supplier<EffectInstance> strength4 = ()->new EffectInstance(Effects.DAMAGE_BOOST, 70, 0, false, false, false);
	private static Supplier<EffectInstance> defense4 = ()->new EffectInstance(Effects.DAMAGE_RESISTANCE, 40, 0, false, false, false);

	@Override
	public boolean hurtEnemy(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        itemStack_1.hurtAndBreak(1, livingEntity_2, (livingEntity_1x) -> 
            livingEntity_1x.broadcastBreakEvent(EquipmentSlotType.MAINHAND));

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
