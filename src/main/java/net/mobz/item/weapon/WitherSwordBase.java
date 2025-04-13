package net.mobz.item.weapon;

import java.util.Random;
import java.util.function.Consumer;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.mobz.MobZRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class WitherSwordBase extends Item {
	public WitherSwordBase(ToolMaterial toolMaterial, Item.Properties properties) {
		super(properties.sword(toolMaterial, -1, -2.4F));
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {
		MobZRarity.UNCOMMON.addToTooltip(tooltip);
	}

	@Override
	public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		// [1, 4]
		int durationMultiplier = 1 + new Random().nextInt(4);
		MobEffectInstance effect = new MobEffectInstance(MobEffects.WITHER, 60*durationMultiplier, 0, false, false, false);
		target.addEffect(effect);
	}
}
