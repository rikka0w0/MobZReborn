package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Sbow extends BowItem {
    public Sbow(Item.Properties properties) {
        super(properties);
    }

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		String str = this.getDescriptionId(itemStack);
		tooltip.add(new TranslatableComponent(str+".tooltip"));
	}

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        MobEffectInstance spd = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 7, 1, false, false);
        LivingEntity bob = (LivingEntity) entity;
        if (selected == true) {
           if (bob.isUsingItem()) {
              bob.addEffect(spd);
           }
        }
    }
}