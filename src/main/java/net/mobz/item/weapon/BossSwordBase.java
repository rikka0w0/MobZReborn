package net.mobz.item.weapon;

import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.network.chat.Component;

public class BossSwordBase extends SwordItem {
	public BossSwordBase(ToolMaterial toolMaterial, Item.Properties properties) {
		super(toolMaterial, 1, -2.4F, properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.EPIC.addToTooltip(tooltip);
	}
}
