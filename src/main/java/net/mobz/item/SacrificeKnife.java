package net.mobz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.block.TotemBase;
import net.mobz.init.MobZBlocks;

public class SacrificeKnife extends Item {
	public SacrificeKnife(Properties settings) {
		super(settings);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip,
			ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent("item.mobz.sacrificeknife.tooltip"));
		tooltip.add(new TranslationTextComponent("item.mobz.sacrificeknife.tooltip2"));
	}

	public static int getIntOrDef(CompoundNBT nbt, String key, int defaultVal) {
		return nbt.contains(key) ? nbt.getInt(key) : defaultVal;
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getItemInHand(hand);

		CompoundNBT nbt = itemStack.getOrCreateTagElement("mobz");
		int bloodCounter = getIntOrDef(nbt, "bloodCounter", 0);
		int dryingNumber = getIntOrDef(nbt, "dryingNumber", 0);
		if (user.getHealth() > 2F) {
			user.hurt(DamageSource.MAGIC, 2F);
			if (dryingNumber < 4) {
				dryingNumber = dryingNumber + 1;
			}
			if (bloodCounter < 5000) {
				bloodCounter = bloodCounter + 200;
			}
			nbt.putInt("bloodCounter", bloodCounter);
			nbt.putInt("dryingNumber", dryingNumber);
			return ActionResult.success(itemStack);
		} else {
			return ActionResult.pass(itemStack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		CompoundNBT nbt = stack.getOrCreateTagElement("mobz");
		int bloodCounter = getIntOrDef(nbt, "bloodCounter", 0);
		int dryingNumber = getIntOrDef(nbt, "dryingNumber", 0);
		if (bloodCounter > 0) {
			bloodCounter--;
		}
		if (bloodCounter == 0) {
			dryingNumber = 0;
		}
		nbt.putInt("bloodCounter", bloodCounter);
		nbt.putInt("dryingNumber", dryingNumber);
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		PlayerEntity player = context.getPlayer();
		BlockState state = world.getBlockState(pos);
		BlockState stateUp = world.getBlockState(pos.above());
		BlockState stateDown = world.getBlockState(pos.below());

		if (state.getBlock() == MobZBlocks.TOTEM_MIDDLE) {
			if (stateUp.getBlock() == MobZBlocks.TOTEM_TOP && stateDown.getBlock() == MobZBlocks.TOTEM_BASE) {
				if (Configs.instance.PillagerBossSpawn) {
					ItemStack itemStack = context.getItemInHand();
					CompoundNBT nbt = itemStack.getOrCreateTagElement("mobz");
					int bloodCounter = getIntOrDef(nbt, "bloodCounter", 0);
					int dryingNumber = getIntOrDef(nbt, "dryingNumber", 0);

					if (!stateDown.getValue(TotemBase.ENABLED)) {
						if (bloodCounter > 4000) {
							world.playSound(player, pos, SoundEvents.WITHER_SPAWN, SoundCategory.HOSTILE, 1F, 1F);
							MobZBlocks.TOTEM_BASE.trigger(world, pos.below());
							bloodCounter = 0;
							dryingNumber = 0;
							nbt.putInt("bloodCounter", bloodCounter);
							nbt.putInt("dryingNumber", dryingNumber);
							return ActionResultType.SUCCESS;							
						} else {
							player.displayClientMessage(new TranslationTextComponent("text.mobz.sacrificeknifeblood"),
									true);
							return ActionResultType.PASS;
						}
					}

					return ActionResultType.PASS;
				} else {
					player.displayClientMessage(new TranslationTextComponent("text.mobz.pillagerspawnable"), true);
					return ActionResultType.PASS;
				}
			} else {
				player.displayClientMessage(new TranslationTextComponent("text.mobz.pillagermissing"), true);
				return ActionResultType.PASS;
			}
		} else {
			return ActionResultType.PASS;
		}
	}
}