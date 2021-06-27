package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mobz.common.IRegistrable;

public class Shield extends ShieldItem implements IRegistrable {
	private final String regName;

	@Override
	public String Mobz$getRegistryName() {
		return regName;
	}

    public Shield(String name, Properties settings) {
        super(settings);
        this.regName = name;
    }

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.shield.tooltip"));
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    // Fabric: Need impl
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
    	return true;
    }
}
