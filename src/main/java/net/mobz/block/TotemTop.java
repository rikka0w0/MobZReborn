package net.mobz.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.mobz.init.MobZBlocks;

public class TotemTop extends AbstractHead {
	@Nullable
	private BlockPattern golemPattern;
	protected static final VoxelShape SHAPE = box(4D, 0, 4D, 12D, 10D, 12D);

	public TotemTop(Properties settings) {
		super(settings);
	}

	private BlockPattern getGolemPattern() {
		if (this.golemPattern == null) {
			this.golemPattern = BlockPatternBuilder.start().aisle("~T~", "~M~", "~B~")
					.where('T', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(MobZBlocks.TOTEM_TOP)))
					.where('M', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(MobZBlocks.TOTEM_MIDDLE)))
					.where('B', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(MobZBlocks.TOTEM_BASE)))
					.where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.golemPattern;
	}

	@Override
	public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
		BlockPattern.PatternHelper blockpattern$patternhelper = this.getGolemPattern().find(world, pos);
		if (blockpattern$patternhelper != null) {
			for (int j = 0; j < this.getGolemPattern().getWidth(); ++j) {
				for (int k = 0; k < this.getGolemPattern().getHeight(); ++k) {
					CachedBlockInfo cachedblockinfo2 = blockpattern$patternhelper.getBlock(j, k, 0);
					world.setBlock(cachedblockinfo2.getPos(), Blocks.AIR.defaultBlockState(), 2);
					world.globalLevelEvent(2001, cachedblockinfo2.getPos(), Block.getId(cachedblockinfo2.getState()));
				}
			}

			BlockPos blockpos = blockpattern$patternhelper.getBlock(1, 2, 0).getPos();
			TNTEntity tntentity = new TNTEntity(world, (double) blockpos.getX() + 0.5D, (double) blockpos.getY(),
					(double) blockpos.getZ() + 0.5D, null);
			world.addFreshEntity(tntentity);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader world, List<ITextComponent> tooltip,
			ITooltipFlag options) {
		tooltip.add(new TranslationTextComponent("block.mobz.totemtop.tooltip"));
	}
}
