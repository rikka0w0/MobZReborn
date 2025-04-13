package net.mobz.item.weapon;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.mobz.MobZRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AxeBase extends Item {
	public AxeBase(ToolMaterial toolMaterial, Item.Properties properties) {
		super(properties.sword(toolMaterial, 1.0F, -3.4F));
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		MobZRarity.RARE.addToTooltip(tooltip);
	}

	private final static List<Set<Supplier<MobEffectInstance>>> EFFECTS = List.of(
		Set.of(
			()->new MobEffectInstance(MobEffects.SLOWNESS, 40, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.STRENGTH, 50, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.SLOWNESS, 100, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.STRENGTH, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.SLOWNESS, 120, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.STRENGTH, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.RESISTANCE, 40, 0, false, false, false)
		),
		Set.of(
			()->new MobEffectInstance(MobEffects.SLOWNESS, 140, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.STRENGTH, 70, 0, false, false, false),
			()->new MobEffectInstance(MobEffects.RESISTANCE, 40, 0, false, false, false)
		)
	);

	@Override
	public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
		itemStack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);

		int effectIndex = new Random().nextInt(4);
		EFFECTS.get(effectIndex).stream().map(Supplier::get).forEach(target::addEffect);
	}
}
