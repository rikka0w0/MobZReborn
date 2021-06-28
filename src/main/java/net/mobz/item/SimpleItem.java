package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SimpleItem extends Item {
    /**
     * @param name        Naming rules: lower case English letters and numbers only, words are separated by '_', e.g. "cooked_beef"
     * @param hasSubItems
     */
    public SimpleItem(Item.Properties properties) {
    	super(properties);
    }

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		String str = this.getDescriptionId(itemStack);
		tooltip.add(new TranslationTextComponent(str+".tooltip"));
	}
}
