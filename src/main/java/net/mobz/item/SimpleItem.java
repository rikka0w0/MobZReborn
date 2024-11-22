package net.mobz.item;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SimpleItem extends Item {
	@Nullable
	public final MobZRarity mobZTier;

	@Nullable
	private MutableComponent tooltip;

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
    	this.tooltip = hasTooltip ? Component.translatable(this.getDescriptionId() + ".tooltip") : null;
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		if (this.tooltip != null) {
			tooltip.add(this.tooltip);
		}
		if (this.mobZTier != null) {
			this.mobZTier.addToTooltip(tooltip);
		}
	}

	public static Function<Item.Properties, SimpleItem> ofTier(@Nullable MobZRarity mobZTier) {
		return (props) -> new SimpleItem(props, mobZTier);
	}
}
