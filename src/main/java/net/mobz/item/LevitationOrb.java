package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class LevitationOrb extends Item {
	public LevitationOrb(Item.Properties properties) {
		super(properties.durability(161));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip,
			TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.levitation_orb.tooltip"));
		tooltip.add(Component.translatable("item.mobz.levitation_orb.tooltip2"));
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
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		if (user instanceof Player) {
			ItemStack stack = user.getItemInHand(hand);
			if (stack.getDamageValue() == 0) {
				stack.setDamageValue(1);
				return InteractionResultHolder.success(user.getItemInHand(hand));
			} else {
				return InteractionResultHolder.pass(user.getItemInHand(hand));
			}
		}
		return InteractionResultHolder.pass(user.getItemInHand(hand));
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
						player.getCooldowns().addCooldown(this, stack.getMaxDamage());
					}
				}
			} else if (!player.getCooldowns().isOnCooldown(this)) {
				damage = 0;
			}
		}

		stack.setDamageValue(damage);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
}