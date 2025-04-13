package net.mobz.item.weapon;
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

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;

public class StoneTomahawk extends Item {
	public StoneTomahawk(ToolMaterial toolMaterial, Item.Properties properties) {
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

		target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 0, false, false, false));
	}
}
