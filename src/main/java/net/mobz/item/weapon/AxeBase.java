package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import net.minecraft.world.item.TooltipFlag;
import net.mobz.MobZRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AxeBase extends SwordItem {
	public AxeBase(Tier tier, Item.Properties properties) {
		super(tier, properties.attributes(SwordItem.createAttributes(tier, 1, -3.4F)));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
		MobZRarity.RARE.addToTooltip(tooltip);
	}

	private final static List<Set<Supplier<MobEffectInstance>>> EFFECTS = List.of(
		Set.of(
			()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false)
		)
	);

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		int effectIndex = new Random().nextInt(4);
		EFFECTS.get(effectIndex).stream().map(Supplier::get).forEach(target::addEffect);

		return true;
	}
}
