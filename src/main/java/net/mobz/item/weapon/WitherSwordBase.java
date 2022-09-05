package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

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

public class WitherSwordBase extends SwordItem {
    public WitherSwordBase(Tier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.wither_sword.tooltip"));
    }

    private static Supplier<MobEffectInstance> with1 = ()->new MobEffectInstance(MobEffects.WITHER, 60, 0, false, false, false);
    private static Supplier<MobEffectInstance> with2 = ()->new MobEffectInstance(MobEffects.WITHER, 120, 0, false, false, false);
    private static Supplier<MobEffectInstance> with3 = ()->new MobEffectInstance(MobEffects.WITHER, 180, 0, false, false, false);
    private static Supplier<MobEffectInstance> with4 = ()->new MobEffectInstance(MobEffects.WITHER, 240, 0, false, false, false);

    @Override
    public boolean hurtEnemy(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        itemStack_1.hurtAndBreak(1, (LivingEntity) livingEntity_2, (livingEntity_1x) ->
            ((LivingEntity) livingEntity_1x).broadcastBreakEvent(EquipmentSlot.MAINHAND));

		Random random = new Random();
		int randomNumber = random.nextInt() % 4;
		if (randomNumber < 0) {
			randomNumber = randomNumber * (-1);
		}

		switch (randomNumber) {
		case 0:
			livingEntity_1.addEffect(with1.get());
			return true;
		case 1:
			livingEntity_1.addEffect(with2.get());
			return true;
		case 2:
			livingEntity_1.addEffect(with3.get());
			return true;
		case 3:
			livingEntity_1.addEffect(with4.get());
			return true;
		default:
			return true;
		}
	}
}
