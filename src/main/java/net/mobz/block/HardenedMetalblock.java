package net.mobz.block;

import java.util.List;
import java.util.function.Predicate;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.mobz.entity.MetalGolem;
import net.mobz.init.MobZEntities;

public class HardenedMetalblock extends Block {
	@Nullable
	private BlockPattern golemPattern;
	private static final Predicate<BlockState> IS_PUMPKIN = (state) -> {
		return state != null && (state.is(Blocks.CARVED_PUMPKIN) || state.is(Blocks.JACK_O_LANTERN));
	};

	public HardenedMetalblock(Properties settings) {
		super(settings);
	}

	private BlockPattern getGolemPattern() {
		if (this.golemPattern == null) {
			this.golemPattern = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
					.where('^', CachedBlockInfo.hasState(IS_PUMPKIN))
					.where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(this)))
					.where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.golemPattern;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		super.neighborChanged(state, world, pos, blockIn, fromPos, isMoving);

		BlockPattern.PatternHelper blockpattern$patternhelper = this.getGolemPattern().find(world, pos);
		if (blockpattern$patternhelper != null) {
			for (int j = 0; j < this.getGolemPattern().getWidth(); ++j) {
				for (int k = 0; k < this.getGolemPattern().getHeight(); ++k) {
					CachedBlockInfo cachedblockinfo2 = blockpattern$patternhelper.getBlock(j, k, 0);
					world.setBlock(cachedblockinfo2.getPos(), Blocks.AIR.defaultBlockState(), 2);
					world.globalLevelEvent(2001, cachedblockinfo2.getPos(), Block.getId(cachedblockinfo2.getState()));
				}
			}

			BlockPos spawnPos = blockpattern$patternhelper.getBlock(1, 2, 0).getPos();
			MetalGolem golem = (MetalGolem) MobZEntities.METALGOLEM.create(world);
			golem.moveTo(spawnPos, 0.0F, 0.0F);
			world.addFreshEntity(golem);
		}
	}
	
	@Override
	public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (world.isClientSide) {
			return;
		}
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader world, List<ITextComponent> tooltip,
			ITooltipFlag options) {
		tooltip.add(new TranslationTextComponent("block.mobz.hardenedmetal_block.tooltip"));
	}
}
