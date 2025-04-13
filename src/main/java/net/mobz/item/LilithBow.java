package net.mobz.item;

import java.util.function.Consumer;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.mobz.MobZRarity;

public class LilithBow extends BowItem {
	public LilithBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		MobZRarity.RARE.addToTooltip(tooltip);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		if (entity instanceof LivingEntity user && slot != null && slot.getType() == EquipmentSlot.Type.HAND) {
			if (user.isUsingItem()) {
				user.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 1, false, false));
			}
		}
	}
}