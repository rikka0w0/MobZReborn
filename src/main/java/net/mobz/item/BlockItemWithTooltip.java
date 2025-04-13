package net.mobz.item;

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

public class BlockItemWithTooltip extends BlockItem {
	private final Consumer<Consumer<Component>> toolTipAppenderImpl;

	public BlockItemWithTooltip(Block block, Item.Properties properties,
		Consumer<Consumer<Component>> toolTipAppenderImpl) {

		super(block, properties);
		this.toolTipAppenderImpl = toolTipAppenderImpl;
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		this.toolTipAppenderImpl.accept(tooltip);
	}
}
