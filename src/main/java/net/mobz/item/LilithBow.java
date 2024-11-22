package net.mobz.item;

import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mobz.MobZRarity;

public class LilithBow extends BowItem {
    public LilithBow(Item.Properties properties) {
        super(properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.RARE.addToTooltip(tooltip);
	}

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        MobEffectInstance spd = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1, false, false);
        LivingEntity bob = (LivingEntity) entity;
        if (selected == true) {
           if (bob.isUsingItem()) {
              bob.addEffect(spd);
           }
        }
    }
}