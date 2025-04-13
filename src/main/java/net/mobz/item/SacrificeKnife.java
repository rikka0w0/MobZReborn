package net.mobz.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.MobZRarity;
import net.mobz.block.TotemBase;
import net.mobz.init.MobZBlocks;

public class SacrificeKnife extends SimpleItem {
	public SacrificeKnife(Properties settings) {
		super(settings.stacksTo(1).durability(5000), MobZRarity.UNCOMMON, true);
	}

	public static int getBloodCounter(ItemStack itemStack) {
		return itemStack.getDamageValue();
	}

	// For rendering only
	public static float getDryingNumber(ItemStack itemStack) {
		CustomModelData customModelData =
				itemStack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, CustomModelData.EMPTY);
		Float dryingNumber = customModelData.getFloat(0);
		return dryingNumber == null ? 0.0F : dryingNumber;
	}

	private static void setParam(ItemStack itemStack, int bloodCounter, float dryingNumber) {
		itemStack.setDamageValue(bloodCounter);
		CustomModelData customModelData =
				new CustomModelData(List.of(dryingNumber), List.of(), List.of(), List.of());
		itemStack.set(DataComponents.CUSTOM_MODEL_DATA, customModelData);
	}

	@Override
	public InteractionResult use(Level world, Player user, InteractionHand hand) {
		ItemStack itemStack = user.getItemInHand(hand);

		int bloodCounter = getBloodCounter(itemStack);
		if (user.getHealth() > 2F) {
			user.hurt(world.damageSources().magic(), 2F);
			if (bloodCounter < 5000) {
				bloodCounter = bloodCounter + 200;
			}
			setParam(itemStack, bloodCounter, 0);
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		int bloodCounter = getBloodCounter(stack);
		float dryingNumber = getDryingNumber(stack);

		if (bloodCounter > 0) {
			bloodCounter--;
		}

		if (bloodCounter == 0) {
			dryingNumber = 0;
		} else {
			dryingNumber += 0.01F;
			if (dryingNumber > 2.0F) {
				dryingNumber = 2.0F;
			}
		}

		setParam(stack, bloodCounter, dryingNumber);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		BlockState state = world.getBlockState(pos);
		BlockState stateUp = world.getBlockState(pos.above());
		BlockState stateDown = world.getBlockState(pos.below());

		if (state.getBlock() == MobZBlocks.TOTEM_MIDDLE.get()) {
			if (stateUp.getBlock() == MobZBlocks.TOTEM_TOP.get() && stateDown.getBlock() == MobZBlocks.TOTEM_BASE.get()) {
				if (MobZ.configs.pillager_boss.enabled) {
					ItemStack itemStack = context.getItemInHand();
					int bloodCounter = getBloodCounter(itemStack);

					if (!stateDown.getValue(TotemBase.ENABLED)) {
						if (bloodCounter > 4000) {
							world.playSound(player, pos, SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1F, 1F);
							MobZBlocks.TOTEM_BASE.get().trigger(world, pos.below());
							setParam(itemStack, 0, 0);
							return InteractionResult.SUCCESS;
						} else {
							player.displayClientMessage(Component.translatable("text.mobz.sacrifice_knife_blood"),
									true);
							return InteractionResult.PASS;
						}
					}

					return InteractionResult.PASS;
				} else {
					player.displayClientMessage(Component.translatable("text.mobz.pillagerspawnable"), true);
					return InteractionResult.PASS;
				}
			} else {
				player.displayClientMessage(Component.translatable("text.mobz.pillagermissing"), true);
				return InteractionResult.PASS;
			}
		} else {
			return InteractionResult.PASS;
		}
	}
}