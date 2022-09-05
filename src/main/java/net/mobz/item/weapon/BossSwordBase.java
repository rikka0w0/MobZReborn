package net.mobz.item.weapon;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class BossSwordBase extends SwordItem {
    public BossSwordBase(Tier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.boss_sword.tooltip"));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
