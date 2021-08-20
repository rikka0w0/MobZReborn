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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class Orb2 extends Item {
	public Orb2(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip,
			TooltipFlag flag) {
		tooltip.add(new TranslatableComponent("item.mobz.orb2.tooltip"));
		tooltip.add(new TranslatableComponent("item.mobz.orb2.tooltip2"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		if (user instanceof Player) {
			ItemStack stack = user.getItemInHand(hand);
			int sam = stack.getDamageValue();
			if (sam == 0) {
				sam++;
				stack.setDamageValue(sam);
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
		MobEffectInstance lev = new MobEffectInstance(MobEffects.LEVITATION, 9, 1, false, false);
		int sam = stack.getDamageValue();
		if (sam <= 160 && sam > 0 && selected == true) {
			player.addEffect(lev);
			sam++;
		} else {
			if (sam > 160) {
				player.getCooldowns().addCooldown(this, 80);
				sam = -80;
			} else {
				if (selected == false && sam >= 1) {
					sam--;
				} else {
					if (sam < 0) {
						sam++;
					}
				}
			}
		}
		stack.setDamageValue(sam);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
}