package net.mobz.item;

import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mobz.MobZRarity;

public class LevitationOrb extends Item {
	public LevitationOrb(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip,
			TooltipFlag flag) {
		tooltip.add(Component.translatable(this.descriptionId + ".tooltip"));
		MobZRarity.LEGENDARY.addToTooltip(tooltip);
	}

	/**
	 * Orb States:
	 * 1. damage==0: Idle, ready to use. Right click enters state 2.
	 * 2. damage>0 && damage<damageMax: Flying. damage++ until damage==damageMax
	 * 3. damage=damageMax: Cooling down. On entry, start a cooling down instance, go to state 1 once done.
	 *
	 * damageMax is flying ticks minus 1.
	 */

	@Override
	public InteractionResult use(Level world, Player user, InteractionHand hand) {
		if (user instanceof Player) {
			ItemStack stack = user.getItemInHand(hand);
			if (stack.getDamageValue() == 0) {
				stack.setDamageValue(1);
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.PASS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (!(entity instanceof Player))
			return;

		Player player = (Player) entity;
		int damage = stack.getDamageValue();

		if (damage > 0) {
			if (damage < stack.getMaxDamage()) {
				if (selected) {
					player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 9, 1, false, false));
					damage++;

					if (damage == stack.getMaxDamage()) {
						player.getCooldowns().addCooldown(stack, stack.getMaxDamage());
					}
				}
			} else if (!player.getCooldowns().isOnCooldown(stack)) {
				damage = 0;
			}
		}

		stack.setDamageValue(damage);
	}
}