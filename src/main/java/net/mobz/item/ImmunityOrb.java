package net.mobz.item;

import java.util.function.Consumer;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.mobz.MobZRarity;

public class ImmunityOrb extends Item {
	public ImmunityOrb(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		tooltip.accept(Component.translatable(this.descriptionId + ".tooltip"));
		MobZRarity.EPIC.addToTooltip(tooltip);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		if (!(entity instanceof LivingEntity user))
			return;

		if (SimpleItem.inventoryTickIsSlotQuickAccess(itemStack, entity)) {
			// TODO: check ok.hurtDir = 10F; // Yarn: knockbackVelocity
			if (user.hasEffect(MobEffects.WITHER) || user.hasEffect(MobEffects.POISON)) {
				user.removeEffect(MobEffects.WITHER);
				user.removeEffect(MobEffects.POISON);
			}
		}
	}
}