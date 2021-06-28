package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.item.IItemTier;
import net.minecraft.world.World;

public class FrozenSwordBase extends SwordItem {
    public FrozenSwordBase(IItemTier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, -2.4f, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.frozen_sword.tooltip"));
    }

    private static Supplier<EffectInstance> slow1 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 50, 1, false, false, false);
    private static Supplier<EffectInstance> slow3 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 1, false, false, false);
    private static Supplier<EffectInstance> slow4 = ()->new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150, 1, false, false, false);

    @Override
    public boolean hurtEnemy(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        itemStack_1.hurtAndBreak(1, livingEntity_2, (livingEntity_1x) -> 
            ((LivingEntity) livingEntity_1x).broadcastBreakEvent(EquipmentSlotType.MAINHAND)
        );

		Random random = new Random();
		int randomNumber = random.nextInt() % 3;
		if (randomNumber < 0) {
			randomNumber = randomNumber * (-1);
		}

		switch (randomNumber) {
		case 0:
			livingEntity_1.addEffect(slow1.get());
			return true;
		case 1:
			livingEntity_1.addEffect(slow3.get());
			return true;
		case 2:
			livingEntity_1.addEffect(slow4.get());
			return true;
		default:
			return true;
		}
    }
}
