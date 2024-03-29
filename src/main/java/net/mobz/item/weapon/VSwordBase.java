package net.mobz.item.weapon;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class VSwordBase extends SwordItem {
    public VSwordBase(Tier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, -1, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.v_sword.tooltip"));
    }

    public boolean hurtEnemy(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        itemStack_1.hurtAndBreak(1, (LivingEntity) livingEntity_2, (livingEntity_1x) ->
            ((LivingEntity) livingEntity_1x).broadcastBreakEvent(EquipmentSlot.MAINHAND)
        );

        livingEntity_1.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 0, false, false, false));
        return true;
    }
}
