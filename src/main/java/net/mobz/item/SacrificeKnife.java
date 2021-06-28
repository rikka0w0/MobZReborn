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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SacrificeKnife extends Item {
	public int bloodCounter = 0;
	public int dryingNumber = 0;

	public SacrificeKnife(Properties settings) {
		super(settings);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip,
			ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent("item.mobz.sacrificeknife.tooltip"));
		tooltip.add(new TranslationTextComponent("item.mobz.sacrificeknife.tooltip2"));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getItemInHand(hand);
		if (!world.isClientSide && user.getHealth() > 2F) {
			user.hurt(DamageSource.MAGIC, 2F);
			if (dryingNumber < 4) {
				dryingNumber = dryingNumber + 1;
			}
			if (bloodCounter < 5000) {
				bloodCounter = bloodCounter + 950;
			}
			return ActionResult.success(itemStack);
		} else
			return ActionResult.success(itemStack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClientSide) {
			if (bloodCounter > 0) {
				bloodCounter--;
			}
			if (bloodCounter == 0) {
				dryingNumber = 0;
			}
		}
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		PlayerEntity player = context.getPlayer();
		BlockState state = world.getBlockState(context.getClickedPos());
		BlockState stateUp = world.getBlockState(context.getClickedPos().above());
		BlockState stateDown = world.getBlockState(context.getClickedPos().below());

		// TODO: Impl this
		/*
		if (state.getBlock() == Blockinit.TOTEM_MIDDLE) {
			if (stateUp.getBlock() == Blockinit.TOTEM_TOP && stateDown.getBlock() == Blockinit.TOTEM_BASE) {
				if (AutoConfig.getConfigHolder(configz.class).getConfig().PillagerBossSpawn) {
					if (bloodCounter > 3000) {
						if (Blockinit.TOTEMMIDDLEENTITY.getBlockEntity(world, pos).startTimer == false) {
							world.playSound(player, pos, SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1F, 1F);
							Blockinit.TOTEMMIDDLEENTITY.getBlockEntity(world, pos).startTimer = true;
							return ActionResultType.SUCCESS;
						} else {
							return ActionResultType.PASS;
						}
					} else {
						player.displayClientMessage(new TranslationTextComponent("text.mobz.sacrificeknifeblood"),
								true);
						return ActionResultType.PASS;
					}
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
		}*/
		
		return ActionResultType.PASS;
	}
}