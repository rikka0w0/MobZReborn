package net.mobz.item;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class SimpleItem extends Item {
	@Nullable
	public final MobZRarity mobZTier;

	@Nullable
	private final boolean hasTooltip;

    /**
     * @param name        Naming rules: lower case English letters and numbers only, words are separated by '_', e.g. "cooked_beef"
     * @param hasSubItems
     */
    public SimpleItem(Item.Properties properties) {
    	this(properties, null, false);
    }

    public SimpleItem(Item.Properties properties, @Nullable MobZRarity mobZTier) {
    	this(properties, mobZTier, false);
    }

    public SimpleItem(Item.Properties properties, @Nullable MobZRarity mobZTier, boolean hasTooltip) {
    	super(properties);
    	this.mobZTier = mobZTier;
    	this.hasTooltip = hasTooltip;
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		if (this.hasTooltip) {
			tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltip"));
		}
		if (this.mobZTier != null) {
			this.mobZTier.addToTooltip(tooltip);
		}
	}

	public static Function<Item.Properties, SimpleItem> ofTier(@Nullable MobZRarity mobZTier) {
		return (props) -> new SimpleItem(props, mobZTier);
	}
}
