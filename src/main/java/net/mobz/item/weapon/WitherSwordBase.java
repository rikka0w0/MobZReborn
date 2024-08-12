package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;

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

public class WitherSwordBase extends SwordItem {
	public WitherSwordBase(Tier IItemTier_1, Item.Properties properties) {
		super(IItemTier_1, 1, -2.4f, properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.wither_sword.tooltip"));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, (livingEntity_1x) ->
			livingEntity_1x.broadcastBreakEvent(EquipmentSlot.MAINHAND)
		);

		// [1, 4]
		int durationMultiplier = 1 + new Random().nextInt(4);
		MobEffectInstance effect = new MobEffectInstance(MobEffects.WITHER, 60 * durationMultiplier, 0, false, false, false);
		target.addEffect(effect);

		return true;
	}
}
