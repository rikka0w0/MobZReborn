package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Orb2 extends Item {
    private int sam = 0; // TODO: This might not work in multi-player

    public Orb2(Item.Properties properties) {
        super(properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.orb2.tooltip"));
        tooltip.add(new TranslationTextComponent("item.mobz.orb2.tooltip2"));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof PlayerEntity) {
            if (world.isClientSide) {
                if (sam == 0) {
                    sam++;
                    return ActionResult.success(user.getItemInHand(hand));
                } else {
                    return ActionResult.pass(user.getItemInHand(hand));
                }
            }
        }
        return ActionResult.pass(user.getItemInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        EffectInstance lev = new EffectInstance(Effect.byId(25), 9, 1, false, false);
        LivingEntity bob = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) bob;
        if (!world.isClientSide) {
            if (sam <= 160 && sam > 0 && selected == true) {
                bob.addEffect(lev);
                sam++;
            } else {
                if (sam > 160) {
                    player.getCooldowns().addCooldown(this, 80);
                    sam = -80;
                } else {
                    if (selected == false && sam >= 1) {
                        sam = sam - 1;
                    } else {
                        if (sam < 0) {
                            sam++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}