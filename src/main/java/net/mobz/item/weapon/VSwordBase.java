package net.mobz.item.weapon;

import java.util.List;

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

public class VSwordBase extends SwordItem {
	public VSwordBase(Tier tier, Item.Properties properties) {
		super(tier, properties.attributes(SwordItem.createAttributes(tier, -1, -2.4F)));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.stone_tomahawk.tooltip"));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 0, false, false, false));
		return true;
	}
}
