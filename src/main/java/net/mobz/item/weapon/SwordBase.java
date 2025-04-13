package net.mobz.item.weapon;

import java.util.function.Consumer;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.mobz.MobZRarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class SwordBase extends Item {
	public SwordBase(ToolMaterial toolMaterial, Item.Properties properties) {
		super(properties.sword(toolMaterial, 0.0F, -2.4F));
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		MobZRarity.LEGENDARY.addToTooltip(tooltip);
	}
}
