package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Orb extends Item {
    public Orb(Item.Properties properties) {
        super(properties);
    }

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.orb.tooltip"));
        tooltip.add(new TranslationTextComponent("item.mobz.orb.tooltip2"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        PlayerEntity ok = (PlayerEntity) bob;
        if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7
                || slot == 8 && !world.isClientSide) {
            ok.hurtDir = 10F; // TODO: Yarn: knockbackVelocity
            if (ok.hasEffect(Effects.WITHER) || ok.hasEffect(Effects.POISON)) {
                ok.removeEffect(Effects.WITHER);
                ok.removeEffect(Effects.POISON);
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}