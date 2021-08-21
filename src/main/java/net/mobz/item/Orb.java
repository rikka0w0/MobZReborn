package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class Orb extends Item {
    public Orb(Item.Properties properties) {
        super(properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent("item.mobz.orb.tooltip"));
        tooltip.add(new TranslatableComponent("item.mobz.orb.tooltip2"));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
    	if (!(entity instanceof LivingEntity))
    		return;

    	LivingEntity ok = (LivingEntity) entity;
        if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7
                || slot == 8 && !world.isClientSide) {
            ok.hurtDir = 10F; // Yarn: knockbackVelocity
            if (ok.hasEffect(MobEffects.WITHER) || ok.hasEffect(MobEffects.POISON)) {
                ok.removeEffect(MobEffects.WITHER);
                ok.removeEffect(MobEffects.POISON);
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}