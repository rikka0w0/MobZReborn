package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class FrozenSwordBase extends SwordItem {
	public FrozenSwordBase(ToolMaterial toolMaterial, Item.Properties properties) {
        super(toolMaterial, 1, -2.4F, properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.UNCOMMON.addToTooltip(tooltip);
	}

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		// [1, 3]
		int durationMultiplifer = 1 + new Random().nextInt(3);
		MobEffectInstance effect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50 * durationMultiplifer, 1, false, false, false);
		target.addEffect(effect);

		return true;
	}
}
