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

public class FrozenSwordBase extends SwordItem {
    public FrozenSwordBase(Tier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.frozen_sword.tooltip"));
    }

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, (livingEntity_1x) ->
			livingEntity_1x.broadcastBreakEvent(EquipmentSlot.MAINHAND)
		);

		// [1, 3]
		int durationMultiplifer = 1 + new Random().nextInt(3);
		MobEffectInstance effect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50 * durationMultiplifer, 1, false, false, false);
		target.addEffect(effect);

		return true;
	}
}
