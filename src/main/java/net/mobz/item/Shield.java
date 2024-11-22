package net.mobz.item;

import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.network.chat.Component;

public class Shield extends ShieldItem {
	public Shield(Properties settings) {
		super(settings);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.UNCOMMON.addToTooltip(tooltip);
	}
}
