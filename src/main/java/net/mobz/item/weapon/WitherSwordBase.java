package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;

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

public class WitherSwordBase extends SwordItem {
    public WitherSwordBase(Tier tier, Item.Properties properties) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, -1, -2.4F)));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.wither_sword.tooltip"));
    }

    @Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		// [1, 4]
		int durationMultiplier = 1 + new Random().nextInt(4);
		MobEffectInstance effect = new MobEffectInstance(MobEffects.WITHER, 60*durationMultiplier, 0, false, false, false);
		target.addEffect(effect);

		return true;
	}
}
