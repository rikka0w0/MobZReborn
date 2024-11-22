package net.mobz.item.weapon;

import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.network.chat.Component;

public class BossSwordBase extends SwordItem {
	public BossSwordBase(Tier tier, Item.Properties properties) {
		super(tier, properties.attributes(SwordItem.createAttributes(tier, 1, -2.4F)));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.EPIC.addToTooltip(tooltip);
	}
}
