package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.block.TotemBase;
import net.mobz.init.MobZBlocks;

public class SacrificeKnife extends Item {
	public SacrificeKnife(Properties settings) {
		super(settings);
	}

	public static int getBloodCounter(ItemStack itemStack) {
		CompoundTag nbt = itemStack.getOrCreateTagElement("mobz");
		return getIntOrDef(nbt, "bloodCounter", 0);
	}

	public static int getDryingNumber(ItemStack itemStack) {
		CompoundTag nbt = itemStack.getOrCreateTagElement("mobz");
		return getIntOrDef(nbt, "dryingNumber", 0);
	}

	private static void setParam(ItemStack itemStack, int bloodCounter, int dryingNumber) {
		CompoundTag nbt = itemStack.getOrCreateTagElement("mobz");
		nbt.putInt("bloodCounter", bloodCounter);
		nbt.putInt("dryingNumber", dryingNumber);
	}

	public static int getIntOrDef(CompoundTag nbt, String key, int defaultVal) {
		return nbt.contains(key) ? nbt.getInt(key) : defaultVal;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip,
			TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.sacrifice_knife.tooltip"));
		tooltip.add(Component.translatable("item.mobz.sacrifice_knife.tooltip2"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		ItemStack itemStack = user.getItemInHand(hand);

		int bloodCounter = getBloodCounter(itemStack);
		int dryingNumber = getDryingNumber(itemStack);
		if (user.getHealth() > 2F) {
			user.hurt(world.damageSources().magic(), 2F);
			if (dryingNumber < 4) {
				dryingNumber = dryingNumber + 1;
			}
			if (bloodCounter < 5000) {
				bloodCounter = bloodCounter + 200;
			}
			setParam(itemStack, bloodCounter, dryingNumber);
			return InteractionResultHolder.success(itemStack);
		} else {
			return InteractionResultHolder.pass(itemStack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		int bloodCounter = getBloodCounter(stack);
		int dryingNumber = getDryingNumber(stack);
		if (bloodCounter > 0) {
			bloodCounter--;
		}
		if (bloodCounter == 0) {
			dryingNumber = 0;
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