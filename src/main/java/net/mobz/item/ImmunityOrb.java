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
import net.minecraft.world.level.Level;

public class ImmunityOrb extends Item {
    public ImmunityOrb(Item.Properties properties) {
        super(properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.immunity_orb.tooltip"));
        tooltip.add(Component.translatable("item.mobz.immunity_orb.tooltip2"));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
    	if (!(entity instanceof LivingEntity))
    		return;

    	LivingEntity ok = (LivingEntity) entity;
        if (slot >= 0 && slot <= 8 && !world.isClientSide) {
            // TODO: check ok.hurtDir = 10F; // Yarn: knockbackVelocity
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