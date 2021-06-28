package net.mobz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import net.mobz.init.MobZBlocks;

public class EnderHeader extends AbstractHead {
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);;

	public EnderHeader(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public static boolean isValid(World world, BlockPos pos, BlockState state) {
		if (state.getBlock() != MobZBlocks.ENDERHEADER) {
			return false;
		}

		BlockState bottom = world.getBlockState(pos.below(2));
		BlockState top1 = world.getBlockState(pos.below().east());
		BlockState top2 = world.getBlockState(pos.below());
		BlockState top3 = world.getBlockState(pos.below().west());
		BlockState head1 = world.getBlockState(pos.east());
		BlockState head2 = world.getBlockState(pos);
		BlockState head3 = world.getBlockState(pos.west());

		if (bottom.getBlock() == Blocks.SOUL_SAND) {
			if (top1.getBlock() == Blocks.SOUL_SAND) {
				if (top2.getBlock() == Blocks.SOUL_SAND) {
					if (top3.getBlock() == Blocks.SOUL_SAND) {
						if (head1.getBlock() == MobZBlocks.ENDERHEADER) {
							if (head2.getBlock() == MobZBlocks.ENDERHEADER) {
								if (head3.getBlock() == MobZBlocks.ENDERHEADER) {
									world.removeBlock(pos, false);
									world.removeBlock(pos.east(), false);
									world.removeBlock(pos.west(), false);
									world.removeBlock(pos.below(2), false);
									world.removeBlock(pos.below().east(), false);
									world.removeBlock(pos.below(), false);
									world.removeBlock(pos.below().west(), false);
									return true;
								}
							}
						}
					}
				}
			}
		}

		BlockState top4 = world.getBlockState(pos.below().north());
		BlockState top5 = world.getBlockState(pos.below().south());
		BlockState head4 = world.getBlockState(pos.north());
		BlockState head5 = world.getBlockState(pos.south());

		if (bottom.getBlock() == Blocks.SOUL_SAND) {
			if (top4.getBlock() == Blocks.SOUL_SAND) {
				if (top2.getBlock() == Blocks.SOUL_SAND) {
					if (top5.getBlock() == Blocks.SOUL_SAND) {
						if (head4.getBlock() == MobZBlocks.ENDERHEADER) {
							if (head2.getBlock() == MobZBlocks.ENDERHEADER) {
								if (head5.getBlock() == MobZBlocks.ENDERHEADER) {
									world.removeBlock(pos, false);
									world.removeBlock(pos.north(), false);
									world.removeBlock(pos.south(), false);
									world.removeBlock(pos.below(2), false);
									world.removeBlock(pos.below().north(), false);
									world.removeBlock(pos.below(), false);
									world.removeBlock(pos.below().south(), false);
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;

	}
}

/*
 * public void onStacksDropped(BlockState state, World world, BlockPos pos,
 * ItemStack stack) { ItemStack headdrop = new ItemStack(Blocks.ENDERHEADER);
 * super.onStacksDropped(state, world, pos, stack); if
 * (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 1) {
 * Block.dropStack(world, pos, headdrop); } }
 */

/*
 * public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity
 * player) { world.playSound(player, pos, SoundEvents.ENTITY_WITHER_SPAWN,
 * SoundCategory.AMBIENT, 1F, 0.3F); }
 */